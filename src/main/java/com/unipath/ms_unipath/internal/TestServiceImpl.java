package com.unipath.ms_unipath.internal;

import com.unipath.ms_unipath.domain.model.entities.Career;
import com.unipath.ms_unipath.domain.model.entities.Test;
import com.unipath.ms_unipath.domain.model.entities.TestCareer;
import com.unipath.ms_unipath.domain.services.TestService;
import com.unipath.ms_unipath.repositories.CareerRepository;
import com.unipath.ms_unipath.repositories.TestCareerRepository;
import com.unipath.ms_unipath.repositories.TestRepository;
import com.unipath.ms_unipath.rest.resources.DTOs.AnswerChasideDetailDTO;
import com.unipath.ms_unipath.rest.resources.test.CreateTestResource;
import com.unipath.ms_unipath.rest.resources.test.TestRequest;
import com.unipath.ms_unipath.rest.resources.test.TestResource;
import com.unipath.ms_unipath.rest.resources.test.TestResponse;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TestServiceImpl implements TestService {
    @Value("${external.api.url}")
    private String externalApiUrl;

    private final RestTemplate restTemplate;
    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final CareerRepository CareerRepository;
    private final TestCareerRepository testCareerRepository;

    @Autowired
    public TestServiceImpl(RestTemplate restTemplate, TestRepository testRepository, UserRepository userRepository, CareerRepository careerRepository, TestCareerRepository testCareerRepository) {
        this.restTemplate = restTemplate;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
        CareerRepository = careerRepository;
        this.testCareerRepository = testCareerRepository;
    }

    @Override
    public TestResponse getPredict (TestRequest request, Long user_id){
        //Hallamos los datos necesarios para guardar el Test
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        String interestDominant = evaluateChaside(request.listAnswers());
        User user = userRepository.findById(user_id);

        //Creamos el recurso con los datos del test
        CreateTestResource createTestResource = new CreateTestResource(
                request.preferred_course_1(),
                request.genre(),
                request.preferred_course_2(),
                request.preferred_course_3(),
                request.district(),
                request.type_school(),
                interestDominant,
                request.empathy_level(),
                request.listen_level(),
                request.solution_level(),
                request.communication_level(),
                request.teamwork_level(),
                request.monthly_cost(),
                fechaHoraActual
                );

        //Generamos la Entidad y guardamos en la BD
        var newTest = new Test(createTestResource, user);
        testRepository.save(newTest);

        //Obtenemos el test creado mediante el id
        Long testId = newTest.getId();
        Test testCreated = testRepository.findById(testId)
                .orElseThrow(() -> new NoSuchElementException("Test con ID " + testId + " no encontrado"));

        //Obtenemos la prediccion de la API externa
        List<TestResource> response =  externalApiCall(createTestResource);

        //Relacionamos la carrera y el test en la BD
        for (TestResource t : response) {
            Career career = (Career) CareerRepository.findByName(t.nameCareer());
            TestCareer newTestCareer = new TestCareer(testCreated, career, Float.parseFloat(t.hitRate()));
            testCareerRepository.save(newTestCareer);
        }

        TestResponse testResponse = new TestResponse(
                response,
                user.getName() + ' ' + user.getLastName(),
                Period.between(user.getBirthdate(), LocalDate.from(fechaHoraActual)).getYears(),
                testCreated.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy HH:mm:ss"))
        );
        return testResponse;
    }

    @Override
    public String evaluateChaside (List<AnswerChasideDetailDTO> chasideDetails){
        if (chasideDetails == null || chasideDetails.isEmpty()){
            return "No se proporcionaron respuestas para el test Chaside";
        }

        Map<String, Integer> conteoIntereses = new HashMap<String, Integer>();
        conteoIntereses.put("C",0);
        conteoIntereses.put("H",0);
        conteoIntereses.put("A",0);
        conteoIntereses.put("S",0);
        conteoIntereses.put("I",0);
        conteoIntereses.put("D",0);
        conteoIntereses.put("E",0);

        for (AnswerChasideDetailDTO dto : chasideDetails){
            if (dto.getScore() == 1){
                if (Arrays.asList(98,12,64,53,85,1,78,20,71,91,15,51,2,46).contains(dto.getId().intValue())){
                    conteoIntereses.put("C",conteoIntereses.get("C")+dto.getScore());
                } else if (Arrays.asList(9,34,80,25,95,67,41,74,56,89,63,30,72,86).contains(dto.getId().intValue())) {
                    conteoIntereses.put("H",conteoIntereses.get("H")+dto.getScore());
                } else if (Arrays.asList(21,45,96,57,28,11,50,3,81,36,22,39,76,82).contains(dto.getId().intValue())) {
                    conteoIntereses.put("A",conteoIntereses.get("A")+dto.getScore());
                } else if (Arrays.asList(33,92,70,8,87,62,23,44,16,52,69,40,29,4).contains(dto.getId().intValue())) {
                    conteoIntereses.put("S",conteoIntereses.get("S")+dto.getScore());
                } else if (Arrays.asList(75,6,19,38,60,27,83,54,47,97,26,59,90,10).contains(dto.getId().intValue())) {
                    conteoIntereses.put("I",conteoIntereses.get("I")+dto.getScore());
                } else if (Arrays.asList(84,31,48,73,5,65,14,37,58,24,13,66,18,43).contains(dto.getId().intValue())) {
                    conteoIntereses.put("D",conteoIntereses.get("D")+dto.getScore());
                } else if (Arrays.asList(77,42,88,17,93,32,68,49,35,61,94,7,79,55).contains(dto.getId().intValue())) {
                    conteoIntereses.put("E",conteoIntereses.get("E")+dto.getScore());
                } else {
                    System.out.println("Advertencia: ID de pregunta " + dto.getId() + " no mapeado a ningún interés conocido.");
                }
            }
        }

        int sumaTotal = conteoIntereses.values().stream().mapToInt(Integer::intValue).sum();
        if (sumaTotal == 0) {
            return "No se pudo determinar el interés (no se reconocieron respuestas válidas)";
        }

        String interesPrincipal = null;
        int maxVotos = 0;

        for (Map.Entry<String, Integer> entry : conteoIntereses.entrySet()) {
            if (entry.getValue() > maxVotos) {
                maxVotos = entry.getValue();
                interesPrincipal = entry.getKey();
            }
        }

        System.out.println("Conteo de intereses: " + conteoIntereses);
        return interesPrincipal;

    }

    @Override
    public List<TestResource> externalApiCall (CreateTestResource resource){
        try {
            //Creamos el recurso HTTP que se conectara a la API externa
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CreateTestResource> request = new HttpEntity<>(resource, headers);

            // Inicializamos el tipo de dato que reciba la respuesta del API
            ParameterizedTypeReference<List<TestResource>> responseType =
                    new ParameterizedTypeReference<List<TestResource>>() {};

            //Realizamos la solicitud al API REST con los parametros creados
            ResponseEntity<List<TestResource>> response = restTemplate.exchange(
                    externalApiUrl,
                    HttpMethod.POST,
                    request,
                    responseType
            );

            //Imprimimos en consola en caso de recibir un estado exitoso y retornamos lo obtenido
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("Respuesta de API externa: " + response.getBody());
                return response.getBody();
            } else {
                System.err.println("Error al consumir API externa. Código de estado: " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Excepción al consumir API externa: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

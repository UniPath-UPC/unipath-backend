package com.unipath.ms_unipath.internal;

import com.unipath.ms_unipath.domain.model.entities.Request;
import com.unipath.ms_unipath.domain.services.RequestService;
import com.unipath.ms_unipath.repositories.RequestRepository;
import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResource;
import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResponse;
import com.unipath.ms_unipath.security.application.internal.outboundservices.hashing.HashingService;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories.UserRepository;
import com.unipath.ms_unipath.shared.domain.exceptions.AuthenticatedException;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class RequestServiceImpls implements RequestService {

    public RequestRepository requestRepository;
    public UserRepository userRepository;
    private final HashingService hashingService;
    private final JavaMailSender mailSender;

    @Autowired
    public RequestServiceImpls(RequestRepository requestRepository, UserRepository userRepository, HashingService hashingService, JavaMailSender mailSender) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.mailSender = mailSender;
    }

    @Override
    public Optional<ValidationCodeResponse> createRequestCode (String email){
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new NotFoundException("Not found user with that email");

        Random random = new Random();
        int numeroAleatorio = 100000 + random.nextInt(900000);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Código de verificación");
        message.setText("Tu código de verificación es: " + numeroAleatorio);
        mailSender.send(message);


        Request newRequest = new Request(
                user,
                hashingService.encode(String.valueOf(numeroAleatorio)),
                LocalDateTime.now(),
                0
        );

        requestRepository.save(newRequest);

        return Optional.of(new ValidationCodeResponse(user.getId(), newRequest.getId()));
    }

    @Override
    public Integer validateRequest(ValidationCodeResource validationCodeResource){
        Request request = requestRepository.findById(validationCodeResource.id_request())
                .orElseThrow(() -> new NotFoundException("Not found request with id: "+ validationCodeResource.id_request()));

        if (hashingService.matches(validationCodeResource.code(), request.getValidation_code())) {
            request.setValidation(1);
            requestRepository.save(request);
            return 1;
        }
        else return 0;

    }
}

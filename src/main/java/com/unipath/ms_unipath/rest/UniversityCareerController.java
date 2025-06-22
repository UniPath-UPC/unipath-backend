package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.services.UniversityCareerService;
import com.unipath.ms_unipath.rest.resources.university.UniversityResource;
import com.unipath.ms_unipath.rest.resources.universitycareer.UniversityCareerRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/universitycareer", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "University Career", description = "University Career Management Endpoints")
public class UniversityCareerController {

    private final UniversityCareerService universityCareerService;


    public UniversityCareerController(UniversityCareerService universityCareerService) {
        this.universityCareerService = universityCareerService;
    }

    @GetMapping("/{careerName}")
    ResponseEntity<List<UniversityCareerRequest>> getUniversityCareers(@PathVariable String careerName) {
        String newCareerName = careerName.replace("-"," ");

        List<UniversityCareerRequest> universityCareerRequests = universityCareerService.getInfoUniversityByCareerName(newCareerName);

        return new ResponseEntity<>(universityCareerRequests, HttpStatus.OK);
    }
}

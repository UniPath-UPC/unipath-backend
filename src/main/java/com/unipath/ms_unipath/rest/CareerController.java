package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.services.CareerService;
import com.unipath.ms_unipath.rest.resources.career.CareerRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/career", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Career", description = "Career Management Endpoints")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping("/{careername}")
    ResponseEntity<CareerRequest> getCareer(@PathVariable String careername) {
        String setCareer = careername.replace("-", " ");
        CareerRequest careerRequest = careerService.getCareer(setCareer);

        return new ResponseEntity<>(careerRequest, HttpStatus.OK);
    }
}

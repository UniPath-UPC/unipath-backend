package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.services.CareerService;
import com.unipath.ms_unipath.domain.services.RequestService;
import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResource;
import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResponse;
import com.unipath.ms_unipath.rest.resources.test.TestRequest;
import com.unipath.ms_unipath.rest.resources.test.TestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/request", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Request", description = "Request Management Endpoints")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/code-validation")
    ResponseEntity<ValidationCodeResponse> createRequest(@RequestParam String email) {
        Optional<ValidationCodeResponse> response = requestService.createRequestCode(email);

        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/match-code")
    ResponseEntity<Integer> matchCode(@RequestBody ValidationCodeResource validationCodeResource) {
        Integer response = requestService.validateRequest(validationCodeResource);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

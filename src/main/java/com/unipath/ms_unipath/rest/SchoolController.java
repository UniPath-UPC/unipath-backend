package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.model.entities.School;
import com.unipath.ms_unipath.domain.services.SchoolService;
import com.unipath.ms_unipath.rest.resources.school.CreateSchoolResource;
import com.unipath.ms_unipath.rest.resources.school.SchoolsResponse;
import com.unipath.ms_unipath.rest.resources.university.UniversityResource;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/school", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "School", description = "School Management Endpoints")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    ResponseEntity<List<SchoolsResponse>> getAllSchools() {
        List<SchoolsResponse> schoolsResponses = schoolService.getAllSchools();

        return new ResponseEntity<>(schoolsResponses, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    ResponseEntity<School> createSchool(@RequestBody CreateSchoolResource resource) {
        School school = schoolService.create(resource);

        return new ResponseEntity<>(school, HttpStatus.CREATED);
    }

    @GetMapping("/{schoolId}")
    ResponseEntity<School> getSchool(@PathVariable Long schoolId) {
        School school = schoolService.getSchool(schoolId);

        return new ResponseEntity<>(school, HttpStatus.OK);
    }
}

package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.model.entities.University;
import com.unipath.ms_unipath.domain.services.UniversityService;
import com.unipath.ms_unipath.rest.resources.university.UniversityResource;
import com.unipath.ms_unipath.rest.resources.university.CreateUniversityResource;
import com.unipath.ms_unipath.rest.resources.university.UpdateUniversityResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/university", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "University", description = "University Management Endpoints")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping()
    ResponseEntity<List<UniversityResource>> getAllUniversity() {
        List<UniversityResource> universityResources = universityService.getAll()
                .stream().map(this::mapEntityToResource)
                .toList();

        return new ResponseEntity<>(universityResources, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<UniversityResource> createUniversity(@RequestBody CreateUniversityResource resource) {
        University university = universityService.create(resource);

        return new ResponseEntity<>(mapEntityToResource(university), HttpStatus.CREATED);
    }

    @PutMapping("/{universityId}")
    ResponseEntity<UniversityResource> updateUniversity(@RequestBody UpdateUniversityResource resource, @PathVariable Long universityId) {
        University university = universityService.update(universityId, resource);

        return new ResponseEntity<>(mapEntityToResource(university), HttpStatus.CREATED);
    }

    @DeleteMapping("/{universityId}")
    ResponseEntity<?> deleteUniversity(@PathVariable Long universityId) {
        universityService.delete(universityId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UniversityResource mapEntityToResource(University university) {
        return new UniversityResource(university.getId(), university.getName(), university.getImageUrl());
    }
}

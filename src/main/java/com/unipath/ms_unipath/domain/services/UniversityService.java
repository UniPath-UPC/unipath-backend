package com.unipath.ms_unipath.domain.services;

import com.unipath.ms_unipath.domain.model.entities.University;
import com.unipath.ms_unipath.rest.resources.university.CreateUniversityResource;
import com.unipath.ms_unipath.rest.resources.university.UpdateUniversityResource;

import java.util.List;

public interface UniversityService {
    List<University> getAll();
    University create(CreateUniversityResource resource);
    University update(Long universityId, UpdateUniversityResource resource);
    void delete(Long universityId);
}

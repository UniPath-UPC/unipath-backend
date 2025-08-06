package com.unipath.ms_unipath.domain.services;

import com.unipath.ms_unipath.domain.model.entities.School;
import com.unipath.ms_unipath.domain.model.entities.University;
import com.unipath.ms_unipath.rest.resources.school.CreateSchoolResource;
import com.unipath.ms_unipath.rest.resources.school.SchoolsResponse;
import com.unipath.ms_unipath.rest.resources.university.CreateUniversityResource;

import java.util.List;

public interface SchoolService {
    School create(CreateSchoolResource resource);
    List<SchoolsResponse> getAllSchools ();
}

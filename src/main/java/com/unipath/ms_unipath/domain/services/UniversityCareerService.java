package com.unipath.ms_unipath.domain.services;

import com.unipath.ms_unipath.rest.resources.universitycareer.UniversityCareerRequest;

import java.util.List;

public interface UniversityCareerService {
    List<UniversityCareerRequest> getInfoUniversityByCareerName (String careerName);
}

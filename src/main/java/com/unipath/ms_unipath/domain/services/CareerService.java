package com.unipath.ms_unipath.domain.services;

import com.unipath.ms_unipath.rest.resources.career.CareerRequest;

public interface CareerService {
    CareerRequest getCareer(String name);
}

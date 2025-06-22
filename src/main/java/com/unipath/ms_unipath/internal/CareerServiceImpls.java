package com.unipath.ms_unipath.internal;

import com.unipath.ms_unipath.domain.model.entities.Career;
import com.unipath.ms_unipath.domain.services.CareerService;
import com.unipath.ms_unipath.repositories.CareerRepository;
import com.unipath.ms_unipath.rest.resources.career.CareerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerServiceImpls implements CareerService {

    public CareerRepository careerRepository;

    @Autowired
    public CareerServiceImpls(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    @Override
    public CareerRequest getCareer(String name){
        Career career = (Career) careerRepository.findByName(name);

        return new CareerRequest(career.getName(), career.getDescription(), career.getImageUrl());
    }
}

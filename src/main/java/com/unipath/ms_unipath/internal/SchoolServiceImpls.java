package com.unipath.ms_unipath.internal;

import com.unipath.ms_unipath.domain.model.entities.School;
import com.unipath.ms_unipath.domain.services.SchoolService;
import com.unipath.ms_unipath.repositories.SchoolRepository;
import com.unipath.ms_unipath.rest.resources.school.CreateSchoolResource;
import com.unipath.ms_unipath.rest.resources.school.SchoolsResponse;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolServiceImpls implements SchoolService {

    public SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpls(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School create(CreateSchoolResource resource) {
        var newSchool = new School(resource);
        return schoolRepository.save(newSchool);
    }

    @Override
    public List<SchoolsResponse> getAllSchools (){
        List<School> schools = schoolRepository.findAll();

        List<SchoolsResponse> schoolsResponses = new ArrayList<>();

        for (School school : schools) {
            SchoolsResponse response = new SchoolsResponse(school.getId(), school.getName());
            schoolsResponses.add(response);
        }

        return schoolsResponses;
    }

    @Override
    public School getSchool (Long id){
        var school =  this.schoolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found school with id: "+ id));

        return school;
    }
}

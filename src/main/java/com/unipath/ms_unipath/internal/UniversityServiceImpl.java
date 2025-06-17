package com.unipath.ms_unipath.internal;

import com.unipath.ms_unipath.domain.model.entities.University;
import com.unipath.ms_unipath.domain.services.UniversityService;
import com.unipath.ms_unipath.repositories.UniversityRepository;
import com.unipath.ms_unipath.rest.resources.university.CreateUniversityResource;
import com.unipath.ms_unipath.rest.resources.university.UpdateUniversityResource;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public University create(CreateUniversityResource resource) {
        var newUniversity = new University(resource);
        return universityRepository.save(newUniversity);
    }

    @Override
    public University update(Long universityId, UpdateUniversityResource resource) {
        var foundedUniversity =  this.universityRepository.findById(universityId)
                .orElseThrow(() -> new NotFoundException("Not found university with id: "+ universityId));

        var updateUniversity = foundedUniversity.update(resource);

        return universityRepository.save(updateUniversity);
    }

    @Override
    public void delete(Long universityId) {
        var foundedUniversity =  this.universityRepository.findById(universityId)
                .orElseThrow(() -> new NotFoundException("Not found university with id: "+ universityId));

        universityRepository.delete(foundedUniversity);
    }
}

package com.unipath.ms_unipath.internal;

import com.unipath.ms_unipath.domain.model.entities.Career;
import com.unipath.ms_unipath.domain.model.entities.University;
import com.unipath.ms_unipath.domain.model.entities.UniversityCareer;
import com.unipath.ms_unipath.domain.services.UniversityCareerService;
import com.unipath.ms_unipath.repositories.CareerRepository;
import com.unipath.ms_unipath.repositories.UniversityCareerRepository;
import com.unipath.ms_unipath.repositories.UniversityRepository;
import com.unipath.ms_unipath.rest.resources.universitycareer.UniversityCareerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityCareerServiceImpls implements UniversityCareerService {

    private final UniversityCareerRepository universityCareerRepository;
    private final UniversityRepository universityRepository;
    private final CareerRepository careerRepository;

    @Autowired
    public UniversityCareerServiceImpls(UniversityCareerRepository universityCareerRepository, UniversityRepository universityRepository, CareerRepository careerRepository) {
        this.universityCareerRepository = universityCareerRepository;
        this.universityRepository = universityRepository;
        this.careerRepository = careerRepository;
    }

    @Override
    public List<UniversityCareerRequest> getInfoUniversityByCareerName (String careerName){
        Career career = (Career) careerRepository.findByName(careerName);

        List<UniversityCareer> universityCareers =  universityCareerRepository.findAllByCareerId(career.getId());

        List<UniversityCareerRequest> list = new ArrayList<UniversityCareerRequest>();

        for (UniversityCareer universityCareer : universityCareers) {
            UniversityCareerRequest newUniversityCareerRequest = new UniversityCareerRequest(
                    universityCareer.getUniversity().getLogoUrl(),
                    universityCareer.getUniversity().getName(),
                    universityCareer.getUniversity().getColor(),
                    universityCareer.getUniversity().getDescription(),
                    universityCareer.getUniversity().getImageUrl(),
                    universityCareer.getWebsiteUrl()
            );

            list.add(newUniversityCareerRequest);
        }

        return list;
    }
}

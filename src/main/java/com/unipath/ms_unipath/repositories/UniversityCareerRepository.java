package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.UniversityCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityCareerRepository extends JpaRepository<UniversityCareer, Long> {
    List<UniversityCareer> findAllByCareerId(Long id);
}

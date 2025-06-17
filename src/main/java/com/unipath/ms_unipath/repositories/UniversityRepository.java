package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}

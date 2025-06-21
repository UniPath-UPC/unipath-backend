package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}

package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
    Object findByName(String name);
}

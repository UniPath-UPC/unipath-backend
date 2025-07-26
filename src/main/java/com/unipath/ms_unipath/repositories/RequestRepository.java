package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}

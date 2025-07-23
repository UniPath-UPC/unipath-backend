package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByUserId (Long user_id);
}

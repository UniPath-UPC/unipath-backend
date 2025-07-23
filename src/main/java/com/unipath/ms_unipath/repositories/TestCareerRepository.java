package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.Career;
import com.unipath.ms_unipath.domain.model.entities.TestCareer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCareerRepository extends JpaRepository<TestCareer, Long> {
    List<TestCareer> findByTestIdOrderByHitRateDesc (Long test_id);
}

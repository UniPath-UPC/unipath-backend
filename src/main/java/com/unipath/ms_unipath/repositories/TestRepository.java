package com.unipath.ms_unipath.repositories;

import com.unipath.ms_unipath.domain.model.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByUserId (Long user_id);
    @Query("SELECT COUNT(t) FROM Test t " +
            "JOIN t.user u " +
            "JOIN u.school s " +
            "WHERE s.id = :schoolId")
    long countBySchoolId(@Param("schoolId") Long schoolId);
}

package com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories;

import com.unipath.ms_unipath.domain.model.entities.School;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.domain.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    List<User> findBySchool(School school);
    User findBySchoolAndRole(School school, Role role);
    User findById(Long id);

    boolean existsBySchoolAndRole(School school, Role role);
}
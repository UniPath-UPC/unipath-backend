package com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories;

import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    User findByEmail(String email);

    User findById(Long id);
}
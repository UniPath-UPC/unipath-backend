package com.unipath.ms_unipath.security.application.internal.commandservices;

import com.unipath.ms_unipath.security.domain.model.entities.Role;
import com.unipath.ms_unipath.security.domain.services.RoleService;
import com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.unipath.ms_unipath.security.interfaces.rest.resources.CreateRoleResource;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import com.unipath.ms_unipath.shared.domain.exceptions.ServerErrorException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class RoleCommandServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}

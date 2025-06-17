package com.unipath.ms_unipath.security.domain.services;

import com.unipath.ms_unipath.security.domain.model.entities.Role;
import com.unipath.ms_unipath.security.interfaces.rest.resources.CreateRoleResource;

import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleByName(String name);
}

package com.unipath.ms_unipath.security.domain.services;

import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignInResource;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignUpResource;

import java.util.Optional;

public interface UserService {
    Optional<User> signIn(SignInResource resource);
    Optional<User> signUp(SignUpResource resource);
}

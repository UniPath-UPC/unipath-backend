package com.unipath.ms_unipath.security.application.internal.commandservices;

import com.unipath.ms_unipath.rest.resources.user.UpdateUserResource;
import com.unipath.ms_unipath.security.application.internal.outboundservices.hashing.HashingService;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.domain.services.UserService;
import com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.unipath.ms_unipath.security.infrastructure.persistence.jpa.repositories.UserRepository;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignInResource;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignUpResource;
import com.unipath.ms_unipath.shared.domain.exceptions.AuthenticatedException;
import com.unipath.ms_unipath.shared.domain.exceptions.BadRequestException;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import com.unipath.ms_unipath.shared.domain.exceptions.ServerErrorException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
    }


    @Override
    public Optional<User> signIn(SignInResource resource) {
        var user = userRepository.findByEmail(resource.email());

        if (user == null)
            throw new NotFoundException("Not found user with that username");


        if (!hashingService.matches(resource.password(), user.getPassword()))
            throw new AuthenticatedException("Passwords do not match");

        return Optional.of(user);
    }

    @Override
    public Optional<User> signUp(SignUpResource resource) {
        if (userRepository.existsByEmail(resource.email()))
            throw new BadRequestException("Already exist a user with this email");

        var role = roleRepository.findByName(resource.role())
                .orElseThrow(() -> new NotFoundException("Not found a role with this name"));

        try {
            var user = new User(resource, role, hashingService.encode(resource.password()));
            return Optional.of(userRepository.save(user));
        } catch (Exception e) {
            throw new ServerErrorException();
        }
    }

    @Override
    public User getUserById(Long userId){
        User user = userRepository.findById(userId);
        return user;
    }

    @Override
    public User updateUser(Long userId, UpdateUserResource resource){
        var foundedUser =  this.userRepository.findById(userId);

        var updateUser = foundedUser.update(resource, hashingService.encode(resource.password()));

        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        var foundedUser =  this.userRepository.findById(userId);

        userRepository.delete(foundedUser);
    }
}

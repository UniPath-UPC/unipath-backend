package com.unipath.ms_unipath.security.interfaces.rest;
import com.unipath.ms_unipath.security.application.internal.outboundservices.tokens.TokenService;
import com.unipath.ms_unipath.security.domain.services.UserService;
import com.unipath.ms_unipath.security.interfaces.rest.resources.AuthenticatedUserResource;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignInResource;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignUpResource;
import com.unipath.ms_unipath.shared.domain.exceptions.BadRequestException;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserService userService;
    private final TokenService tokenService;

    public AuthenticationController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(
            @RequestBody SignInResource signInResource) {

        var token = tokenService.generateToken(signInResource.email());

        var user = userService.signIn(signInResource);

        if (user.isEmpty())
            throw new BadRequestException("Something went wrong with password or username");

        var authenticatedUserResource = new AuthenticatedUserResource(user.get().getId(), token);

        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/sign-up")
    public ResponseEntity<SignInResource> signUp(@RequestBody SignUpResource signUpResource) {
        var user = userService.signUp(signUpResource);

        if (user.isEmpty()) {
            throw new NotFoundException("Not found user");
        }

        var token = tokenService.generateToken(user.get().getEmail());

        var createUserResource = new SignInResource(user.get().getEmail(), token);
        return new ResponseEntity<>(createUserResource, HttpStatus.CREATED);
    }

}

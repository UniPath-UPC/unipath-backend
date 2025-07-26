package com.unipath.ms_unipath.rest;

import com.unipath.ms_unipath.domain.model.entities.University;
import com.unipath.ms_unipath.rest.resources.university.UniversityResource;
import com.unipath.ms_unipath.rest.resources.university.UpdateUniversityResource;
import com.unipath.ms_unipath.rest.resources.user.UpdatePasswordResource;
import com.unipath.ms_unipath.rest.resources.user.UpdateUserResource;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.domain.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "User Management Endpoints")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    ResponseEntity<User> updateUser(@RequestBody UpdateUserResource resource, @PathVariable Long userId) {
        User user = userService.updateUser(userId, resource);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.unipath.ms_unipath.security.interfaces.rest.resources;

import java.time.LocalDate;

public record SignUpResource(String name, String lastName, LocalDate birthdate, String email, String password, String role) {
}

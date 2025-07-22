package com.unipath.ms_unipath.rest.resources.user;

import java.time.LocalDate;

public record UpdateUserResource(String name, String lastName, LocalDate birthdate, String email, String password, String school) {
}

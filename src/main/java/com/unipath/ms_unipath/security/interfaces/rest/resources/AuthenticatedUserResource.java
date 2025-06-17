package com.unipath.ms_unipath.security.interfaces.rest.resources;

import java.util.UUID;

public record AuthenticatedUserResource(Long userId, String accessToken) {
}

package com.unipath.ms_unipath.domain.services;

import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResource;
import com.unipath.ms_unipath.rest.resources.request.ValidationCodeResponse;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RequestService {
    Optional<ValidationCodeResponse> createRequestCode (String email);
    Integer validateRequest(ValidationCodeResource validationCodeResource);
}

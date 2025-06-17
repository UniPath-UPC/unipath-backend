package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationError extends ApiError {

    public AuthenticationError(int httpStatus, String message) {
        super(httpStatus, message);
    }
}



package com.unipath.ms_unipath.shared.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticatedException extends RuntimeException {

    public AuthenticatedException(String message) {
        super(message);
    }
}
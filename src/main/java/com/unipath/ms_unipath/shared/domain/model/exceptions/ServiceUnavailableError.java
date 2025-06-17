package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceUnavailableError extends ApiError {
    private String retryAfter;

    public ServiceUnavailableError(int httpStatus, String message, String retryAfter) {
        super(httpStatus, message);
        this.retryAfter = retryAfter;
    }
}


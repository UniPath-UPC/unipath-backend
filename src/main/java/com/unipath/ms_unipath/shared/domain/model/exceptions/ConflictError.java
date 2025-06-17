package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConflictError extends ApiError {
    private String resource;
    private String error;

    public ConflictError(int httpStatus, String message, String resource, String error) {
        super(httpStatus, message);
        this.resource = resource;
        this.error = error;
    }
}

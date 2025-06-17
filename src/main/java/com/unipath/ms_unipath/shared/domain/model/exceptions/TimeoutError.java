package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeoutError extends ApiError {
    private String operation;
    private String timeout;

    public TimeoutError(int httpStatus, String message, String operation, String timeout) {
        super(httpStatus, message);
        this.operation = operation;
        this.timeout = timeout;
    }
}


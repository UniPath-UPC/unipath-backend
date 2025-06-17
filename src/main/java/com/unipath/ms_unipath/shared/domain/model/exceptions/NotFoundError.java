package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundError extends ApiError {
    public NotFoundError(int httpStatus, String message) {
        super(httpStatus, message);
    }
}


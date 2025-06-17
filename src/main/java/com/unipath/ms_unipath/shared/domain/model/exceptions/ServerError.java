package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerError extends ApiError {
    private String timestamp;

    public ServerError(int httpStatus, String message, String timestamp) {
        super(httpStatus, message);
        this.timestamp = timestamp;
    }
}

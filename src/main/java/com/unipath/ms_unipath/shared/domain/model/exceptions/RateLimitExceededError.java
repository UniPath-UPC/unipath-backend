package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateLimitExceededError extends ApiError {
    private int limit;
    private String timeWindow;

    public RateLimitExceededError(int httpStatus, String message, int limit, String timeWindow) {
        super(httpStatus, message);
        this.limit = limit;
        this.timeWindow = timeWindow;
    }
}


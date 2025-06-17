package com.unipath.ms_unipath.shared.domain.model.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseError extends ApiError {
    private String query;
    private String error;

    public DatabaseError(int httpStatus, String message, String query, String error) {
        super(httpStatus, message);
        this.query = query;
        this.error = error;
    }
}

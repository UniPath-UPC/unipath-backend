package com.unipath.ms_unipath.shared.infrastructure.errorhandling;

import com.unipath.ms_unipath.shared.domain.exceptions.AuthenticatedException;
import com.unipath.ms_unipath.shared.domain.exceptions.BadRequestException;
import com.unipath.ms_unipath.shared.domain.exceptions.NotFoundException;
import com.unipath.ms_unipath.shared.domain.exceptions.ServerErrorException;
import com.unipath.ms_unipath.shared.domain.model.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {}

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(BadRequestException ex) {
        ValidationError error = new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(Collections.singletonList(error)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(NotFoundException ex) {
        NotFoundError error = new NotFoundError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(new ErrorResponse(Collections.singletonList(error)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(AuthenticatedException ex) {
        AuthenticationError error = new AuthenticationError(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(new ErrorResponse(Collections.singletonList(error)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(ServerErrorException ex) {
        ServerError error = new ServerError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                LocalTime.now().toString()
        );
        return new ResponseEntity<>(new ErrorResponse(Collections.singletonList(error)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


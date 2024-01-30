package com.orness.error;

import com.orness.hexacustomer.core.exception.CustomerNotFoundException;
import com.orness.hexacustomer.core.exception.UniqueEmailException;
import com.orness.response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.UUID;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException e) {
        UUID stacktraceId = UUID.randomUUID();
        return new ErrorResponse(e.getMessage(), stacktraceId, HttpStatus.NOT_FOUND.name());
    }

    @ExceptionHandler(UniqueEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUniqueEmailFoundException(UniqueEmailException e) {
        UUID stacktraceId = UUID.randomUUID();
        return new ErrorResponse(e.getMessage(), stacktraceId, HttpStatus.CONFLICT.name());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationExceptionException(ConstraintViolationException e) {
        UUID stacktraceId = UUID.randomUUID();
        return new ErrorResponse(e.getMessage(), stacktraceId, HttpStatus.BAD_REQUEST.name());
    }

}

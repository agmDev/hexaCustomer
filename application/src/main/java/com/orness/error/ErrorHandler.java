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
        return new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.name());
    }

    @ExceptionHandler(UniqueEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUniqueEmailFoundException(UniqueEmailException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.name());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationExceptionException(ConstraintViolationException e) {
        return new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST.name());
    }

}

package com.orness.error;

import com.orness.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException e) {
        UUID stacktraceId = UUID.randomUUID();
        return new ErrorResponse(e.getMessage(), stacktraceId, HttpStatus.NOT_FOUND.name());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        UUID stacktraceId = UUID.randomUUID();
        String message = e.getAllErrors().stream()
                .map(error -> MessageFormat.format(Objects.requireNonNull(error.getDefaultMessage()), error.getArguments()))
                .collect(Collectors.joining(" ; "));
        return new ErrorResponse(message, stacktraceId, HttpStatus.BAD_REQUEST.name());
    }

}

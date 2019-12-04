package com.dchristofolli.poc.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Handler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleBadRequestExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ApiException.class)
    public Map<String, HttpStatus> handleApiException(ApiException apiException) {
        Map<String, HttpStatus> errors = new HashMap<>();
        errors.put(apiException.getMessage(), apiException.getStatus());
        return errors;
    }
    @ExceptionHandler(Exception.class)
    public Map<ErrorModel, HttpStatus> handleException(Exception e) {
        Map<ErrorModel, HttpStatus> errors = new HashMap<>();
        ErrorModel error = ErrorModel.builder()
                .message("Unexpected Error")
                .error(e.getClass().getName())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        errors.put(error, HttpStatus.INTERNAL_SERVER_ERROR);
        return errors;
    }
}

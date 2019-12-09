package com.dchristofolli.poc.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel handleValidationExceptions(MethodArgumentNotValidException e) {
        return ErrorModel.builder()
                .error(e.getClass().getName())
                .message("Invalid data")
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApiException.class)
    public ErrorModel apiExceptionValidator(ApiException e) {
        return ErrorModel.builder()
                .message(e.getMessage())
                .error(e.getClass().getName())
                .status(e.getStatus())
                .build();
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorModel handleApiException(ConflictException e) {
        return ErrorModel.builder()
                .message(e.getMessage())
                .error(e.getClass().getName())
                .status(e.getStatus())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorModel handleException(Exception e) {
        return ErrorModel.builder()
                .message("Unexpected Error")
                .error(e.getClass().getName())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

}

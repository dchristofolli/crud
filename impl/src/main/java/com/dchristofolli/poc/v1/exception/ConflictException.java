package com.dchristofolli.poc.v1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends ApiException {
    public ConflictException(String message, HttpStatus status) {
        super(message, status);
    }
}

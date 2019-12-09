package com.dchristofolli.poc.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@lombok.Data
class ErrorModel {
    String message;
    String error;
    HttpStatus status;
}
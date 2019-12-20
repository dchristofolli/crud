package com.dchristofolli.poc.v1.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Builder
@AllArgsConstructor
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorModel {
    String message;
    String error;
    HttpStatus status;
    Map<String, String> fieldErrors;
}
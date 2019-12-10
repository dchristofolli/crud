package com.dchristofolli.poc.v1.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Builder
@AllArgsConstructor
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorModel {
    String message;
    String error;
    HttpStatus status;
    Map<String, String> fieldErrors;
}
package com.dchristofolli.poc.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel {
    private String id;
    private String name;
}


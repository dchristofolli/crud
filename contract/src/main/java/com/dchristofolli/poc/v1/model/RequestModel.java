package com.dchristofolli.poc.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestModel {
    @ApiModelProperty(notes = "User name", required = true)
    @NotBlank(message = "{requiredField}")
    @Size(min = 3, max = 12, message = "{nameRequiredSize}")
    private String name;

    @ApiModelProperty(notes = "User social security number", required = true)
    @NotBlank(message = "{requiredField}")
    @CPF(message = "{invalidSecurityNumber}")
    private String cpf;

    @ApiModelProperty(notes = "User e-mail address", required = true)
    @NotBlank(message = "{requiredField}")
    @Email(message = "{invalidEmail}")
    private String email;

    @ApiModelProperty(notes = "User account password", required = true)
    @NotBlank(message = "{requiredField}")
    @Size(min = 4, max = 12, message = "{invalidPass}")
    private String password;
}
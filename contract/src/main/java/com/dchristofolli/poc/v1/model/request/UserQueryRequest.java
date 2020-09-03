package com.dchristofolli.poc.v1.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryRequest {

    @ApiModelProperty(notes = "User id")
    private String id;

    @ApiModelProperty(notes = "User name")
    private String name;

    @ApiModelProperty(notes = "User social security number")
    @CPF(message = "{invalidSecurityNumber}")
    @Size(max = 11, message = "{onlyNumbers}")
    private String cpf;

    @ApiModelProperty(notes = "User e-mail address")
    @Email(message = "{invalidEmail}")
    private String email;
}

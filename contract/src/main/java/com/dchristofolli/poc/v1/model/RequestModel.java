package com.dchristofolli.poc.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestModel {
    @Size(min = 3, max = 16, message = "{nameMinSize}")
    private String name;

    @CPF(message = "{invalidCpf}")
    private String cpf;

    @NotBlank(message = "{emailNotBlank}")
    @Email(message = "{invalidEmail}")
    private String email;

    @Size(min = 4, max = 12, message = "{invalidPass}")
    private String password;
}

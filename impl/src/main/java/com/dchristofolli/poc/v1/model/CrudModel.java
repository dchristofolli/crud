package com.dchristofolli.poc.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrudModel {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
}

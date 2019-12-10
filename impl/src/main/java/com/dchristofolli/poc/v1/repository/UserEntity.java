package com.dchristofolli.poc.v1.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Users")
public class UserEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    @Indexed(unique = true)
    private String cpf;

    @Indexed(unique = true)
    private String email;

    private String password;
}

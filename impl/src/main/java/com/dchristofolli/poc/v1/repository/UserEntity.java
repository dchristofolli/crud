package com.dchristofolli.poc.v1.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private String id;

    @Indexed(unique = true)
    @JsonProperty("name")
    private String name;

    @Indexed(unique = true)
    @JsonProperty("cpf")
    private String cpf;

    @Indexed(unique = true)
    @JsonProperty("email")
    private String email;
}

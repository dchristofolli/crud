package com.dchristofolli.poc.v1.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(columnDefinition = "text", unique = true)
    private String name;

    @Column(columnDefinition = "text", unique = true)
    private String cpf;

    @Column(columnDefinition = "text", unique = true)
    private String email;

    @Column(columnDefinition = "text", unique = true)
    private String password;
}

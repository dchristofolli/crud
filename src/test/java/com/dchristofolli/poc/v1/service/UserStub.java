package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.repository.UserEntity;

public class UserStub {
    public static UserEntity entityStubRequest(){
        return UserEntity.builder()
                .name("stubber")
                .email("stub@teste.com")
                .cpf("55368778015")
                .password("123456")
                .build();
    }

    public static UserEntity entityStubResponse(){
        return UserEntity.builder()
                .id("1")
                .name("stubber")
                .email("stub@teste.com")
                .cpf("55368778015")
                .password("123456")
                .build();
    }
}

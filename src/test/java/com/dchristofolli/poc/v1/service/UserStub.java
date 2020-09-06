//package com.dchristofolli.poc.v1.service;
//
//import com.dchristofolli.poc.v1.model.request.UserRequest;
//import com.dchristofolli.poc.v1.repository.UserEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserStub {
//    public static UserEntity entityStubRequest() {
//        return UserEntity.builder()
//                .name("stubber")
//                .email("stub@teste.com")
//                .cpf("55368778015")
//                .build();
//    }
//
//    public static UserEntity entityStubModel() {
//        return UserEntity.builder()
//                .id("1")
//                .name("stubber")
//                .email("stub@teste.com")
//                .cpf("55368778015")
//                .build();
//    }
//
//    public static List<UserEntity> entityStubList() {
//        List<UserEntity> list = new ArrayList<>();
//        list.add(entityStubModel());
//        return list;
//    }
//
//    public static UserRequest wrongUserRequestStub(){
//        return UserRequest.builder()
//                .name("1")
//                .cpf("0")
//                .email("d")
//                .build();
//    }
//}

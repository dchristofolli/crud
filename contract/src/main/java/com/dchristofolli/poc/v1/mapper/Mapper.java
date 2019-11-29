package com.dchristofolli.poc.v1.mapper;

import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.model.RequestModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {
    public static CrudModel mapRequestToModel(RequestModel model){
        return CrudModel.builder()
                .name(model.getName())
                .email(model.getEmail())
                .cpf(model.getCpf())
                .password(model.getPassword())
                .build();
    }
}

package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractMapper {
    public static CrudModel mapRequestToModel(RequestModel model){
        return CrudModel.builder()
                .name(model.getName())
                .email(model.getEmail())
                .cpf(model.getCpf())
                .password(model.getPassword())
                .build();
    }

    public static ResponseModel mapModelToResponse(CrudModel model){
        return ResponseModel.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}


package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import com.dchristofolli.poc.v1.model.UserModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ContractMapper {
    static UserModel mapRequestToModel(RequestModel model) {
        return UserModel.builder()
                .name(model.getName().toLowerCase())
                .email(model.getEmail())
                .cpf(model.getCpf())
                .password(model.getPassword())
                .build();
    }

    static ResponseModel mapModelToResponse(UserModel model) {
        return ResponseModel.builder()
                .id(model.getId())
                .name(model.getName().toLowerCase())
                .build();
    }
}


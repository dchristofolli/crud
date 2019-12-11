package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.model.request.UserRequest;
import com.dchristofolli.poc.v1.model.response.UserResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractMapper {
    public static UserModel mapRequestToModel(UserRequest model) {
        return UserModel.builder()
                .name(model.getName().toLowerCase())
                .email(model.getEmail())
                .cpf(model.getCpf())
                .password(model.getPassword())
                .build();
    }

    public static UserResponse mapModelToResponse(UserModel model) {
        return UserResponse.builder()
                .id(model.getId())
                .name(model.getName().toLowerCase())
                .build();
    }
}


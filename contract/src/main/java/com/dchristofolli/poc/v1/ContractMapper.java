package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.model.request.UserRequest;
import com.dchristofolli.poc.v1.model.response.UserListResponse;
import com.dchristofolli.poc.v1.model.response.UserResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractMapper {
    public static UserModel mapRequestToModel(UserRequest model) {
        return UserModel.builder()
                .name(model.getName().toLowerCase())
                .email(model.getEmail())
                .cpf(model.getCpf())
                .build();
    }

    public static UserResponse mapModelToResponse(UserModel model) {
        return UserResponse.builder()
                .name(model.getName().toLowerCase())
                .email(model.getEmail())
                .cpf(model.getCpf())
                .build();
    }

    public static UserResponse mapModelToContract(UserModel model){
        return UserResponse.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }

    public static UserListResponse mapToResponseList(List<UserModel> userModelList) {
        List<UserResponse> collect = userModelList.stream()
                .map(userModel -> UserResponse.builder()
                        .cpf(userModel.getCpf())
                        .name(userModel.getName())
                        .email(userModel.getEmail())
                        .build()).collect(Collectors.toList());
        return UserListResponse.builder()
                .list(collect)
                .quantity(collect.size())
                .build();
    }
}


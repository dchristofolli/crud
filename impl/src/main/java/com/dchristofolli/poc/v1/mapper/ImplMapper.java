package com.dchristofolli.poc.v1.mapper;

import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.repository.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImplMapper {
    public static UserModel mapEntityToModel(UserEntity entity) {
        return UserModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .build();
    }

    public static UserEntity mapModelToEntity(UserModel model) {
        return UserEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .cpf(model.getCpf())
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }
}

package com.dchristofolli.poc.v1.mapper;

import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.model.ImplResponseModel;
import com.dchristofolli.poc.v1.repository.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImplMapper {
    public static CrudModel mapEntityToModel(UserEntity entity) {
        return CrudModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static UserEntity mapModelToEntity(CrudModel model) {
        return UserEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .cpf(model.getCpf())
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }

    public static ImplResponseModel mapEntityToResponse(UserEntity entity){
        return ImplResponseModel.builder()
                .name(entity.getName())
                .build();
    }
}

package com.dchristofolli.poc.v1.mapper;

import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.model.ImplResponseModel;
import com.dchristofolli.poc.v1.repository.CrudEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImplMapper {
    public static CrudModel mapEntityToModel(CrudEntity entity) {
        return CrudModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static CrudEntity mapModelToEntity(CrudModel model) {
        return CrudEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .cpf(model.getCpf())
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }

    public static ImplResponseModel mapEntityToResponse(CrudEntity entity){
        return ImplResponseModel.builder()
                .name(entity.getName())
                .build();
    }
}

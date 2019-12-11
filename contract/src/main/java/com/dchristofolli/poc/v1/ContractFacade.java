package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.dchristofolli.poc.v1.ContractMapper.mapModelToResponse;
import static com.dchristofolli.poc.v1.ContractMapper.mapRequestToModel;

@AllArgsConstructor
@Component
public class ContractFacade {
    private ImplFacade facade;

    public ResponseModel createUser(RequestModel requestModel) {
        return mapModelToResponse(facade.createUser(mapRequestToModel(requestModel)));
    }

    public List<ResponseModel> findAllUsers() {
        return facade.findAllUsers().stream()
                .map(ContractMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public ResponseModel delete(String id) {
        return mapModelToResponse(facade.delete(id));
    }

    public List<ResponseModel> findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name) {
        return facade.find(id, cpf, email, name)
                .stream()
                .map(ContractMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public ResponseModel updateUsername(String oldName, String newName) {
        return mapModelToResponse(facade.updateUserName(oldName, newName));
    }
}
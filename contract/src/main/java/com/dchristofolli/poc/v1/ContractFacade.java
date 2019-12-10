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

    public ResponseModel findUserById(String id) {
        return mapModelToResponse(facade.findUserById(id));
    }

    public List<ResponseModel> findAllUsers() {
        return facade.findAllUsers().stream()
                .map(ContractMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public ResponseModel delete(String id) {
        return mapModelToResponse(facade.delete(id));
    }

    public ResponseModel findUserByCpf(String cpf) {
        return mapModelToResponse(facade.findUserByCpf(cpf));
    }

    public ResponseModel findUserByName(String name) {
        return mapModelToResponse(facade.findUserByName(name));
    }

    public ResponseModel findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name) {
        return mapModelToResponse(facade.findByIdOrCpfOrEmailOrName(id, cpf, email, name));
    }

    public ResponseModel updateUsername(String oldName, String newName) {
        return mapModelToResponse(facade.updateUserName(oldName, newName));
    }
}
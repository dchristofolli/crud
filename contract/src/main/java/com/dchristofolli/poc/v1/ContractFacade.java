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

    public ResponseModel showUserById(String id) {
        return mapModelToResponse(facade.showUserById(id));
    }

    public List<ResponseModel> showAllUsers() {
        return facade.showAllUsers().stream()
                .map(ContractMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        facade.delete(id);
    }

    public ResponseModel updatePassword(String name, String oldPass, String newPass) {
        return mapModelToResponse(facade.updatePassword(name, oldPass, newPass));
    }

    public ResponseModel findUserByCpf(String cpf) {
        return mapModelToResponse(facade.findUserByCpf(cpf));
    }

    public ResponseModel findUserByName(String name) {
        return mapModelToResponse(facade.findUserByName(name));
    }

    public ResponseModel findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name){
        return mapModelToResponse(facade.findByIdOrCpfOrEmailOrName(id, cpf, email, name));
    }

}
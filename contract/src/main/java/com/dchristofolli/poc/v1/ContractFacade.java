package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.ImplResponseModel;
import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import com.dchristofolli.poc.v1.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dchristofolli.poc.v1.ContractMapper.mapModelToResponse;
import static com.dchristofolli.poc.v1.ContractMapper.mapRequestToModel;

@AllArgsConstructor
@Component
public class ContractFacade {
    private CrudService service;

    public ResponseModel createUser(RequestModel model) {
        return mapModelToResponse(service.create(mapRequestToModel(model)));
    }
    public ResponseModel showUserById(String id) {
        return mapModelToResponse(service.showUserById(id));
    }

    public List<ImplResponseModel> showAllUsers() {
        return service.showAllUsers();
    }

    public void delete(String id) {
        service.delete(id);
    }

    public ResponseModel updatePassword(String id, String oldPass, String newPass) {
        return mapModelToResponse(service.updatePassword(id, oldPass, newPass));
    }

    public ResponseModel findUserByCpf(String cpf) {
        return mapModelToResponse(service.findUserByCpf(cpf));
    }

    public ResponseModel findUserByName(String name) {
        return mapModelToResponse(service.findUserByName(name));
    }
}
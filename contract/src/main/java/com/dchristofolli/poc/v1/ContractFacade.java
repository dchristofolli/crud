package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.model.RequestModel;
import com.dchristofolli.poc.v1.model.ResponseModel;
import com.dchristofolli.poc.v1.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dchristofolli.poc.v1.ContractMapper.mapModelToResponse;
import static com.dchristofolli.poc.v1.ContractMapper.mapRequestToModel;

@Configuration
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

    public ResponseModel update(String id, RequestModel user) {
        return mapModelToResponse(service.update(id, mapRequestToModel(user)));
    }

    public List<CrudModel> showAllUsers() {
        return service.showAllUsers();
    }

    public void delete(String id) {
        service.delete(id);
    }
}
package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.request.UserQueryRequest;
import com.dchristofolli.poc.v1.model.request.UserRequest;
import com.dchristofolli.poc.v1.model.response.UserResponse;
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

    public UserResponse createUser(UserRequest requestModel) {
        return mapModelToResponse(facade.createUser(mapRequestToModel(requestModel)));
    }

    public UserResponse delete(String id) {
        return mapModelToResponse(facade.delete(id));
    }

    public List<UserResponse> find(UserQueryRequest userQueryRequest) {
        return facade.find(userQueryRequest.getId(), userQueryRequest.getCpf(),
                userQueryRequest.getEmail(), userQueryRequest.getCpf())
                .stream()
                .map(ContractMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse updateUsername(String oldName, String newName) {
        return mapModelToResponse(facade.updateUserName(oldName, newName));
    }
}
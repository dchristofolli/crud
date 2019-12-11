package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.request.UserQueryRequest;
import com.dchristofolli.poc.v1.model.request.UserRequest;
import com.dchristofolli.poc.v1.model.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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

    public List<UserResponse> findAllUsers() {
        return facade.findAllUsers().stream()
                .map(ContractMapper::mapModelToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse delete(String id) {
        return mapModelToResponse(facade.delete(id));
    }

    public List<UserResponse> findByIdOrCpfOrEmailOrName(UserQueryRequest userQueryRequest) {
        if(ObjectUtils.isEmpty(userQueryRequest))
            return findAllUsers();
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
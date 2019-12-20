package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.model.request.UserQueryRequest;
import com.dchristofolli.poc.v1.model.request.UserRequest;
import com.dchristofolli.poc.v1.model.response.UserListResponse;
import com.dchristofolli.poc.v1.model.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dchristofolli.poc.v1.ContractMapper.*;

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

    public UserListResponse find(UserQueryRequest userQueryRequest) {
        List<UserModel> models = facade.find(userQueryRequest.getId(), userQueryRequest.getCpf(),
                userQueryRequest.getEmail(), userQueryRequest.getCpf());
        return mapToResponseList(models);
    }

    public UserResponse updateUsername(String oldName, String newName) {
        return mapModelToResponse(facade.updateUserName(oldName, newName));
    }
}
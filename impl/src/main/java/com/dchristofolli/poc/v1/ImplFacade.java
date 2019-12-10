package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.mapper.ImplMapper;
import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapEntityToModel;


@AllArgsConstructor
@Component
public class ImplFacade {
    private UserService service;

    public UserModel createUser(UserModel model) {
        return mapEntityToModel(service.create(model));
    }

    public UserModel findUserById(String id) {
        return mapEntityToModel(service.showUserById(id));
    }

    public List<UserModel> findAllUsers() {
        service.emptyListChecker();
        return service.findAllUsers()
                .stream()
                .map(ImplMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public UserModel delete(String id) {
        return mapEntityToModel(service.delete(id)
                .orElseThrow(() -> new ApiException("Invalid id", HttpStatus.BAD_REQUEST)));
    }

    public UserModel findUserByCpf(String cpf) {
        return mapEntityToModel(service.findUserByCpf(cpf));
    }

    public UserModel findUserByName(String name) {
        return mapEntityToModel(service.findUserByName(name));
    }

    public UserModel findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name) {
        return mapEntityToModel(service.findUserByIdOrCpfOrEmailOrName(id, cpf, email, name));
    }

    public UserModel updateUserName(String oldName, String newName) {
        if (service.userDoesNotExistsByName(oldName.toLowerCase()))
            throw new ApiException("User does not exists", HttpStatus.NOT_FOUND);
        if (service.newNameIsEqualsOldName(oldName, newName))
            throw new ApiException("New name must be different from old one", HttpStatus.BAD_REQUEST);
        return mapEntityToModel(service.updateUsername(oldName, newName));
    }
}

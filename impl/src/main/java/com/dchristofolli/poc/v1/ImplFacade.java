package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.mapper.ImplMapper;
import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.repository.UserEntity;
import com.dchristofolli.poc.v1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public List<UserModel> findAllUsers() {
        if (service.repositoryIsEmpty())
            throw new ApiException("No users found", HttpStatus.NOT_FOUND);
        List<UserEntity> list = service.findAllUsers();
        list.forEach(userEntity -> userEntity.setCpf(null));
        return list.stream()
                .map(ImplMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public UserModel delete(String id) {
        return mapEntityToModel(service.delete(id)
                .orElseThrow(() -> new ApiException("Invalid id", HttpStatus.BAD_REQUEST)));
    }

    public List<UserModel> find(String id, String cpf, String email, String name) {
        List<UserModel> list = new ArrayList<>();
        if (service.userArgumentsIsEmpty(id, cpf, email, name))
            return findAllUsers();
        list.add(mapEntityToModel(service.findByIdOrCpfOrEmailOrName(id, cpf, email, name)));
        return list;
    }

    public UserModel updateUserName(String oldName, String newName) {
        if (service.userDoesNotExistsByName(oldName.toLowerCase()))
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        if (service.newNameIsEqualsOldName(oldName, newName))
            throw new ApiException("New name must be different from old one", HttpStatus.BAD_REQUEST);
        return mapEntityToModel(service.updateUsername(oldName, newName));
    }
}

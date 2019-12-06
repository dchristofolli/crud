package com.dchristofolli.poc.v1;

import com.dchristofolli.poc.v1.mapper.ImplMapper;
import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapEntityToModel;


@AllArgsConstructor
@Component
public class ImplFacade {
    private UserService service;

    public UserModel createUser(UserModel model) {
        service.userValidator(model);
        return mapEntityToModel(service.create(model));
    }

    public UserModel showUserById(String id) {
        return mapEntityToModel(service.showUserById(id));
    }

    public List<UserModel> showAllUsers() {
        return service.showAllUsers()
                .stream()
                .map(ImplMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        service.delete(id);
    }

    public UserModel updatePassword(String id, String oldPass, String newPass) {
        return mapEntityToModel(service.updatePassword(id, oldPass, newPass));
    }

    public UserModel findUserByCpf(String cpf) {
        return mapEntityToModel(service.findUserByCpf(cpf));
    }

    public UserModel findUserByName(String name) {
        return mapEntityToModel(service.findUserByName(name));
    }
}

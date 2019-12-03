package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.mapper.ImplMapper;
import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.model.ImplResponseModel;
import com.dchristofolli.poc.v1.repository.CrudEntity;
import com.dchristofolli.poc.v1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapEntityToModel;
import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapModelToEntity;


@AllArgsConstructor
@Service
public class CrudService {
    private UserRepository repository;
    private ServiceUtils serviceUtils;

    public CrudModel create(CrudModel user) {

        return mapEntityToModel(repository.save(mapModelToEntity(user)));
    }

    public CrudModel showUserById(String id) {
        return mapEntityToModel(repository.findById(id)
                .orElseThrow(() -> new ApiException("User not exists", HttpStatus.NOT_FOUND)));
    }

    public CrudModel update(String id, CrudModel user) {
        Optional<CrudEntity> usr = serviceUtils.updateUser(id, user);
        if (usr.isEmpty())
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        return mapEntityToModel(repository.save(usr.get()));
    }

    public List<ImplResponseModel> showAllUsers() {
        List<ImplResponseModel> users = repository
                .findAll()
                .stream()
                .map(ImplMapper::mapEntityToResponse)
                .collect(Collectors.toList());
        if (users.isEmpty()) throw new ApiException("No users found", HttpStatus.NOT_FOUND);
        return users;
    }


    public void delete(String id) {
        if (!repository.existsById(id))
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }
}

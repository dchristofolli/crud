package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.repository.UserEntity;
import com.dchristofolli.poc.v1.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapModelToEntity;


@AllArgsConstructor
@Service
@Slf4j
public class UserService {
    private UserRepository repository;

    public UserEntity create(UserModel user) {
        return repository.save(mapModelToEntity(user));
    }

    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }

    public boolean repositoryIsEmpty() {
        return repository.findAll().isEmpty();
    }

    public Optional<UserEntity> delete(String id) {
        Optional<UserEntity> entity = repository.findById(id);
        repository.delete(entity.orElseThrow(() -> new ApiException("Invalid id", HttpStatus.BAD_REQUEST)));
        return entity;
    }

    public UserEntity findByIdOrCpfOrEmailOrName(UserEntity entity) {
        return repository.findByIdOrCpfOrEmailOrName(entity.getId(), entity.getCpf(),
                entity.getEmail(), entity.getName().toLowerCase())
                .orElseThrow(() -> new ApiException("Bad request", HttpStatus.BAD_REQUEST));
    }

    public boolean userDoesNotExistsByName(String name) {
        return !repository.existsByName(name.toLowerCase());
    }

    public UserEntity updateUsername(String oldName, String newName) {
        UserEntity entity = repository.findByName(oldName)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        entity.setName(newName);
        return repository.save(entity);
    }

    public boolean newNameIsEqualsOldName(String oldName, String newName) {
        return oldName.equals(newName);
    }

    public boolean userArgumentsIsEmpty(UserModel model) {
        return ObjectUtils.isEmpty(model.getId()) && ObjectUtils.isEmpty(model.getCpf()) &&
                ObjectUtils.isEmpty(model.getEmail()) && ObjectUtils.isEmpty(model.getName());
    }
}

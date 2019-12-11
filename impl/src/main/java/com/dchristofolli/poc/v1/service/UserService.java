package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.repository.UserEntity;
import com.dchristofolli.poc.v1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapModelToEntity;


@AllArgsConstructor
@Service
public class UserService {
    private UserRepository repository;

    public UserEntity create(UserModel user) {
        return repository.save(mapModelToEntity(user));
    }

    public UserEntity showUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Invalid id", HttpStatus.BAD_REQUEST));
    }

    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }

    public void emptyListChecker() {
        if (repository.findAll().isEmpty()) throw new ApiException("No users found", HttpStatus.NOT_FOUND);
    }

    public Optional<UserEntity> delete(String id) {
        Optional<UserEntity> entity = repository.findById(id);
        repository.delete(entity.orElseThrow(() -> new ApiException("Invalid id", HttpStatus.BAD_REQUEST)));
        return entity;
    }

    public UserEntity findByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new ApiException("Invalid number", HttpStatus.BAD_REQUEST));
    }

    public UserEntity findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new ApiException("Bad request", HttpStatus.BAD_REQUEST));
    }

    public UserEntity findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name) {
        //todo tirar o "user" do nome dos métodos
        return repository.findByIdOrCpfOrEmailOrName(id, cpf, email, name)
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
}

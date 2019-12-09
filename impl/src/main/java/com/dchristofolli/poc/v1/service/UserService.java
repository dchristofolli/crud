package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.exception.ConflictException;
import com.dchristofolli.poc.v1.model.UserModel;
import com.dchristofolli.poc.v1.repository.UserEntity;
import com.dchristofolli.poc.v1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public void registrationChecker(UserModel user) {
        if (repository.existsByCpfOrEmailOrName(user.getCpf(), user.getEmail(), user.getName()))
            throw new ConflictException("User already exists in database", HttpStatus.CONFLICT);
    }

    public UserEntity showUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("User not exists", HttpStatus.NOT_FOUND));
    }

    public List<UserEntity> showAllUsers() {
        return repository.findAll();
    }

    public void emptyListValidator() {
        if (repository.findAll().isEmpty()) throw new ApiException("No users found", HttpStatus.NOT_FOUND);
    }

    public void delete(String id) {
        if (!repository.existsById(id))
            throw new ApiException("User not found in database", HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }

    public UserEntity findUserByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new ApiException("Bad request", HttpStatus.BAD_REQUEST));
    }

    public UserEntity findUserByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserEntity findUserByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name) {
        return repository.findByIdOrCpfOrEmailOrName(id, cpf, email, name)
                .orElseThrow(() -> new ApiException("Bad request", HttpStatus.BAD_REQUEST));
    }

    public boolean userExistsByName(String name) {
        //todo remover
        System.out.println("USER EXISTS");
        return repository.existsByName(name);
    }

    public UserEntity updateUsername(String oldName, String newName) {
        //todo remover
        System.out.println("ENTRANDO NO UPDATE" + oldName + " " + newName);
        UserEntity entity = repository.findByName(oldName)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        System.out.println("NOT FOUND");
        entity.setName(newName);
        System.out.println("FINALIZANDO UPDATE");
        return repository.save(entity);
    }
}

package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.repository.CrudEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class ServiceUtils {
    private CrudRepository repository;
    Optional<CrudEntity> updateUser(String id, CrudModel user) {
        Optional<CrudEntity> usr = repository.findById(id);
        if (user.getEmail() != null)
            usr.get().setEmail(user.getEmail());
        if (user.getName() != null)
            usr.get().setName(user.getName());
        if (user.getCpf() != null)
            usr.get().setCpf(user.getCpf());
        if (user.getEmail() != null)
            usr.get().setEmail(user.getEmail());
        return usr;
    }
}

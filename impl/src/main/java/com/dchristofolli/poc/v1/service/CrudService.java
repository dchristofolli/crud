package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.exception.ApiException;
import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.repository.CrudEntity;
import com.dchristofolli.poc.v1.repository.CrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapEntityToModel;
import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapModelToEntity;


@AllArgsConstructor
@Service
public class CrudService {
    private CrudRepository repository;

    public CrudModel create(CrudModel user) {
        return mapEntityToModel(repository.save(mapModelToEntity(user)));
    }

    public CrudModel showUserById(String id) {
        return mapEntityToModel(repository.findById(id)
                .orElseThrow(() -> new ApiException("User not exists", HttpStatus.NOT_FOUND)));
    }

    public CrudModel update(String id, CrudModel user) {
        Optional<CrudEntity> usr = repository.findById(id);
        if(user.getEmail() != null)
            usr.get().setEmail(user.getEmail());
        if(user.getName() != null)
            usr.get().setName(user.getName());
        if(user.getCpf() != null)
            usr.get().setCpf(user.getCpf());
        if(user.getEmail() != null)
            usr.get().setEmail(user.getEmail());
        return mapEntityToModel(repository.save(usr.get()));
    }

//    public CrudModel findById(String id) {
//        return ImplMapper.mapEntityToModel(repository.findById(id));
//    }
//
//    public List<CrudEntity> findAll(){
//        return repository.findAll();
//    }
}

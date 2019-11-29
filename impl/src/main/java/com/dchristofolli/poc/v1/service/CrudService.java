package com.dchristofolli.poc.v1.service;

import com.dchristofolli.poc.v1.mapper.ImplMapper;
import com.dchristofolli.poc.v1.model.CrudModel;
import com.dchristofolli.poc.v1.repository.CrudEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import static com.dchristofolli.poc.v1.mapper.ImplMapper.mapModelToEntity;


@AllArgsConstructor
@NoArgsConstructor
@Service("crudService")
public class CrudService {
    private CrudRepository<CrudEntity, String> repository;

    public CrudModel create(CrudModel user) {
        return ImplMapper.mapEntityToModel(repository.save(mapModelToEntity(user)));
    }

//    public CrudModel findById(String id) {
//        return ImplMapper.mapEntityToModel(repository.findById(id));
//    }
//
//    public List<CrudEntity> findAll(){
//        return repository.findAll();
//    }
}

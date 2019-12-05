package com.dchristofolli.poc.v1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<CrudEntity, String> {
    CrudEntity findByCpf(String cpf);
    Optional<CrudEntity> findByName(String name);
    CrudEntity findByEmail(String email);
}

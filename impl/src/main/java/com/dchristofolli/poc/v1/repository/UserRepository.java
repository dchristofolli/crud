package com.dchristofolli.poc.v1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findById(String id);

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name);

    boolean existsByName(String name);
}

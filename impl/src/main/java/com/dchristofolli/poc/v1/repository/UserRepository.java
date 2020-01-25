package com.dchristofolli.poc.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findById(String id);

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name);

    boolean existsByName(String name);
}

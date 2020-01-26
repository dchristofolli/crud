package com.dchristofolli.poc.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findById(String id);

    Optional<UserEntity> findByName(String name);

    @Query("SELECT u FROM Users u WHERE u.id=?1 or u.cpf=?2 or u.email=?3 or u.name=?4")
    Optional<UserEntity> findByIdOrCpfOrEmailOrName(String id, String cpf, String email, String name);

    boolean existsByName(String name);
}

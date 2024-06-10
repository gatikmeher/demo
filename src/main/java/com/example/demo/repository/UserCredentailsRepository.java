package com.example.demo.repository;

import com.example.demo.mysql.model.UserCredential;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCredentailsRepository extends CrudRepository<UserCredential, Integer>{

    Optional<UserCredential> findByUserId(Long userId);
    Optional<UserCredential> findByUsername(String username);
}

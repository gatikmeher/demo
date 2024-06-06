package com.example.demo.repository;

import com.example.demo.mysql.model.UserCredential;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentailsRepository extends CrudRepository<UserCredential, Integer>{
}

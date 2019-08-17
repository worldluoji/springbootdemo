package com.springboot.logindemo.repository;

import com.springboot.logindemo.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface  UserRepository extends CrudRepository<User, Integer> {
    List<User> findByEmail(String email);
    void deleteByEmail(String email);
}
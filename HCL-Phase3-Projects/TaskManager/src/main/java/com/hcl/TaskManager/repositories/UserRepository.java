package com.hcl.TaskManager.repositories;


import org.springframework.data.repository.CrudRepository;

import com.hcl.TaskManager.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByName(String name);
    Optional<User> findByUserName(String email);
}
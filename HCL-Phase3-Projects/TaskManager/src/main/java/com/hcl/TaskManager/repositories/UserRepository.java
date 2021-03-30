package com.hcl.TaskManager.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.TaskManager.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByName(String name);
    Optional<User> findByUserName(String email);
}
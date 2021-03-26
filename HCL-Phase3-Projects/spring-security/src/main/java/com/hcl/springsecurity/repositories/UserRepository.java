package com.hcl.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.springsecurity.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String userName);
	boolean existsByUserName(String userName);

}

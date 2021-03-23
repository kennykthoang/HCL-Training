package com.example.Authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.entities.User;
import com.example.Authentication.exceptions.UserNotFoundException;
import com.example.Authentication.repositories.UserRepository;



@Service
public class UserService {
	
	@Autowired
	 private UserRepository userRepository;
	
	

    public Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getUserByName(String name)
    {
    	User foundUser = userRepository.findByName(name);
    	return foundUser;
    }

    public User getUserByEmail(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser;
    }
    
    public User getUserById(int id) {
    	Optional<User> foundUser = userRepository.findById(id);
    	
    	
    	//TODO: we need to decide how to handle a "Not Found" condition
    	
    	if (!foundUser.isPresent()) {
    		throw new UserNotFoundException();
    	}
    	
    	return(foundUser.get());
    }
    
    public void updateUser(User usertoUpdate) {
    	userRepository.save(usertoUpdate);
    }
    
    public boolean validateCredentials(String email, String password)
    {
    	User user = userRepository.findByEmail(email);
    	
    	if(user != null && user.getPassword().equals(password))
    		return true;
    	return false;
    }
    
    public boolean validateEmptyInput(String input)
	{
		if(input.isEmpty() || input.trim().isEmpty())
		{
			return false;
		}
		return true;
	}
}

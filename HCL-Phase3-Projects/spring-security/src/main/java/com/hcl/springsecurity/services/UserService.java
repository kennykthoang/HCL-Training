package com.hcl.springsecurity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.springsecurity.entities.User;
import com.hcl.springsecurity.repositories.UserRepository;



@Service
public class UserService {
	
	@Autowired
	 private UserRepository userRepository;

	

    public Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getUserByUserName(String username)
    {
    	Optional<User> foundUser = userRepository.findByUserName(username);
    	return foundUser.get();
    }
    
    public void updateUser(User usertoUpdate) {
    	userRepository.save(usertoUpdate);
    }
    
    public boolean validateCredentials(String username, String password)
    {
    	Optional<User> user = userRepository.findByUserName(username);
    	
    	if(user.get() != null && user.get().getPassword().equals(password))
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

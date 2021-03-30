package com.hcl.TaskManager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hcl.TaskManager.entities.User;
import com.hcl.TaskManager.repositories.UserRepository;



@Service
public class UserService {
	
	@Autowired
	 private UserRepository userRepository;

	

    public Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getUserByUserName(String username) throws UsernameNotFoundException
    {
    	Optional<User> foundUser = userRepository.findByUserName(username);
    	if(!foundUser.isPresent())
    		throw new UsernameNotFoundException("Username \"" + username + "\" was not found");
    	return (foundUser.get());
    }
    
    public boolean existsByUserName(String username)
    {
    	if(userRepository.findByUserName(username).isPresent())
    		return true;
    	return false;
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

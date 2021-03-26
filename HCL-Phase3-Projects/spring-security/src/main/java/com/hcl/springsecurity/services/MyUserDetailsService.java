package com.hcl.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.hcl.springsecurity.entities.MyUserDetails;
import com.hcl.springsecurity.entities.User;
import com.hcl.springsecurity.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByUserName(userName);
		
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found: + userName"));
		
		return user.map(MyUserDetails::new).get();
	}

}

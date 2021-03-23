package com.example.Authentication;


import com.example.Authentication.entities.User;
import com.example.Authentication.repositories.UserRepository;
import com.example.Authentication.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EntityTests {

	@Test
	public void whenSetPassword_CheckGetPassword() {
		User testUser = new User();
		
		testUser.setPassword("mypassword");
		assertEquals(testUser.getPassword(),"mypassword");
	}
	
	@Test
	public void whenSetEmail_CheckGetEmail() {
		User testUser = new User();
		
		testUser.setEmail("test@email.com");
		assertEquals(testUser.getEmail(),"test@email.com");
	}
	
	@Test
	public void whenSetName_CheckGetName() {
		User testUser = new User();
		
		testUser.setName("Name");
		assertEquals(testUser.getName(),"Name");
	}
	
	
	
}

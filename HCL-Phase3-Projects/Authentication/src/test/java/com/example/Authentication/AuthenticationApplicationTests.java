package com.example.Authentication;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.example.Authentication.entities.User;
import com.example.Authentication.exceptions.UserNotFoundException;
import com.example.Authentication.services.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AuthenticationApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DataSource database;
	
	private static boolean dataLoaded = false;
	
	@BeforeAll
	public void setup() throws SQLException
	{
		if(!dataLoaded)
		{
			try(Connection con = database.getConnection())
			{
				ScriptUtils.executeSqlScript(con, new ClassPathResource("/data.sql"));
				dataLoaded = true;
			}
		}
	}
	
	@AfterAll
	public void reset() throws SQLException
	{
		try(Connection con = database.getConnection())
		{
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/reset.sql"));
		}
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testServiceCall()
	{
		Iterable<User> users = userService.getAllUsers();
		
		Integer count = 0;
		for(User u : users)
		{
			count++;
		}
		assertNotEquals(count, 0);
	}
	
	@Test
	void testGetUserByName()
	{	
		User user = userService.getUserByName("test");
		assertEquals(user.getName(), "test");
	}
	
	@Test
	void testGetUserByEmail()
	{	
		User user = userService.getUserByEmail("test@email.com");
		assertEquals(user.getEmail(), "test@email.com");
	}
	
	@Test
	void testGetUserById()
	{	
		User user1 = userService.getUserById(1);
		assertEquals(user1.getId(), 1);
		assertThrows(UserNotFoundException.class, () -> {
			User user2 = userService.getUserById(0);
		});
	}

	@Test
	void testUpdateUser()
	{
		User user = new User();
		user.setName("Unique");
		user.setEmail("unique@email.com");
		user.setPassword("unique");
		
		userService.updateUser(user);
		
		User found = userService.getUserByName("Unique");
		assertEquals(found.getName(), user.getName());
	}
	
	@Test
	void testValidateCredentials()
	{
		boolean test1 = userService.validateCredentials("test@email.com", "testpassword");
		boolean test2 = userService.validateCredentials("test@email.com", "TESTPASSWORD");
		boolean test3 = userService.validateCredentials("nonexistent@email.com", "");
		
		assertTrue(test1);
		assertFalse(test2);
		assertFalse(test3);
		
	}
	
	@Test
	void testValidateEmptyInput()
	{
		boolean test1 = userService.validateEmptyInput("  ");
		boolean test2 = userService.validateEmptyInput("empty");
		boolean test3 = userService.validateEmptyInput("");
		assertFalse(test1);
		assertTrue(test2);
		assertFalse(test3);
	}
}

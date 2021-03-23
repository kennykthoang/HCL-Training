package com.example.Authentication;


import com.example.Authentication.entities.User;
import com.example.Authentication.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;




@DataJpaTest

public class AuthenticationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindAll_thenReturnAll()
    {
    	User dummyUser1 = new User();
        dummyUser1.setName("Dummy");
        dummyUser1.setEmail("test@test.com");
        dummyUser1.setPassword("password");
        
        User dummyUser2 = new User();
        dummyUser2.setName("Dummy2");
        dummyUser2.setEmail("test2@test.com");
        dummyUser2.setPassword("password2");
        
        User dummyUser3 = new User();
        dummyUser3.setName("Dummy3");
        dummyUser3.setEmail("test3@test.com");
        dummyUser3.setPassword("password3");
        
        entityManager.persist(dummyUser1);
        entityManager.persist(dummyUser2);
        entityManager.persist(dummyUser3);
        entityManager.flush();
        
    	Iterable<User> users = userRepository.findAll();
    	
    	Integer count = 0;
		for(User u : users)
		{
			count++;
		}
		assertNotEquals(users, 0);
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        // given

        User dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        entityManager.persist(dummyUser);
        entityManager.flush();

        // when
        User found = userRepository.findByName(dummyUser.getName());

        // then

        assertEquals(found.getName(), dummyUser.getName());
    }
    
    @Test
    public void whenFindByEmail_thenReturnUser()
    {
    	// given
    	
    	User dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        entityManager.persist(dummyUser);
        entityManager.flush();
        
        // when 
        User found = userRepository.findByEmail(dummyUser.getEmail());
        
        // then
        
        assertEquals(found.getEmail(), dummyUser.getEmail());
    }
    
    @Test
    public void whenFindById_thenReturnUser()
    {
    	// given
    	
    	User dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        entityManager.persist(dummyUser);
        entityManager.flush();
        
        // when 
        Optional<User> found = userRepository.findById(dummyUser.getId());
        
        // then
        
        assertNotNull(found);
    }
    
    @Test
    public void whenGivenUser_thenAddToDatabase()
    {
    	// given
    	
    	User dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setEmail("test@test.com");
        dummyUser.setPassword("password");
        entityManager.persist(dummyUser);
        entityManager.flush();
        
        // when
        
        userRepository.save(dummyUser);
        
        // then (check if dummyUser is in database)
        
        User found = userRepository.findByName("Dummy");
        assertNotNull(found);
    }
}

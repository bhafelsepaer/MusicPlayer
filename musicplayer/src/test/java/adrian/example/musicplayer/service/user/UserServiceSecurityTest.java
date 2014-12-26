package adrian.example.musicplayer.service.user;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;


import adrian.example.musicplayer.dao.user.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@Transactional
@TransactionConfiguration
public class UserServiceSecurityTest {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired 
	UserServiceSecurity userServiceSecurity;
	
	@Before
	public void setUp() throws Exception {}

	@Test
	public void loadUserByUsername() throws Exception {
		org.springframework.security.core.userdetails.User userTest = 
				(User) userServiceSecurity.loadUserByUsername("TestLogin");
		
		assertEquals("Test Authority" ,"ROLE_USER",
				userTest.getAuthorities().iterator().next().toString());
		assertEquals("Test Login", "TestLogin", userTest.getUsername());
		assertEquals("Test Password", 
				     "$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK", 
				     userTest.getPassword());
	}
}

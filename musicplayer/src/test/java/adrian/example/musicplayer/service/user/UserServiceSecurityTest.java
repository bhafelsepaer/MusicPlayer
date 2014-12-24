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
	UserDao userDao;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired 
	UserServiceSecurity userServiceSecurity;
	
	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
	
	@Before
	public void setUp() throws Exception {
		user.setActive_cod(12345);
		user.setAddress("KremnizerWeg");
		user.setEmail("janekgabka@gmail.com");
		user.setEnabled(true);
		user.setFirstName("Kitajec");
		user.setLogin("Kamil");
		user.setPassword("qwertY12#");
		
		userServiceImpl.saveUser(user);
	}

	@Test
	public void loadUserByUsername() throws Exception {
		
		org.springframework.security.core.userdetails.User userTest = (User) userServiceSecurity.loadUserByUsername("Kamil");
		
		assertEquals("Test Authority" ,user.getRoleUser().iterator().next().getAuthority().toString(),
				userTest.getAuthorities().iterator().next().toString());
		assertEquals("Test Login", user.getLogin() ,userTest.getUsername());
		assertEquals("Test Password", user.getPassword(), userTest.getPassword());
	}
}

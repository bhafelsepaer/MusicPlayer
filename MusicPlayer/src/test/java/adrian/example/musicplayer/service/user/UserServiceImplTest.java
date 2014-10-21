package adrian.example.musicplayer.service.user;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import adrian.example.musicplayer.dao.user.UserDao;
import adrian.example.musicplayer.model.User.UserInformation;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class UserServiceImplTest {
	
	@Autowired
	UserDao userDao;
	
	@Autowired 
	UserServiceImpl userServiceImpl;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
    adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
	
	UserInformation userInformation = new UserInformation();
	
	private String login = "Armani";
	
	@Before
	public void setUp() throws Exception {
		user.setActive_cod(123456);
		user.setAddress("KrepnizerWeg");
		user.setEmail("Mail@gmail.com");
		user.setEnabled(false);
		user.setFirstName("Armani");
		user.setLogin(login);
		user.setPassword("$2a$10$/y3BTr9MlLNXvQ.vs8H2buLHbAcbnx.JOnXLBUA.kJ0efP3TS6Riq");
		
		userInformation.setAge(23);
		userInformation.setInterest("Football");
		userInformation.setProgrammingSkill("JAVA");
		userInformation.setSex("K");
		userInformation.setSurname("Ferderk");
		userInformation.setUser(user);
		
		user.setUserInformation(userInformation);
		
		userDao.saveUser(user);
	}

	@Test
	public void findLogin() throws Exception {
		 adrian.example.musicplayer.model.User.User user2 = userServiceImpl.findLogin(login);
		 assertEquals(user2.getAddress(), user.getAddress());
		 assertEquals(user2.getActive_cod(), user.getActive_cod());
		 assertEquals(user2.getEmail(), user.getEmail());
		 assertEquals(user2.getFirstName(), user.getFirstName());
		 assertEquals(user2.getLogin(), user.getLogin());
		 assertEquals(user2.getPassword(), user.getPassword());
		 assertEquals(user2.getUser_id(), user.getUser_id());
	}
	
	@Test
	public void saveUser() throws Exception {
		adrian.example.musicplayer.model.User.User user2 = new adrian.example.musicplayer.model.User.User();
		user2.setActive_cod(543463);
		user2.setAddress("Vegas");
		user2.setEmail("Maradona@gmail.com");
		user2.setEnabled(false);
		user2.setFirstName("Chikita");
		user2.setLogin("Kazik666Cebulak");
		user2.setPassword("qwertY12#");
		
		userServiceImpl.saveUser(user2);
		
		assertEquals(543463, user2.getActive_cod());
		assertEquals("Vegas", user2.getAddress());
		assertEquals("Maradona@gmail.com", user2.getEmail());
		assertFalse(user2.isEnabled());
		assertEquals("Chikita", user2.getFirstName());
		assertEquals("Kazik666Cebulak", user2.getLogin());
	    assertTrue(passwordEncoder.matches("qwertY12#", user2.getPassword()));
	}

}

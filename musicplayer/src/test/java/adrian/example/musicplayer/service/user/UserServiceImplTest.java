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
import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.model.User.UserInformation;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class UserServiceImplTest {
	
	@Autowired 
	UserServiceImpl userServiceImpl;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Before
	public void setUp() throws Exception {}

	@Test
	public void findLogin() throws Exception {
        User user = this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("Tested User id", 1, user.getUser_id());
		assertEquals("Tested User Login", "TestLogin", user.getLogin());
		assertEquals("Tested User Password", 
				"$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK",
				user.getPassword());
		assertEquals("Tested User FirstName", "TestFirstName", user.getFirstName());
		assertEquals("Tested User Address", "TestAddress", user.getAddress());
		assertEquals("Tested User ActiveCode", 12345, user.getActive_cod());
	}
	
	@Test
	public void saveUser() throws Exception {
		User userToSave = new User();
		userToSave.setLogin("User2");
		userToSave.setActive_cod(54321);
		userToSave.setAddress("AddressSave");
		userToSave.setEmail("SavedEmail@Test.com");
		userToSave.setEnabled(false);
		userToSave.setFirstName("SavedName");
		userToSave.setPassword("password12345@");
		
		this.userServiceImpl.saveUser(userToSave);
        
		User loadedUser = (User) this.userServiceImpl.findLogin("User2");
		
		assertEquals("Tested User id", 2, loadedUser.getUser_id());
		assertEquals("Tested User Login", "User2", loadedUser.getLogin());
		assertTrue("Tested User Password", 
				passwordEncoder.matches("password12345@", loadedUser.getPassword()));
		assertEquals("Tested User FirstName", "SavedName", loadedUser.getFirstName());
		assertEquals("Tested User Address", "AddressSave", loadedUser.getAddress());
		assertEquals("Tested User ActiveCode", 54321, loadedUser.getActive_cod());
	}

}

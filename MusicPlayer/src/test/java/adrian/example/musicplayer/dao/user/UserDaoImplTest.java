package adrian.example.musicplayer.dao.user;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import adrian.example.musicplayer.model.User.RoleUser;
import adrian.example.musicplayer.model.User.UserInformation;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TransactionConfiguration
@Transactional
public class UserDaoImplTest {

	@Autowired
	UserDao userDaoImpl;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
	UserInformation userInformation = new UserInformation();
	
	@Before
	public void setUp() throws Exception {
		user.setActive_cod(123456);
		user.setAddress("KremnizerWeg");
		user.setConfirmEmail("email@gmail.com");
		user.setConfirmPassword("zxcvbnm");
		user.setEmail("email@gmail.com");
		user.setEnabled(false);
		user.setFirstName("Adam");
		user.setLogin("Adam");
		user.setPassword("$2a$10$/y3BTr9MlLNXvQ.vs8H2buLHbAcbnx.JOnXLBUA.kJ0efP3TS6Riq");
		
		userInformation.setAge(23);
		userInformation.setInterest("Music");
		userInformation.setProgrammingSkill("Java");
		userInformation.setSex("M");
		userInformation.setSurname("Kitajec");
		
		userInformation.setUser(user);
		user.setUserInformation(userInformation);
		
		userDaoImpl.saveUser(user);
		
	}
	
	@Test
	public void saveUser() throws Exception {
		assertEquals(123456, user.getActive_cod());
		assertEquals("KremnizerWeg", user.getAddress());
		assertEquals("email@gmail.com", user.getEmail());
		assertEquals("$2a$10$/y3BTr9MlLNXvQ.vs8H2buLHbAcbnx.JOnXLBUA.kJ0efP3TS6Riq", user.getPassword());
		assertEquals(false, user.isEnabled());
		assertEquals("Adam", user.getFirstName());
		assertEquals("email@gmail.com", user.getEmail());
	}
	
	@Test
	public void getUser_id() throws Exception {
		int id = userDaoImpl.getUser_id(user.getLogin());
		assertEquals(id, user.getUser_id());
	}
	
	@Test
	public void findLogin() throws Exception {
		adrian.example.musicplayer.model.User.User user2 = userDaoImpl.findLogin(user.getLogin());
		assertEquals(user2.getLogin(), user.getLogin());
		assertEquals(user2.getPassword(), user.getPassword());
		assertEquals(user2.getUser_id(), user.getUser_id());
		assertEquals(user2.getFirstName(), user.getFirstName());
	}
	
	@Test
	public void findLoginNull() throws Exception {
		assertNull(userDaoImpl.findLogin("Janek"));
	}
	
	@Test
	public void findUserById() throws Exception {
		adrian.example.musicplayer.model.User.User user3 = userDaoImpl.findUserById(user.getUser_id());
		assertEquals(user3.getLogin(), user.getLogin());
		assertEquals(user3.getPassword(), user.getPassword());
		assertEquals(user3.getUser_id(), user.getUser_id());
		assertEquals(user3.getFirstName(), user.getFirstName());
	}
	
	@Test
	public void getUserInformationByIdExist() throws Exception {
		UserInformation userInformation2 = userDaoImpl.getUserInformationById(user.getUser_id());
		assertEquals(userInformation2.getAge(), userInformation.getAge());
		assertEquals(userInformation2.getInterest(), userInformation.getInterest());
		assertEquals(userInformation2.getSex(), userInformation.getSex());
		assertEquals(userInformation2.getUser_information_id(), userInformation.getUser_information_id());
	}
	
	@Test
	public void getUserInformationByIdNewUserInformation() throws Exception {
		UserInformation userInformation3 = userDaoImpl.getUserInformationById(5000);
		assertNull(userInformation3.getInterest());
		assertNull(userInformation3.getProgrammingSkill());
		assertNull(userInformation3.getSex());
		assertNull(userInformation3.getSurname());
		assertEquals(0, userInformation3.getAge());
	}
	
	@Test
	public void updateUserInformation() throws Exception {
		userDaoImpl.updateUserInformation(user.getUser_id(), "Wisla", "Aniki");
		assertEquals("Wisla", user.getAddress());
		assertEquals("Aniki", user.getFirstName());
	}
	
	@Test
	public void updatePassword() throws Exception {
		userDaoImpl.updatePassword(user.getUser_id(), "qwertY1@");
		System.out.println(user.getPassword());
		assertTrue(bcryptEncoder.matches("qwertY1@",user.getPassword()));
	}
	
	@Test
	public void updateEmail() throws Exception {
		userDaoImpl.updateEmail(user.getUser_id(), "NowyMail@gmail.com");
		assertEquals("NowyMail@gmail.com", user.getEmail());
	}
	
	@Test
	public void updateOrSaveInformation() throws Exception {
		UserInformation userInformation2 = new UserInformation();
		userInformation2.setAge(44);
		userInformation2.setInterest("SEX");
		userInformation2.setProgrammingSkill("Hibernate");
		userInformation2.setSex("K");
		userInformation2.setSurname("Stefania");
		
		userDaoImpl.updateOrSaveUserInformation(user.getUser_id(), userInformation2);
		
		assertEquals(userInformation.getAge(), userInformation2.getAge());
		assertEquals(userInformation.getInterest(), userInformation2.getInterest());
		assertEquals(userInformation.getProgrammingSkill(), userInformation2.getProgrammingSkill());
		assertEquals(userInformation.getSex(), userInformation2.getSex());
		assertEquals(userInformation.getSurname(), userInformation2.getSurname());
	}
  
  @Test
  public void checkPassword() throws Exception {
	  assertTrue(userDaoImpl.checkPassword(user.getUser_id(), "qwertY12#"));
  }
  
  @Test
  public void checkUserVerify() throws Exception {
	  assertTrue(userDaoImpl.checkUserVerify(user.getUser_id(), user.getActive_cod()));
  }
	
  public void setEnebledTrue() throws Exception {
	  userDaoImpl.setEnabledTrue(user.getUser_id());
	  assertTrue(user.isEnabled());
  }
}

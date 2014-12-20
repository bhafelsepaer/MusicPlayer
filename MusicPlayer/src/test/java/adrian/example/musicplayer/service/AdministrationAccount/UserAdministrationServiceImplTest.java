package adrian.example.musicplayer.service.AdministrationAccount;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@TransactionConfiguration
@Transactional
public class UserAdministrationServiceImplTest {

	@Autowired 
	UserAdministrationServiceImpl userServiceImpl;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	 BCryptPasswordEncoder bcryptEncoder;
	
	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
	
	UserInformation userInformation = new UserInformation();
	
	@Before
	public void setUp() throws Exception {
		user.setActive_cod(123456);
		user.setAddress("KrepnizerWeg");
		user.setEmail("Mail@gmail.com");
		user.setEnabled(false);
		user.setFirstName("Armani");
		user.setLogin("Armani");
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
	public void getUser_id() {
		assertEquals(user.getUser_id(), userServiceImpl.getUser_id("Armani"));
	}
	
	@Test 
	public void getUserInformationById() throws Exception {
		UserInformation userInformationTest = userServiceImpl.getUserInformationById(user.getUser_id());
		
		assertEquals("Test Age", userInformation.getAge(), userInformationTest.getAge());
		assertEquals("Test Interest", userInformation.getInterest(), userInformationTest.getInterest());
		assertEquals("Test Programming Skill", userInformation.getProgrammingSkill(), userInformationTest.getProgrammingSkill());
		assertEquals("Test Sex", userInformation.getSex(), userInformationTest.getSex());
		assertEquals("Test Surname", userInformation.getSurname(), userInformationTest.getSurname());
		assertEquals("Test User", userInformation.getUser(), userInformationTest.getUser());
		assertEquals("Test id", userInformation.getUser_information_id(), userInformationTest.getUser_information_id());
	}
	
	@Test
	public void findLogin() throws Exception {
		adrian.example.musicplayer.model.User.User userTest = userServiceImpl.findLogin("Armani");
		
		assertEquals("Test Login", user.getLogin(), userTest.getLogin());
		assertEquals("Test id", user.getUser_id(), userTest.getUser_id());
		assertEquals("Test UserInformation", user.getUserInformation(), userTest.getUserInformation());
	}
	
	@Test
	public void updateUserInformationSucced() throws Exception {
		userServiceImpl.updateUserInformation(user.getUser_id(), "New York", "Kruger");
		assertEquals("Test Address", "New York", user.getAddress());
		assertEquals("Test FirstName", "Kruger", user.getFirstName());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void updateUserInformationFail() throws ObjectNotFoundException {
		userServiceImpl.updateUserInformation(4000, "New York", "Kruger");
	}
	
	@Test
	public void updatePasswordSucced() throws Exception {
		userServiceImpl.updatePassword(user.getUser_id(), "asdfG12#");
		assertTrue("Test Password", bcryptEncoder.matches("asdfG12#",user.getPassword()));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void updatePasswordFail() throws ObjectNotFoundException {
		userServiceImpl.updatePassword(3000, "asdfG12#");
	}
	
	@Test
	public void updateEmailSucced() throws Exception {
		userServiceImpl.updateEmail(user.getUser_id(), "NewMail@gmail.com");
		assertEquals("Test Update Mail", "NewMail@gmail.com", user.getEmail());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void updateEmailFail() throws ObjectNotFoundException {
		userServiceImpl.updateEmail(5000, "NewMail@gmail.com");
	}
	
	@Test
	public void updateOrSaveUserInformationSucced() throws Exception {
		UserInformation userInformation = new UserInformation();
		userInformation.setAge(55);
		userInformation.setInterest("Eat");
		userInformation.setProgrammingSkill("HTML");
		userInformation.setSex("K");
		userInformation.setSurname("Kazik");
		
		userServiceImpl.updateOrSaveUserInformation(user.getUser_id(), userInformation);
		assertEquals("Test Age", 55, userInformation.getAge());
		assertEquals("Test Interest", "Eat", userInformation.getInterest());
		assertEquals("Test Programming Skill", "HTML", userInformation.getProgrammingSkill());
		assertEquals("Test Sex", "K", userInformation.getSex());
		assertEquals("Test Surname", "Kazik", userInformation.getSurname());
	}
	
	@Test(expected = java.lang.NullPointerException.class)
	public void updateOrSaveUserInformationFail() {
		UserInformation userInformationTest = new UserInformation();
		userInformationTest.setAge(55);
		userInformationTest.setInterest("Eat");
		userInformationTest.setProgrammingSkill("HTML");
		userInformationTest.setSex("K");
		userInformationTest.setSurname("Kazik");
		
		userServiceImpl.updateOrSaveUserInformation(1000, userInformationTest);
		
	}
	
	@Test
	public void checkUserVerifySucced() throws Exception {
		assertTrue("Test Verify", userServiceImpl.checkUserVerify(user.getUser_id(), 123456));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void checkUserVerifyFail() throws ObjectNotFoundException {
		userServiceImpl.checkUserVerify(5000, 123456);
	}
	
	@Test
	public void checkPassword() throws Exception {
		assertTrue("Test Password", userServiceImpl.checkPassword(user.getUser_id(), "qwertY12#"));
	}
	
	@Test
	public void setEnabledTrue() throws Exception {
		userServiceImpl.setEnabledTrue(user.getUser_id());
		assertTrue(user.isEnabled());
	}
	
}

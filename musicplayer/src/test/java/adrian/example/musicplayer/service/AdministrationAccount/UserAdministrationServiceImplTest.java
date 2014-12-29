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

import adrian.example.musicplayer.model.User.User;
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
	 BCryptPasswordEncoder bcryptEncoder;
	
	@Before
	public void setUp() throws Exception {}

	@Test
	public void getUser_id() {
		int user_id =  this.userServiceImpl.getUser_id("TestLogin");
		assertEquals("Test User id", 1, user_id);
	}
	
	@Test 
	public void getUserInformationById() throws Exception {
       UserInformation userInformation = this.userServiceImpl.getUserInformationById(1);
		
	    assertEquals("Tested UserInformation id", 1, userInformation.getUser_information_id());
		assertEquals("Tested UserInformation Age", 23, userInformation.getAge());
		assertEquals("Tested UserInformation Interest", "Football, Video Game, Travel", 
				userInformation.getInterest());
		assertEquals("Tested UserInformation Sex" ,"yes", userInformation.getSex());
		assertEquals("Tested UserInformation Programming Skill", 
				 "JAVA, JAVAEE", userInformation.getProgrammingSkill());
	}
	
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
	public void updateUserInformationSucced() throws Exception {
       User userBeforeUpdate = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("Test user Address Before Update", "TestAddress",
				userBeforeUpdate.getAddress());
		assertEquals("Test FirstName Before Update", "TestFirstName", 
				userBeforeUpdate.getFirstName());
		
		this.userServiceImpl.updateUserInformation(1, "Wisla", "Aniki");
		
		User userAfterUpdate = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("Tested Address", "Wisla", userAfterUpdate.getAddress());
		assertEquals("Tested FirstName", "Aniki", userAfterUpdate.getFirstName());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void updateUserInformationFail() throws ObjectNotFoundException {
		userServiceImpl.updateUserInformation(4000, "New York", "Kruger");
	}
	
	@Test
	public void updatePasswordSucced() throws Exception {
        User userBeforeUpdate = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("Tested Password Before Update", 
				"$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK",
				userBeforeUpdate.getPassword());
		
		this.userServiceImpl.updatePassword(userBeforeUpdate.getUser_id(), "qwertY1@");
		
		User userAfterUpdate = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertTrue("Tested Password After Update", bcryptEncoder.matches("qwertY1@",
				userAfterUpdate.getPassword()));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void updatePasswordFail() throws ObjectNotFoundException {
		userServiceImpl.updatePassword(3000, "asdfG12#");
	}
	
	@Test
	public void updateEmailSucced() throws Exception {
        User userBeforeUpdate = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("Tested user email before update", "TestEmail@Test.com",
				userBeforeUpdate.getEmail());
		
		this.userServiceImpl.updateEmail(userBeforeUpdate.getUser_id(), "NowyMail@gmail.com");
		
		User userAfterUpdate = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("tested user email after update", "NowyMail@gmail.com", 
				userAfterUpdate.getEmail());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void updateEmailFail() throws ObjectNotFoundException {
		userServiceImpl.updateEmail(5000, "NewMail@gmail.com");
	}
	
	@Test
	public void updateOrSaveUserInformationSucced() throws Exception {
	User user = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("Tested user id", 1, user.getUser_id());
		assertEquals("Tested UserInformation id", 1, 
				user.getUserInformation().getUser_information_id());
		assertEquals("Tested UserInformation age", 23, user.getUserInformation().getAge());
		assertEquals("Tested UserInformation interest", "Football, Video Game, Travel", 
				user.getUserInformation().getInterest());
		assertEquals("Tested UserInformation ProgrammingSKill", "JAVA, JAVAEE", 
				user.getUserInformation().getProgrammingSkill());
		assertEquals("Tested UserInformation sex", "yes", user.getUserInformation().getSex());
		assertEquals("Tested UserInformation Surname", "TestSurname", 
				user.getUserInformation().getSurname());
		
		UserInformation userInformation2 = new UserInformation();
		userInformation2.setAge(44);
		userInformation2.setInterest("Game, Tennis");
		userInformation2.setProgrammingSkill("Hibernate, JPA");
		userInformation2.setSex("no");
		userInformation2.setSurname("Czesław");
		
		
		this.userServiceImpl.updateOrSaveUserInformation(user.getUser_id(), userInformation2);
		
        User userAfterUpdate = (User) this.userServiceImpl.findLogin("TestLogin");
		
		assertEquals("Tested user id", 1, userAfterUpdate.getUser_id());
		assertEquals("Tested UserInformation id", 1, 
				userAfterUpdate.getUserInformation().getUser_information_id());
		assertEquals("Tested UserInformation age", 44, userAfterUpdate.getUserInformation().getAge());
		assertEquals("Tested UserInformation interest", "Game, Tennis", 
				userAfterUpdate.getUserInformation().getInterest());
		assertEquals("Tested UserInformation ProgrammingSKill", "Hibernate, JPA", 
				userAfterUpdate.getUserInformation().getProgrammingSkill());
		assertEquals("Tested UserInformation sex", "no", userAfterUpdate.getUserInformation().getSex());
		assertEquals("Tested UserInformation Surname", "Czesław", 
				userAfterUpdate.getUserInformation().getSurname());  
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
		 User user = (User) this.userServiceImpl.findLogin("TestLogin");
		  assertTrue("Test User verify active code",
				  this.userServiceImpl.checkUserVerify(user.getUser_id(), 12345));
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void checkUserVerifyFail() throws ObjectNotFoundException {
		userServiceImpl.checkUserVerify(5000, 123456);
	}
	
	@Test
	public void checkPassword() throws Exception {
		  User user = (User) this.userServiceImpl.findLogin("TestLogin");
		  assertTrue("Tested Password", this.userServiceImpl.checkPassword(user.getUser_id(), 
				      "TestPassword12345@"));
	}
	
	@Test
	public void setEnabledTrue() throws Exception {
		  User userBeforeUpdateEnabled = (User) this.userServiceImpl.findLogin("TestLogin");
		  assertFalse("Tested Enabled before update", userBeforeUpdateEnabled.isEnabled());
		  
		  this.userServiceImpl.setEnabledTrue(userBeforeUpdateEnabled.getUser_id());
		  
		  User userAfterUpdateEnabled = (User) this.userServiceImpl.findLogin("TestLogin");
		  assertTrue("Tested Enabled After Update ", userAfterUpdateEnabled.isEnabled());
	}
	
}

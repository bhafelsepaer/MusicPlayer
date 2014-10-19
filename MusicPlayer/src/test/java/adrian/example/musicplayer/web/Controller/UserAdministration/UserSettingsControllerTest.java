package adrian.example.musicplayer.web.Controller.UserAdministration;

import java.security.Principal;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import adrian.example.musicplayer.model.User.UserInformation;
import adrian.example.musicplayer.service.UserInformationServiceList;
import adrian.example.musicplayer.service.AdministrationAccount.UserAdministrationService;
import adrian.example.musicplayer.service.user.UserServiceImpl;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TransactionConfiguration
@Transactional
public class UserSettingsControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	UserAdministrationService userAdministrationService;
	
	@Autowired
	UserServiceImpl userServiceImplSettings;
	
	@Autowired
	UserInformationServiceList userInformationServiceList;
	

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void activeUserFromEmailUserNotFound () throws Exception {
		
		adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
		user.setLogin("Adrian");
		user.setPassword("qwerty1@A");
		user.setEmail("MY EMAIL");
		user.setActive_cod(12345);
		
		userServiceImplSettings.saveUser(user);
		
		this.mockMvc.perform(get("/verify")
	            	.param("user_id", "" + user.getUser_id())
	            	.param("active_code", "123456")
	            	.sessionAttr("user", new adrian.example.musicplayer.model.User.User()))
		            .andExpect(redirectedUrl("/error403"))
		            .andExpect(status().is3xxRedirection());
	}

	@Test
	public void activeUserFromEmailUserFound() throws Exception {
		
		adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
		user.setLogin("Adrian");
		user.setPassword("qwerty1@A");
		user.setActive_cod(12345);
		
		userServiceImplSettings.saveUser(user);
		
		this.mockMvc.perform(get("/verify")
	            	.param("user_id", "" + user.getUser_id())
	            	.param("active_code","12345")
	            	.sessionAttr("user", new adrian.example.musicplayer.model.User.User()))
		            .andExpect(redirectedUrl("/"))
		            .andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void settingsUserHomePageLoginAndPrincipalNotMatch() throws Exception {
		
		adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
		user.setLogin("Adrian");
		
		User userSpring = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
		this.mockMvc.perform(get("/settings_account_user/Adrian/profile")
				             .principal(authentication))
				             .andExpect(redirectedUrl("/error403"))
				             .andExpect(status().is3xxRedirection());
	}

    @Test
    public void settingsUserHomePageLoginAndPrincipalMatch() throws Exception {
    	
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
		user.setLogin("Adrian");
		user.setPassword("qwerty1@A");
		user.setActive_cod(12345);
		
		userServiceImplSettings.saveUser(user);
		
		User userSpring = new User("Adrian", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
		this.mockMvc.perform(get("/settings_account_user/Adrian/profile")
	             .principal(authentication))
	             .andExpect(view().name("user_setting/user_settingProfil"))
	             .andExpect(status().isOk())
	             .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settingProfil.jsp"));
    }
    
    @Test
    public void settingsUserProfilePageHasError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("qwerty0");
    	user.setActive_cod(12345);
    	user.setAddress("Krampnizer Weg 12/13");
    	user.setFirstName("Adrian");
    	
    	userServiceImplSettings.saveUser(user);
    	
    	this.mockMvc.perform(post("/settings_account_user/Adrian/profile")
    			     .param("login", "Adrian")
    			     .param("password", "qwerty0")
    			     .param("active_cod", "12345")
    			     .param("address", "")
    			     .param("firstName", "Adrian")
    			     .sessionAttr("user", user))
    			     .andExpect(view().name("user_setting/user_settingProfil"))
    			     .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settingProfil.jsp"))
    			     .andExpect(status().isOk());
    }
    
    @Test
    public void  settingsUserProfilePageHasNoError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("qwerty0");
    	user.setActive_cod(12345);
    	user.setAddress("Krampnizer Weg 12/13");
    	user.setFirstName("Adrian");
    	
    	this.mockMvc.perform(post("/settings_account_user/Adrian/profile")
			     .param("login", "Adrian")
			     .param("password", "qwerty0")
			     .param("active_cod", "12345")
			     .param("address", "Nowy Adres")
			     .param("firstName", "firstName")
			     .sessionAttr("user", user))
			     .andExpect(redirectedUrl("/settings_account_user/" + user.getLogin() + "/profile"))
			     .andExpect(status().is3xxRedirection());
    	
    	assertEquals(user.getAddress(), "Nowy Adres");
    	assertEquals(user.getFirstName(), "firstName");
    }
    
    @Test
    public void settingUserPasswordGetPrincipalNotEqualsLogin() throws Exception{
    	
    	User userSpring = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
		adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("qwerty0");
    	user.setActive_cod(12345);
    	user.setAddress("Krampnizer Weg 12/13");
    	user.setFirstName("Adrian");
    	
    	this.mockMvc.perform(get("/settings_account_user/Adrian/password")
    			    .principal(authentication))
    	            .andExpect(redirectedUrl("/error403"))
    	            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void settingUserPasswordGetPrincipalEqualsLogin() throws Exception {
    	User userSpring = new User("Adrian", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
		adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("qwerty0");
    	user.setActive_cod(12345);
    	user.setAddress("Krampnizer Weg 12/13");
    	user.setFirstName("Adrian");
    	
    	userServiceImplSettings.saveUser(user);
    	
    	this.mockMvc.perform(get("/settings_account_user/Adrian/password")
    			    .principal(authentication))
    			    .andExpect(view().name("user_setting/user_settignPassword"))
    			    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settignPassword.jsp"))
    			    .andExpect(model().attributeExists("user"))
    			    .andExpect(status().isOk());
    }
    
    @Test
    public void settingUserPasswordHasError() throws Exception {
		adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("qwerty0");
    	user.setActive_cod(12345);
    	user.setAddress("Krampnizer Weg 12/13");
    	user.setFirstName("Adrian");
    	
        userServiceImplSettings.saveUser(user);
    	
    	this.mockMvc.perform(post("/settings_account_user/Adrian/password")
    			    .param("user_password", "errorpassword")
    			    .sessionAttr("user", user))
    			    .andExpect(view().name("user_setting/user_settignPassword"))
    			    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settignPassword.jsp"))
    			    .andExpect(model().attributeExists("wrongCurrentPassword"))
    			    .andExpect(status().isOk());
    }
    
    @Test
    public void settingUserPasswordNoError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("$2a$10$27EDo8MqErgfPgkTHOgTgud3gmdSgRdi.aWWP5Wav.9tR9dkRbPta");
    	user.setActive_cod(12345);
    	user.setAddress("Krampnizer Weg 12/13");
    	user.setFirstName("Adrian");
    	
        userServiceImplSettings.saveUser(user);
    	
    	this.mockMvc.perform(post("/settings_account_user/Adrian/password")
    			    .param("user_password", "zxcvbnM123$")
    			    .param("password", "zxcvbnM123$")
    			    .param("confirmPassword", "zxcvbnM123$")
    			    .param("user_id", "" + user.getUser_id())
    			    .sessionAttr("user", user))
    			    .andExpect(redirectedUrl("/settings_account_user/" + user.getLogin() +  "/password"))
    			    .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void settingUserEmailGETPrincipalAndLoginNotMatch() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("zxcvbnM123$");
    	user.setActive_cod(12345);
    	
    	userServiceImplSettings.saveUser(user);
    	
    	
      	User userSpring = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
    		
    	this.mockMvc.perform(get("/settings_account_user/Adrian/email")
    			    .principal(authentication))
    	            .andExpect(redirectedUrl("/error403"))
    	            .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void settingUserEmailGetPrincipalAndLoginMatch() throws Exception{
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("zxcvbnM123$");
    	user.setActive_cod(12345);
    	
    	userServiceImplSettings.saveUser(user);
    	
    	
      	User userSpring = new User("Adrian", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
        
        this.mockMvc.perform(get("/settings_account_user/Adrian/email")
        		    .principal(authentication))
        		    .andExpect(model().attributeExists("user"))
        		    .andExpect(view().name("user_setting/user_settingEmail"))
        		    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settingEmail.jsp"))
        		    .andExpect(status().isOk());
    }
    
    @Test
    public void settingUserEmailPostHasError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("zxcvbnM123$");
    	user.setActive_cod(12345);
    	user.setEmail("123@gmail.com");
    	
    	userServiceImplSettings.saveUser(user);
    	
    	this.mockMvc.perform(post("/settings_account_user/Adrian/email")
    			    .param("email", "123sad")
    			    .sessionAttr("user", user))
    			    .andExpect(view().name("user_setting/user_settingEmail"))
    			    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settingEmail.jsp"));
    }
    
    @Test
    public void settingsUserEmailPostNoError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("zxcvbnM123$");
    	user.setActive_cod(12345);
    	user.setEmail("123@gmail.com");
    	
    	userServiceImplSettings.saveUser(user);
    	
    	User userSpring = new User("Adrian", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
    	
    	this.mockMvc.perform(post("/settings_account_user/Adrian/email")
			    .param("email", "Adrian12@gmail.com")
			    .param("confirmEmail", "Adrian12@gmail.com")
			    .param("password", "zxcvbnM123$")
			    .sessionAttr("user", user)
			    .principal(authentication))
			    .andExpect(redirectedUrl("redirect:/settings_account_user/" + authentication.getName() + "/userInformation"))
			    .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void settingUserInformationGETPrincipalAndLoginNotMatch() throws Exception {
    	User userSpring = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
        
        this.mockMvc.perform(get("/settings_account_user/Adrian/userInformation")
        		    .principal(authentication))
        		    .andExpect(redirectedUrl("/error403"))
        		    .andExpect(status().is3xxRedirection()); 
    }
    
    @Test
    public void settingUserInformationGetPrincipalAndLoginMatch() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("zxcvbnM123$");
    	user.setActive_cod(12345);
    	user.setEmail("123@gmail.com");
    	
    	userServiceImplSettings.saveUser(user);
    	
    	User userSpring = new User("Adrian", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
        
        this.mockMvc.perform(get("/settings_account_user/Adrian/userInformation")
        		    .principal(authentication))
        		    .andExpect(model().attributeExists("userInformation"))
        		    .andExpect(model().attributeExists("interestList"))
        		    .andExpect(model().attributeExists("programmingSkillList"))
        		    .andExpect(view().name("user_setting/user_settingInformation"))
        		    .andExpect(status().isOk());
    }
    
     @Test
     public void settingUserInformationPost() throws Exception {
    	adrian.example.musicplayer.model.User.User user = new adrian.example.musicplayer.model.User.User();
    	user.setLogin("Adrian");
    	user.setPassword("zxcvbnM123$");
    	user.setActive_cod(12345);
    	
    	UserInformation userInformation = new UserInformation();
   	    userInformation.setAge(23);
   	    userInformation.setSurname("Adrian");
   	    userInformation.setInterest("Football");
   	    userInformation.setSex("M");
   	    userInformation.setProgrammingSkill("Java");
   	    
   	    user.setUserInformation(userInformation);
   	    userInformation.setUser(user);

   	    userServiceImplSettings.saveUser(user);
   	    
   	    User userSpring = new User("Adrian", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
        
        this.mockMvc.perform(post("/settings_account_user/Adrian/userInformation")
        		    .principal(authentication)
        		    .param("age", 33 + "")
        		    .param("surname", "Paul")
        		    .param("interest", "Music")
        		    .param("sex", "K")
        		    .param("programmingSkill", "Spring"))
        		    .andExpect(redirectedUrl("/settings_account_user/" + authentication.getName() + "/userInformation"))
        		    .andExpect(status().is3xxRedirection());

        assertEquals(user.getUser_id(), userAdministrationService.getUser_id(authentication.getName()));
        assertEquals(33, userInformation.getAge());
        assertEquals("Paul", userInformation.getSurname());
        assertEquals("Music", userInformation.getInterest());
        assertEquals("K", userInformation.getSex());
        assertEquals("Spring", userInformation.getProgrammingSkill());
        
    }
}
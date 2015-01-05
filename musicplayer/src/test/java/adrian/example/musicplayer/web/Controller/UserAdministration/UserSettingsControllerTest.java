package adrian.example.musicplayer.web.Controller.UserAdministration;


import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import adrian.example.musicplayer.model.User.UserInformation;
import adrian.example.musicplayer.service.AdministrationAccount.UserAdministrationService;
import adrian.example.musicplayer.service.list.UserInformationServiceList;
import adrian.example.musicplayer.service.user.UserServiceImpl;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
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
	private WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	
	@Autowired
	UserAdministrationService userAdministrationService;
	
	@Autowired
	UserServiceImpl userServiceImplSettings;
	
	@Autowired
	UserInformationServiceList userInformationServiceList;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
	}
	
	@Test
	public void activeUserFromEmailUserActiveCodeNotMatch () throws Exception {
		this.mockMvc.perform(get("/verify")
	            	.param("user_id", "1")
	            	.param("active_code", "54321")
	            	.sessionAttr("user", new adrian.example.musicplayer.model.User.User()))
		            .andExpect(redirectedUrl("/error403"))
		            .andExpect(status().is3xxRedirection());
	}

	@Test
	public void activeUserFromEmailUserActiveCodeMatch() throws Exception {
		
		this.mockMvc.perform(get("/verify")
	            	.param("user_id", "" + 1)
	            	.param("active_code","12345")
	            	.sessionAttr("user", new adrian.example.musicplayer.model.User.User()))
		            .andExpect(redirectedUrl("/"))
		            .andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void settingsUserHomePageLoginAndPrincipalNotMatch() throws Exception {
		
		User userSpring = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
		this.mockMvc.perform(get("/settings_account_user/TestLogin/profile")
				             .principal(authentication))
				             .andExpect(redirectedUrl("/error403"))
				             .andExpect(status().is3xxRedirection());
	}
   
    @Test
    public void settingsUserHomePageLoginAndPrincipalMatch() throws Exception {
		User userSpring = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
		this.mockMvc.perform(get("/settings_account_user/TestLogin/profile")
	             .principal(authentication))
	             .andExpect(view().name("user_setting/user_settingProfil"))
	             .andExpect(status().isOk())
	             .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settingProfil.jsp"));
    }
    
    @Test
    public void settingsUserProfilePageHasError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = 
    			this.userAdministrationService.findLogin("TestLogin");
    	
    	this.mockMvc.perform(post("/settings_account_user/TestLogin/profile")
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
    	adrian.example.musicplayer.model.User.User user  = 
    			this.userAdministrationService.findLogin("TestLogin");
    	
    	this.mockMvc.perform(post("/settings_account_user/TestLogin/profile")
			     .param("login", "Adrian")
			     .param("password", "qwerty0")
			     .param("active_cod", "12345")
			     .param("address", "Nowy Adres")
			     .param("firstName", "firstName")
			     .sessionAttr("user", user))
			     .andExpect(redirectedUrl("/settings_account_user/" + user.getLogin() + "/profile"))
			     .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void settingUserPasswordGetPrincipalNotEqualsLogin() throws Exception{
    	
    	User userSpring = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
    	this.mockMvc.perform(get("/settings_account_user/TestLogin/password")
    			    .principal(authentication))
    	            .andExpect(redirectedUrl("/error403"))
    	            .andExpect(status().is3xxRedirection());
    }

    @Test
    public void settingUserPasswordGetPrincipalEqualsLogin() throws Exception {
    	User userSpring = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
		
		adrian.example.musicplayer.model.User.User user = 
				this.userAdministrationService.findLogin("TestLogin");
    	
    	this.mockMvc.perform(get("/settings_account_user/TestLogin/password")
    			    .principal(authentication))
    			    .andExpect(view().name("user_setting/user_settignPassword"))
    			    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settignPassword.jsp"))
    			    .andExpect(model().attribute("user", hasProperty("login", is("TestLogin"))))
    			    .andExpect(model().attribute("user", hasProperty("active_cod", is(12345))))
    			    .andExpect(model().attribute("user", hasProperty("address", is("TestAddress"))))
    			    .andExpect(model().attribute("user", hasProperty("firstName", is("TestFirstName"))))
    			    .andExpect(model().attribute("user", hasProperty("email", is("TestEmail@Test.com"))))
    			    .andExpect(status().isOk());
    }
    
    @Test
    public void settingUserPasswordHasError() throws Exception {
		adrian.example.musicplayer.model.User.User user = 
				this.userAdministrationService.findLogin("TestLogin");
    	
    	this.mockMvc.perform(post("/settings_account_user/TestLogin/password")
    			    .param("user_password", "errorpassword")
    			    .sessionAttr("user", user))
    			    .andExpect(view().name("user_setting/user_settignPassword"))
    			    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settignPassword.jsp"))
    			    .andExpect(model().attributeExists("wrongCurrentPassword"))
    			    .andExpect(status().isOk());
    }
    
    @Test
    public void settingUserPasswordNoError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = 
    			this.userAdministrationService.findLogin("TestLogin");
        
    	this.mockMvc.perform(post("/settings_account_user/TestLogin/password")
    			    .sessionAttr("user", user)
    			    .param("user_password", "TestPassword12345@")
    			    .param("password", "TestPassword123456@")
    			    .param("confirmPassword", "TestPassword123456@")
    			    .param("user_id", "" + user.getUser_id()))
    			    .andExpect(redirectedUrl("/settings_account_user/" + user.getLogin() +  "/password"))
    			    .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void settingUserEmailGETPrincipalAndLoginNotMatch() throws Exception {
    		
      	User userSpring = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
    		
    	this.mockMvc.perform(get("/settings_account_user/TestLogin/email")
    			    .principal(authentication))
    	            .andExpect(redirectedUrl("/error403"))
    	            .andExpect(status().is3xxRedirection());
    }
    
    @Test
    public void settingUserEmailGetPrincipalAndLoginMatch() throws Exception{
    	
      	User userSpring = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
        
        this.mockMvc.perform(get("/settings_account_user/TestLogin/email")
        		    .principal(authentication))
        		    .andExpect(model().attribute("user", hasProperty("login", is("TestLogin"))))
        		    .andExpect(model().attribute("user", hasProperty("address", is("TestAddress"))))
        		    .andExpect(model().attribute("user", hasProperty("email", is("TestEmail@Test.com"))))
        		    .andExpect(model().attribute("user", hasProperty("firstName", is("TestFirstName"))))
        		    .andExpect(model().attribute("user", hasProperty("active_cod", is(12345))))
        		    .andExpect(view().name("user_setting/user_settingEmail"))
        		    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settingEmail.jsp"))
        		    .andExpect(status().isOk());
    }
    
    @Test
    public void settingUserEmailPostHasError() throws Exception {
    	adrian.example.musicplayer.model.User.User user = 
    			this.userAdministrationService.findLogin("TestLogin");
    	
    	this.mockMvc.perform(post("/settings_account_user/TestLogin/email")
    			    .param("email", "123sad")
    			    .sessionAttr("user", user))
    			    .andExpect(view().name("user_setting/user_settingEmail"))
    			    .andExpect(forwardedUrl("/WEB-INF/views/user_setting/user_settingEmail.jsp"));
    }
    
    @Test
    public void settingsUserEmailPostNoError() throws Exception {
    	adrian.example.musicplayer.model.User.User user =
    			this.userAdministrationService.findLogin("TestLogin");
    	
    	User userSpring = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
    	
    	this.mockMvc.perform(post("/settings_account_user/TestLogin/email")
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
        
        this.mockMvc.perform(get("/settings_account_user/TestLogin/userInformation")
        		    .principal(authentication))
        		    .andExpect(redirectedUrl("/error403"))
        		    .andExpect(status().is3xxRedirection()); 
    }
    
    @Test
    public void settingUserInformationGetPrincipalAndLoginMatch() throws Exception {
    	adrian.example.musicplayer.model.User.User user = 
    			this.userAdministrationService.findLogin("TestLogin");
    	
    	User userSpring = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
        
    	UserInformation userInformation = 
    			this.userAdministrationService.getUserInformationById(user.getUser_id());
    	
        this.mockMvc.perform(get("/settings_account_user/TestLogin/userInformation")
        		    .principal(authentication))
        		    .andExpect(model().attribute("interestList", 
        		    		this.userInformationServiceList.getInterest()))
        		    .andExpect(model().attribute("programmingSkillList", 
        		            this.userInformationServiceList.getProgrammingStyle()))
        		    .andExpect(model().attribute("userInformation", 
        		    	userInformation))
        		    .andExpect(model().attribute("userInterestList", 
        		    		userInformation.getInterest()))
        		     .andExpect(model().attribute("userProgrammingSkillList",
        		    		 userInformation.getProgrammingSkill()))
        		    .andExpect(view().name("user_setting/user_settingInformation"))
        		    .andExpect(status().isOk());
    }
    
     @Test
     public void settingUserInformationPost() throws Exception {
    	adrian.example.musicplayer.model.User.User user = 
    			this.userAdministrationService.findLogin("TestLogin");
   	    
   	    User userSpring = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSpring, null, userSpring.getAuthorities());
        
        this.mockMvc.perform(post("/settings_account_user/TestLogin/userInformation")
        		    .principal(authentication)
        		    .param("age", 33 + "")
        		    .param("surname", "Paul")
        		    .param("interest", "Music")
        		    .param("sex", "K")
        		    .param("programmingSkill", "Spring"))
        		    .andExpect(redirectedUrl("/settings_account_user/" + authentication.getName() + "/userInformation"))
        		    .andExpect(status().is3xxRedirection());
    }
}
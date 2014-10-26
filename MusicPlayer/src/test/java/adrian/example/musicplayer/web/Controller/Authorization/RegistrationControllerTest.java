package adrian.example.musicplayer.web.Controller.Authorization;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

import javax.transaction.Transactional;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import adrian.example.musicplayer.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TransactionConfiguration
@Transactional
public class RegistrationControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Before
	public void setUp()   {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void registrationPageGetPrincipalNull() throws Exception {
		
		this.mockMvc.perform(get("/registration"))
		            .andExpect(view().name("authorization/registration"))
		            .andExpect(forwardedUrl("/WEB-INF/views/authorization/registration.jsp"))
		            .andExpect(model().attributeExists("user"))
		            .andExpect(model().size(1))
		            .andExpect(model().hasNoErrors())
		            .andExpect(status().isOk());
	}
	
	@Test
	public void registrationPageGetPrincipalnotNull() throws Exception {
		User user = new User("user", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		this.mockMvc.perform(get("/registration").principal(authentication))
		            .andExpect(redirectedUrl("/"))
		            .andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void registrationPagePostResultHasErrors() throws Exception {
		
		adrian.example.musicplayer.model.User.User userAfter = new adrian.example.musicplayer.model.User.User();
		userAfter.setLogin("Adrian");
		userAfter.setPassword("qwerty1@A");
		userAfter.setFirstName("Adrian");
		userAfter.setAddress("Nowa Street666");
		userAfter.setEmail("Adrian@gmail.com");
		this.userServiceImpl.saveUser(userAfter);
		
		
		this.mockMvc.perform(post("/registration")
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		            .param("strategyPattern", "smsActivation")
		            .param("login", "Adrian")
		            .param("password", "")
		            .param("confirmPassword", "qwerty1@A")
		            .param("firstName", "KAmil")
		            .param("address", "Ameryka")
		            .param("email", "ameryka@gmail.com")
		            .sessionAttr("user", new adrian.example.musicplayer.model.User.User())
				)
		            .andExpect(view().name("authorization/registration"))
		            .andExpect(forwardedUrl("/WEB-INF/views/authorization/registration.jsp"))
		            .andExpect(status().isOk());
	}
	
	@Test
	public void registrationPagePostResultNoErrorSaveToDatabase() throws Exception {
		
		this.mockMvc.perform(post("/registration")
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		            .param("strategyPattern", "smsActivation")
		            .param("login", "Adria")
		            .param("password", "qwerty1@A")
		            .param("confirmPassword", "qwerty1@A")
		            .param("firstName", "KAmil")
		            .param("address", "Ameryka")
		            .param("email", "ameryka@gmail.com")
		            .sessionAttr("user", new adrian.example.musicplayer.model.User.User())
				)
				    .andExpect(redirectedUrl("/"))
				    .andExpect(status().is3xxRedirection());
		
		adrian.example.musicplayer.model.User.User user = userServiceImpl.findLogin("Adria");
		
		
		assertNotNull(user);
		assertEquals("Adria", user.getLogin());
		assertTrue(bcryptEncoder.matches("qwerty1@A", user.getPassword()));
		assertEquals("KAmil", user.getFirstName());
		assertEquals("Ameryka", user.getAddress());
		assertEquals("ameryka@gmail.com", user.getEmail());
	}
}






























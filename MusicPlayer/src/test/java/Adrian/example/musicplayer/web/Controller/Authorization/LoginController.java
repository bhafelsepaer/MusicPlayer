package Adrian.example.musicplayer.web.Controller.Authorization;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LoginController {

	@Autowired
	private WebApplicationContext web;
	
	private MockMvc mockMvc;
	
	@Before
	public void setupUp() throws Exception{
		this.mockMvc = webAppContextSetup(this.web).alwaysExpect(status().isOk()).build();	
	}
	
	@Test
	public void loginFormGetSucced() throws Exception{
		User user = new User("adrian","", AuthorityUtils.createAuthorityList("ROLE_USER"));
		TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
		SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
		
		this.mockMvc.perform(get("/login").principal(testingAuthenticationToken))
		            .andExpect(view().name(containsString("authorization/login")));
	}

}

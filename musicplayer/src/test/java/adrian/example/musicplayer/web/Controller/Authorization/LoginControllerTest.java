package adrian.example.musicplayer.web.Controller.Authorization;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class LoginControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;
	 
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
	}
	
	@Test
	public void loginFormGetNullPrincipal() throws Exception {
		this.mockMvc.perform(get("/login"))
		            .andExpect(view().name("authorization/login"))
		            .andExpect(forwardedUrl("/WEB-INF/views/authorization/login.jsp"))
		            .andExpect(status().isOk());
	}
	
	@Test
	public void loginFormGetPrincipalNotNull() throws Exception {
		User user = new User("user1","",AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		this.mockMvc.perform(get("/login").principal(authentication))
		            .andExpect(redirectedUrl("/"))
		            .andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void loginFormPost() throws Exception {
		this.mockMvc.perform(post("/login"))
		            .andExpect(redirectedUrl("/"))
		            .andExpect(status().is3xxRedirection());
	}
}

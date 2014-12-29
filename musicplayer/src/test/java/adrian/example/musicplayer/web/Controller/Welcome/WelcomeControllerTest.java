package adrian.example.musicplayer.web.Controller.Welcome;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.transaction.Transactional;

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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import adrian.example.musicplayer.service.music.PlaylistService;
import adrian.example.musicplayer.service.user.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@Transactional
@TransactionConfiguration
public class WelcomeControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	PlaylistService playListService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
	}

	@Test
	public void test_homePrincipallNotNull() throws Exception {
		User user = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		int user_id  =  this.userService.findLogin("TestLogin").getUser_id();
		
		this.mockMvc.perform(get("/").principal(authentication))
		            .andExpect(view().name(containsString("home")))
		            .andExpect(request().sessionAttribute("user_id", user_id))
		            .andExpect(request().sessionAttribute("playlist", 
		            		   this.playListService.getPlaylistById(user_id)))
		            .andExpect(forwardedUrl("/WEB-INF/views/home.jsp"))
		            .andExpect(status().isOk());
	}
	
	@Test
	public void test_homePrincipallNull() throws Exception {
		this.mockMvc.perform(get("/"))
		            .andExpect(view().name(containsString("home")))
		            .andExpect(forwardedUrl("/WEB-INF/views/home.jsp"))
		            .andExpect(status().isOk());
	}
}

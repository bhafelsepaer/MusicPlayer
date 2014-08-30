package Adrian.example.musicplayer.web.Controller.WelcomeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.containsString;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WelcomeControllerTest{

	@Autowired
	private WebApplicationContext web;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
	     this.mockMvc = webAppContextSetup(this.web).alwaysExpect(status().isOk()).build();	
	}
	
	@Test
	public void home() throws Exception{
		this.mockMvc.perform(get("/"))
		            .andExpect(view().name(containsString("home")))
		             .andExpect(forwardedUrl("/WEB-INF/views/home.jsp"))
		            .andExpect(status().isOk());
	}
}

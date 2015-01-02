package adrian.example.musicplayer.service.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class UserInformationServiceListImplTest {

	@Autowired
	UserInformationServiceList userInformationServiceListImpl;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_getInterest() {
		List<String> interestTest = 
				this.userInformationServiceListImpl.getInterest();
		
		assertEquals(5, interestTest.size());
		assertTrue(interestTest.toString().contains(
				"[Football, Video Game, Travel, Tennis, Fight]"));
	}
	
	@Test 
	public void test_getProgrammingStyle() {
		List<String> programmingStyleTest = 
				this.userInformationServiceListImpl.getProgrammingStyle();
		
		assertEquals(6, programmingStyleTest.size());
		assertTrue(programmingStyleTest.toString().contains(
				"[JAVA, JAVAEE, Spring, Struts, Hibernate, JSP]"));
	}

}

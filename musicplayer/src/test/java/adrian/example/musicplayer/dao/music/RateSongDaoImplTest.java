package adrian.example.musicplayer.dao.music;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.model.Music.RateSong;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@Transactional
@TransactionConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RateSongDaoImplTest {

	@Autowired
	RateSongDao rateSongDao;
	
	@Before
	public void setUp() throws Exception {}

	@Test
	public void test_getRateSongById(){
		RateSong rateSong = 
				this.rateSongDao.getRateSongById(1);
		
		assertEquals("Test Id", 
				1, rateSong.getRateSong_id());
		assertEquals("Test user id",
				1, rateSong.getUser().getUser_id());
		assertEquals("Test Song id", 
				1, rateSong.getSong().getSong_id());
		assertEquals("Test rating song", 
				5.0, rateSong.getRating(), 0.001);
	}
	
	@Test
	public void test_updateRateSong() {
      this.rateSongDao.saveRatingSong(2, 2, 4);
		RateSong rateSong = 
				this.rateSongDao.getRateSongById(6);
		
		assertEquals("Test RateSong Id Update Update",
				 6, rateSong.getRateSong_id());
		assertEquals("Test rating Song Before Update",
				4, rateSong.getRating(), 0.001);
		
		this.rateSongDao.saveRatingSong(2, 2, 3);
		
		RateSong rateSong2 = 
				this.rateSongDao.getRateSongById(6);
		
		assertEquals("Test RateSong Id After Update", 
				 6, rateSong2.getRateSong_id());
		assertEquals("Test rating Song After Update", 
				3, rateSong2.getRating(), 0.001);
	}
	
	@Test
	public void test_saveRateSong() {
		this.rateSongDao.saveRatingSong(2, 3, 5);
		
		RateSong rateSong = 
				this.rateSongDao.getRateSongById(6);
		
		assertEquals("Test RateSong Id", 
				6, rateSong.getRateSong_id());
		assertEquals("Test rating",
				5, rateSong.getRating(), 0.001);
	}
	
	@Test
	public void test_getRateSongByUser(){
		 double rateSong = 
					this.rateSongDao.getRateSongByUser(1, 1);
		 assertEquals("Test rating", 
				 5, rateSong, 0.001);
	}
	
	@Test
	public void test_getAverageSongRating(){
		List<Double> listOfRatingSong = 
				this.rateSongDao.getRateingSongBelongToSong(1);
		
		assertTrue("Test rating Song", 
				listOfRatingSong.toString().contains(
					"[5.0, 4.0, 3.0, 2.0]"
				));
	}
}

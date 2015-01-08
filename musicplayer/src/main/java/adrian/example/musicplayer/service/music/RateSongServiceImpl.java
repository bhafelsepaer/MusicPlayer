package adrian.example.musicplayer.service.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.dao.music.RateSongDao;
import adrian.example.musicplayer.model.Music.RateSong;

@Service
public class RateSongServiceImpl implements RateSongService {
    
	@Autowired
	RateSongDao rateSongDao;
	
	@Override
	@Transactional
	public void saveRatingSong(int user_id, int song_id, int ratingSong) {
		this.rateSongDao.
		     saveRatingSong(user_id, song_id, ratingSong);
	}

	@Override
	public RateSong getRateSongById(int rating_id) {
		return this.rateSongDao.
				    getRateSongById(rating_id);
	}

}

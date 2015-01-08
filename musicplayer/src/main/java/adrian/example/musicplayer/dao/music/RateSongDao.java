package adrian.example.musicplayer.dao.music;

import adrian.example.musicplayer.model.Music.RateSong;

public interface RateSongDao {

	void saveRatingSong(int user_id, int song_id, int ratingSong);
	
	RateSong getRateSongById(int rating_id);
}

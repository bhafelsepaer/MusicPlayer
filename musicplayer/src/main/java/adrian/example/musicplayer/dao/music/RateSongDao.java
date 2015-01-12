package adrian.example.musicplayer.dao.music;

import java.util.List;

import adrian.example.musicplayer.model.Music.RateSong;

public interface RateSongDao {

	void saveRatingSong(int user_id, int song_id, int ratingSong);
	
	RateSong getRateSongById(int rating_id);
	
	double getRateSongByUser(int user_id, int song_id);
	
	List<Double> getRateingSongBelongToSong(int song_id);
}

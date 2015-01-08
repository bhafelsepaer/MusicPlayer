package adrian.example.musicplayer.service.music;

import adrian.example.musicplayer.model.Music.RateSong;

public interface RateSongService {

    void saveRatingSong(int user_id, int song_id, int ratingSong);
	
	RateSong getRateSongById(int rating_id);
}

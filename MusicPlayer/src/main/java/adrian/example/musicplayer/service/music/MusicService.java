package adrian.example.musicplayer.service.music;

import java.util.List;

import adrian.example.musicplayer.model.Music.Song;

public interface MusicService {

	void SaveSong(Song song);
	
	List<Song> getSongByGenre(String genre_name);
	
	Song loadSongById(int id);
}

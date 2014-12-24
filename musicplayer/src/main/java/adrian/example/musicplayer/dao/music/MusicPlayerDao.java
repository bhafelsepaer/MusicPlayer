package adrian.example.musicplayer.dao.music;

import java.util.List;

import adrian.example.musicplayer.model.Music.Song;

public interface MusicPlayerDao {
	
	void saveSong(Song song);
	
	List<Song> getSongByGenre(String genre_name);
	
	List<Song> getSongByAlbum(String album_name);
	
	List<Song> getSongByArtist(String artist_name);
	
	Song loadSongById(int id);
}

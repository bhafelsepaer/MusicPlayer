package adrian.example.musicplayer.service.music;

import java.util.List;

import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

public interface PlayListSongService {

	void savePlaylistSong(int song_id, int playlist_id);

	List<PlaylistSong> getListOfPlaylistSongById(int playlistId);
	
    PlaylistSong getPlaylistSong(int playlist_id, int song_id);
	
	void deleteSongFromPlaylistSong(int playlist_id, int song_id);
}

package adrian.example.musicplayer.service.music;

import java.util.List;

import adrian.example.musicplayer.model.Music.playlist.Playlist;

public interface PlaylistService {
	
	List<Playlist> getPlaylistByUserId(int user_id);
	
	Playlist getPlaylistByPlaylistId(int playlist_id);
	
	void savePlaylist(int user_id, String playlistName);
	
	void updatePlaylist(int playlist_id, String playlistName_updatable);
	
	void deletePlaylist(int playList_id);
}

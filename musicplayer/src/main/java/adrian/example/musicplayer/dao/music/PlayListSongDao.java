package adrian.example.musicplayer.dao.music;

import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

public interface PlayListSongDao {

	void savePlaylistSong(int song_id, int playlist_id);
	
	PlaylistSong getPlaylistSongById(int playlistSongId);
}

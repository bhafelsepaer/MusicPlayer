package adrian.example.musicplayer.service.music;

import java.util.List;

import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

public interface PlayListSongService {

	void savePlaylistSong(int song_id, int playlist_id);

	List<PlaylistSong> getPlaylistSongById(int playlistSongId);
}

package adrian.example.musicplayer.service.music;

import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

public interface PlayListSongService {

	void savePlaylistSong(int song_id, int playlist_id);

	PlaylistSong getPlaylistSongById(int playlistSongId);
}

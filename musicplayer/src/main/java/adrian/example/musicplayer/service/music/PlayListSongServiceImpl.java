package adrian.example.musicplayer.service.music;

import org.springframework.beans.factory.annotation.Autowired;

import adrian.example.musicplayer.dao.music.PlayListSongDao;
import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

public class PlayListSongServiceImpl implements PlayListSongService {

	@Autowired
	PlayListSongDao playlistSongDao;
	
	@Override
	public void savePlaylistSong(int song_id, int playlist_id) {
		this.playlistSongDao.
		     savePlaylistSong(song_id, playlist_id);

	}

	@Override
	public PlaylistSong getPlaylistSongById(int playlistSongId) {
		return this.playlistSongDao.
				   getPlaylistSongById(playlistSongId);
	}

}

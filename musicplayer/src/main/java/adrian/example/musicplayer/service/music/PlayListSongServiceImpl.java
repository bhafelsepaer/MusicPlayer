package adrian.example.musicplayer.service.music;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adrian.example.musicplayer.dao.music.PlayListSongDao;
import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

@Service
public class PlayListSongServiceImpl implements PlayListSongService {

	@Autowired
	PlayListSongDao playlistSongDao;
	
	@Override
	@Transactional
	public void savePlaylistSong(int song_id, int playlist_id) {
		this.playlistSongDao.
		     savePlaylistSong(song_id, playlist_id);

	}

	@Override
	@Transactional
	public PlaylistSong getPlaylistSongById(int playlistSongId) {
		return this.playlistSongDao.
				   getPlaylistSongById(playlistSongId);
	}

}

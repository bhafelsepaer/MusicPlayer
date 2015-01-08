package adrian.example.musicplayer.service.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public List<PlaylistSong> getListOfPlaylistSongById(int playlistId) {
		return this.playlistSongDao.
				   getListOfPlaylistSongById(playlistId);
	}

	@Override
	@Transactional(readOnly = true)
	public PlaylistSong getPlaylistSong(int playlist_id, int song_id) {
		return this.playlistSongDao.
				   getPlaylistSong(playlist_id, song_id);
	}

	@Override
	@Transactional
	public void deleteSongFromPlaylistSong(int playlist_id, int song_id) {
             this.playlistSongDao.
                 deleteSongFromPlaylistSong(playlist_id, song_id);		
	}
}

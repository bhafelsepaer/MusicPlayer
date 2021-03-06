package adrian.example.musicplayer.service.music;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.dao.music.PlayListDao;
import adrian.example.musicplayer.model.Music.playlist.Playlist;

@Service("playListService")
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	PlayListDao playListDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Playlist> getPlaylistByUserId(int user_id) {
		return this.playListDao.getPlaylistByUserId(user_id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Playlist getPlaylistByPlaylistId(int playlist_id) {
		return this.playListDao.getPlaylistByPlaylistId(playlist_id);
	}

	@Override
	@Transactional
	public void savePlaylist(int user_id, String playlistName) {
		this.playListDao.savePlaylist(user_id, playlistName);
	}
	
	@Override
	@Transactional
	public void updatePlaylist(int playlist_id, String playlistName_updatable) {
         this.playListDao.updatePlaylist(playlist_id, playlistName_updatable);		
	}

	@Override
	@Transactional
	public void deletePlaylist(int playList_id) {
		this.playListDao.deletePlaylist(playList_id);
	}
}

package adrian.example.musicplayer.service.music;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adrian.example.musicplayer.dao.music.PlayListDao;
import adrian.example.musicplayer.model.Music.playlist.Playlist;

@Service("playListService")
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	PlayListDao playListDao;
	
	@Override
	@Transactional
	public List<Playlist> getPlaylistById(int user_id) {
		return this.playListDao.getPlaylistById(user_id);
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

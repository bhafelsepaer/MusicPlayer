package adrian.example.musicplayer.dao.music;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adrian.example.musicplayer.model.Music.playlist.Playlist;
import adrian.example.musicplayer.model.User.User;

@Repository("playListDao")
public class PlayListImplDao implements PlayListDao {

	@Autowired 
	SessionFactory sessionFactory;
	
	@Override
	public List<Playlist> getPlaylistById(int user_id) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Playlist> playlist = (List<Playlist>) session.createQuery
				("FROM Playlist  WHERE user_id = :user_id")
				.setParameter("user_id", user_id).list();
         
		System.out.println("SIZE PLAYLIST " + playlist.size());
		return playlist;
	}

	@Override
	public void savePlaylist(int user_id, String playlistName) {
		Session session = this.sessionFactory.getCurrentSession();
		
		User currentUser = (User) session.get(User.class, user_id);
		
		Playlist newPlaylist = new Playlist();
		newPlaylist.setName(playlistName);
		currentUser.getPlaylist().add(newPlaylist);
		
		session.save(currentUser);
	}
	
	@Override
	public void updatePlaylist(int playlist_id, String playlistName_updatable) {
       Session session = this.sessionFactory.getCurrentSession();
       
       Playlist currentPlaylist = (Playlist) session.get(Playlist.class, playlist_id);
       currentPlaylist.setName(playlistName_updatable);
       
       session.update(currentPlaylist);
	}

	@Override
	public void deletePlaylist(int playList_id) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Playlist currentPlayList = (Playlist) session.get(Playlist.class, playList_id);
		
		session.delete(currentPlayList);
	}

}

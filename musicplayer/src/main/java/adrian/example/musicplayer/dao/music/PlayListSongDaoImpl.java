package adrian.example.musicplayer.dao.music;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.model.Music.playlist.Playlist;
import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

@Repository
public class PlayListSongDaoImpl implements PlayListSongDao{

	@Autowired
	private SessionFactory SessionFactory;
	
	@Override
	public void savePlaylistSong(int song_id, int playlist_id) {
		Session session = this.SessionFactory.getCurrentSession();
		
		Song song = (Song) session.load(Song.class, song_id);
		Playlist playlist = (Playlist) session.load(Playlist.class, playlist_id);
		
		PlaylistSong playlistSong = new PlaylistSong();
		Set<PlaylistSong> playlistSet = new HashSet(0);
		
		playlistSong.setSong(song);
		playlistSong.setPlaylist(playlist);
		playlistSet.add(playlistSong);
		
		playlist.setPlaylistSong(playlistSet);
		song.setPlaylistSong(playlistSet);
		
		session.save(playlistSong);
		session.save(playlist);
		session.save(song);
	}

	@Override
	public List<PlaylistSong> getPlaylistSongById(int playlistSongId) {
		Session session = this.SessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<PlaylistSong> playlistSong = (List<PlaylistSong>) 
				session.createQuery("FROM PlaylistSong playlistSong JOIN FETCH playlistSong.song "
						+ "WHERE playlistSong.playlist.playlist_id  = :playlistSongId")
				.setParameter("playlistSongId", playlistSongId).list();
		
		return playlistSong;
	}

}

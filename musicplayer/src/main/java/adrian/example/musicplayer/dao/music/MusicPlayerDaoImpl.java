package adrian.example.musicplayer.dao.music;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import adrian.example.musicplayer.model.Music.Album;
import adrian.example.musicplayer.model.Music.Artist;
import adrian.example.musicplayer.model.Music.Genres;
import adrian.example.musicplayer.model.Music.Song;

@Repository("musicPlayerDao")
public class MusicPlayerDaoImpl implements MusicPlayerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Song> getSongByGenre(String genre_name) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Song> list_of_song= (List<Song>) session.createQuery
				("FROM Song song Where song.genres.genresName = :genre_name")
				.setParameter("genre_name", genre_name).list();
		
		return list_of_song;
	}


	@Override
	public List<Song> getSongByAlbum(String album_name) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Song> list_of_song_by_album = (List<Song>) 
				session.createQuery("FROM Song song WHERE song.album.album_name = :album_name")
				       .setParameter("album_name", album_name).list();
		return list_of_song_by_album;
	}
	
	@Override
	public List<Song> getSongByArtist(String artist_name) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Song> list_of_song_by_artist = (List<Song>)
				session.createQuery("FROM Song song WHERE song.artist.name = :artist_name")
				      .setParameter("artist_name", artist_name).list();
		return list_of_song_by_artist;
	}

	@Override
	public Song getSongById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Song song = (Song)session.get(Song.class, id);
		return song;
	}

}

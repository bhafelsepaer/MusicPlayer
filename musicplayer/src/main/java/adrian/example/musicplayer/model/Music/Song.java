package adrian.example.musicplayer.model.Music;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

@Entity
@Table(name = "SONG", uniqueConstraints = 
@UniqueConstraint(columnNames = {"name", "filename"}))
public class Song implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_id")
	private int song_id;
	
	@Column(name = "name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "filename", length = 255, nullable = false)
	private String filename;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "genre_id")
	private Genres genres;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "album_id")
	private Album album;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "artist_id")
	private Artist artist;
	
	@OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<PlaylistSong> playlistSong;

	@OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RateSong> rateSong;
	
	public int getSong_id() {
		return song_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Genres getGenres() {
		return genres;
	}

	public void setGenres(Genres genres) {
		this.genres = genres;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Set<PlaylistSong> getPlaylistSong() {
		return playlistSong;
	}

	public void setPlaylistSong(Set<PlaylistSong> playlistSong) {
		this.playlistSong = playlistSong;
	}

	public Set<RateSong> getRateSong() {
		return rateSong;
	}

	public void setRateSong(Set<RateSong> rateSong) {
		this.rateSong = rateSong;
	}

	
}

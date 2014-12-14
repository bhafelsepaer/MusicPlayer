package adrian.example.musicplayer.model.Music;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

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
	
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genres genres;
	
	@ManyToOne
	@JoinColumn(name = "album_id")
	private Album album;

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

	
}

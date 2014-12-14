package adrian.example.musicplayer.model.Music;

import java.io.Serializable;
import java.util.Set;

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

@Entity
@Table(name = "ALBUM", uniqueConstraints = @UniqueConstraint(columnNames = "album_name"))
public class Album implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_id")
	private int album_id;

	@Column(name = "album_name", length = 255)
	private String album_name;

	@Column(name = "picture_metadate", length = 255)
	private String picture_metadate;

	@Column(name = "release_date", nullable = true)
	private int release_date;
	
	@OneToMany
	private Set<Song> song;
	
	public int getAlbum_id() {
		return album_id;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public String getPicture_metadate() {
		return picture_metadate;
	}

	public void setPicture_metadate(String picture_metadate) {
		this.picture_metadate = picture_metadate;
	}

	public int getRelease_date() {
		return release_date;
	}

	public void setRelease_date(int release_date) {
		this.release_date = release_date;
	}

	public Set<Song> getSong() {
		return song;
	}

	public void setSong(Set<Song> song) {
		this.song = song;
	}
}

package adrian.example.musicplayer.model.Music;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TRACK", uniqueConstraints = 
       { @UniqueConstraint(columnNames = {"name"})})
public class Track implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "track_id")
	private int track_id;
	
	@Column(name = "name", length = 255)
	private String name;
	
	@Column(name = "metadate_track", length = 255)
	private String metadate_file;
	
	@Column(name = "play_time")
	private Time playTime;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artist_id")
	private Artist artist_track;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genre_id")
	private Genres genres;
	
	public int getTrack_id() {
		return track_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Time playTime) {
		this.playTime = playTime;
	}

	public Artist getArtist_track() {
		return artist_track;
	}

	public void setArtist_track(Artist artist_track) {
		this.artist_track = artist_track;
	}

	public Genres getGenres() {
		return genres;
	}

	public void setGenres(Genres genres) {
		this.genres = genres;
	}
	
}

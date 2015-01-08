package adrian.example.musicplayer.model.Music;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import adrian.example.musicplayer.model.User.User;

@Entity
@Table(name = "rate_song", uniqueConstraints =
       @UniqueConstraint(columnNames = 
       {"user_rate_song_id", "song_rate_song_id"}))
@DynamicUpdate
public class RateSong {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rate_song_id")
	private int rateSong_id;
	
	@Column(name = "rate_song")
	private int rating;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_rate_song_id", referencedColumnName = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "song_rate_song_id", referencedColumnName = "song_id")
	private Song song;

	public int getRateSong_id() {
		return rateSong_id;
	}

	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}
}

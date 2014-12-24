package adrian.example.musicplayer.model.Music.playlist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import adrian.example.musicplayer.model.User.User;

@Entity
@Table(name = "playlist", uniqueConstraints = 
       @UniqueConstraint(columnNames = "name"))
public class Playlist implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playlist_id")
	private int playlist_id;
	
	@Column(name = "name")
	private String name;
	
	public int getPlaylist_id() {
		return playlist_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

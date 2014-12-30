package adrian.example.musicplayer.model.Music.playlist;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PlaylistSong> playlistSong;
	
	public int getPlaylist_id() {
		return playlist_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PlaylistSong> getPlaylistSong() {
		return playlistSong;
	}

	public void setPlaylistSong(Set<PlaylistSong> playlistSong) {
		this.playlistSong = playlistSong;
	}

}

package adrian.example.musicplayer.model.Music;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ARTIST", uniqueConstraints = 
      { @UniqueConstraint(columnNames = "name")})

public class Artist implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_id")
	private int artist_id;
	
	@Column(name = "name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "years_active", nullable = true)
	private int years_active;
	
	@Column(name = "description", length = 255, nullable = true)
	private String description;
	
	@OneToMany
	private Set<Song> song;
	
	public int getArtist_id() {
		return artist_id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYears_active() {
		return years_active;
	}

	public void setYears_active(int years_active) {
		this.years_active = years_active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Song> getSong() {
		return song;
	}

	public void setSong(Set<Song> song) {
		this.song = song;
	}

}

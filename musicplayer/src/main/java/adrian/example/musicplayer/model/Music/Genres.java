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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "GENRES", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Genres implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	private int genres_id;

	@Column(name = "name", length = 255)
	private String genresName;

	@Column(name = "description", length = 255, nullable = true)
	private String genresDescription;

	@OneToMany
	private Set<Song> song;
	
	public int getGenres_id() {
		return genres_id;
	}

	public String getGenresName() {
		return genresName;
	}

	public void setGenresName(String genresName) {
		this.genresName = genresName;
	}

	
	public String getGenresDescription() {
		return genresDescription;
	}

	public void setGenresDescription(String genresDescription) {
		this.genresDescription = genresDescription;
	}

	public Set<Song> getSong() {
		return song;
	}

	public void setSong(Set<Song> song) {
		this.song = song;
	}
}

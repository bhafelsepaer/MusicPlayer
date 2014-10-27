package adrian.example.musicplayer.model.Music;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "GENRES", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Genres {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genres_id")
	private int genres_id;

	@Column(name = "name", length = 255)
	private String genresName;

	@Column(name = "description", length = 255, nullable = true)
	private String genresDescription;

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

}

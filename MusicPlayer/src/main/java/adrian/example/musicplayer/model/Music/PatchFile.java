package adrian.example.musicplayer.model.Music;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PATCH")
public class PatchFile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patch_id")
	private int patch_id;

	@Column(name = "name")
	private String patch_name;

	public int getPatch_id() {
		return patch_id;
	}

	public String getPatch_name() {
		return patch_name;
	}

	public void setPatch_name(String patch_name) {
		this.patch_name = patch_name;
	}

}

package adrian.example.musicplayer.model.User;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "user_information")
public class UserInformation implements Serializable{

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", 
	                  parameters = {@Parameter(name = "property", value = "user")})
	@Column(name = "user_information_id", unique = true, nullable = false)
	private int user_information_id;
	
	@Column(name = "age", nullable = true)
	private int age;
	
	@Column(name = "surname", length = 20, nullable = true)
	private String surname;
	
	@Column(name = "interest", length = 255, nullable = true)
	private String interest;
	
	@Column(name = "sex", length = 100, nullable = true)
	private String sex;
	
	@Column(name = "programming_skill", length = 255, nullable = true)
	private String programmingSkill;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@PrimaryKeyJoinColumn
	private User user;

	public int getUser_information_id() {
		return user_information_id;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProgrammingSkill() {
		return programmingSkill;
	}

	public void setProgrammingSkill(String programmingSkill) {
		this.programmingSkill = programmingSkill;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

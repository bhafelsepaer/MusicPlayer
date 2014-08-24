package adrian.example.musicplayer.model.User;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import adrian.example.musicplayer.validation.userValidation.CheckUserExist;
import adrian.example.musicplayer.validation.userValidation.PasswordEqualsConstraint;

@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"login"})
})
@DynamicInsert
@DynamicUpdate
@PasswordEqualsConstraint(groups = {User.DefaultValidation.class, 
		User.PasswordValidation.class})
public class User implements Serializable{
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;
	
	@NotBlank(groups = DefaultValidation.class)
	@CheckUserExist(groups = DefaultValidation.class)
	@Column(name = "login",length = 20, nullable = false)
	private String login;
	
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})", 
			groups = {DefaultValidation.class, PasswordValidation.class, 
			          MailMatchValidation.class})
	@Column(name = "password", length = 20, nullable = false)
	private String password;
	
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})",
			 groups = {DefaultValidation.class, PasswordValidation.class})
	@Transient
	private String confirmPassword;
	
	@Column(name = "active_cod", length = 5, nullable = false)
	private int active_cod;
	
	@Column(name = "enabled", nullable = true)
	@Type(type = "java.lang.Boolean")
	private boolean enabled;

	@Size(min = 3, max=20, groups = {DefaultValidation.class,
	     FirstNameAndAddressValidation.class})
	@Column(name = "firstName", length = 20)
	private String firstName;
	
	@Size(min = 5, max = 20, groups = {DefaultValidation.class, 
	     FirstNameAndAddressValidation.class})
	@Column(name = "address", length = 50)
	private String address;
	
	@Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
           + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", 
           groups =  {DefaultValidation.class, MailValidation.class, 
			          MailMatchValidation.class})
	@Column(name = "email", length = 40)
	private String email;
	
	@Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
		   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", 
		   groups =  MailMatchValidation.class)
	@Transient
	private String confirmEmail;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
	private Set<RoleUser> roleUser = new HashSet<RoleUser>(0);
	
	public User() {}
	
	public int getUser_id() {
		return user_id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public int getActive_cod() {
		return active_cod;
	}

	public void setActive_cod(int active_cod) {
		this.active_cod = active_cod;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public Set<RoleUser> getRoleUser() {
		return roleUser;
	}
    
	public void setRoleUser(Set<RoleUser> roleUser) {
		this.roleUser = roleUser;
	}
	
	public interface PasswordValidation {};
	
	public interface MailValidation {};
	
	public interface MailMatchValidation {};
	
	public interface DefaultValidation {};
	
	public interface FirstNameAndAddressValidation {};
}

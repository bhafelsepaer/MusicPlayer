package adrian.example.main;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "12345";
		String hashedPassword  = passwordEncoder.encode(password);
		
		System.out.println(hashedPassword);

	}

}

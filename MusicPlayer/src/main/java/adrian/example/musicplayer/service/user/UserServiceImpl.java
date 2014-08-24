package adrian.example.musicplayer.service.user;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.dao.user.UserDao;
import adrian.example.musicplayer.model.User.User;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	@Qualifier("bcryptEncoder")
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public User findLogin(String login) {
		return this.userDao.findLogin(login);
	}
	
	@Override
	@Transactional
	public void saveUser(User user) {
		String encodePassword = 
				passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodePassword);
		user.setActive_cod(active_code());
		
		this.userDao.saveUser(user);
	}
	
	@Override
	public int active_code() {
		
		Random randomNumber = new Random(); 
		int random_active_number = randomNumber.nextInt(99999 - 10000) + 1000;
		
		return random_active_number;
    }
}

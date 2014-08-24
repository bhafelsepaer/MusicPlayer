package adrian.example.musicplayer.service.AdministrationAccount;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.dao.user.UserDao;
import adrian.example.musicplayer.model.User.User;

@Service("userAdministrationServiceImpl")
public class UserAdministrationServiceImpl implements UserAdministrationService {

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public User findLogin(String login) {
		return this.userDao.findLogin(login);
	}
	
	@Override
	@Transactional
	public void updateUserInformation(int user_id, String address, String firstName) {
		
		try{
	    	this.userDao.updateUserInformation(user_id, address, firstName);
		}catch(ObjectNotFoundException exception){
			exception.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public void updatePassword(int user_id, String password)
			throws ObjectNotFoundException {
		try{
			this.userDao.updatePassword(user_id, password);
		}catch(ObjectNotFoundException exception){
			exception.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public void updateEmail(int user_id, String email) {
		try{
			this.userDao.updateEmail(user_id, email);
		}catch(ObjectNotFoundException exception){
			exception.printStackTrace();
		}
	}

	@Override
	@Transactional
	public boolean checkUserVerify(int user_id, int active_code) {
        
		boolean checkedVerify = false;
		
		try{
			checkedVerify  = this.userDao.checkUserVerify(user_id, active_code);
		}catch(ObjectNotFoundException exception){
			exception.printStackTrace();
		}
		return checkedVerify;
	}
	
	@Override
	@Transactional
	public boolean checkPassword(int user_id, String CurrentPassword) {
		return this.userDao.checkPassword(user_id, CurrentPassword);
	}

	@Override
	@Transactional
	public void setEnabledTrue(int user_id) {
		this.userDao.setEnabledTrue(user_id);
	}
}

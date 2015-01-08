package adrian.example.musicplayer.service.AdministrationAccount;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.dao.user.UserDao;
import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.model.User.UserInformation;

@Service("userAdministrationServiceImpl")
public class UserAdministrationServiceImpl implements UserAdministrationService {

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public int getUser_id(String login) {
		return this.userDao.getUser_id(login);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserInformation getUserInformationById(int user_id) {
        return	this.userDao.getUserInformationById(user_id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findLogin(String login) {
		return this.userDao.findUserByLogin(login);
	}
	
	@Override
	@Transactional
	public void updateUserInformation(int user_id, String address, String firstName) throws ObjectNotFoundException {
		
		try{
	    	this.userDao.updateUserInformation(user_id, address, firstName);
		}catch(ObjectNotFoundException exception){
			throw new ObjectNotFoundException(23, "User");
		}
	}
	
	@Override
	@Transactional
	public void updatePassword(int user_id, String password)
			throws ObjectNotFoundException {
		try{
			this.userDao.updatePassword(user_id, password);
		}catch(ObjectNotFoundException exception){
			throw new ObjectNotFoundException(23, "User");
		}
	}
	
	@Override
	@Transactional
	public void updateEmail(int user_id, String email) {
		try{
			this.userDao.updateEmail(user_id, email);
		}catch(ObjectNotFoundException exception){
			throw new ObjectNotFoundException(55, "User");
		}
	}
	
	@Override
	@Transactional
	public void updateOrSaveUserInformation(int user_id,
			UserInformation userInformation) {
        this.userDao.updateOrSaveUserInformation(user_id, userInformation);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkUserVerify(int user_id, int active_code) {
        
		boolean checkedVerify = false;
		
		try{
			checkedVerify  = this.userDao.checkUserVerify(user_id, active_code);
		}catch(ObjectNotFoundException exception){
			throw new ObjectNotFoundException(102, "User");
		}
		return checkedVerify;
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean checkPassword(int user_id, String CurrentPassword) {
		
		boolean checkedPassword = false;
		
		try {
		    checkedPassword = this.userDao.checkPassword(user_id, CurrentPassword);
		}catch(ObjectNotFoundException exception){
			throw new ObjectNotFoundException(103, "User");
		}
		return checkedPassword;
	}

	@Override
	@Transactional
	public void setEnabledTrue(int user_id) {
		this.userDao.setEnabledTrue(user_id);
	}
}

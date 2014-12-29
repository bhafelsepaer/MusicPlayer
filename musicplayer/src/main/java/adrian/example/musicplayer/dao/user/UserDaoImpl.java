package adrian.example.musicplayer.dao.user;


import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import adrian.example.musicplayer.model.User.RoleUser;
import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.model.User.UserInformation;

/**
 * 
 * Implementation of the {@link userDao} interface
 * 
 * @author adrian
 *
 */
@Repository("userDao")
public class UserDaoImpl  implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("bcryptEncoder")
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public int getUser_id(String login) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Integer user_id = (Integer) session.createQuery(
				"SELECT U.user_id FROM User U WHERE U.login = :login")
				.setParameter("login", login)
				.uniqueResult();
		
		return user_id;
	}
	
	@Override
	public User findUserByLogin(String login) {
	    Session session = this.sessionFactory.getCurrentSession();
	   
	    User user = (User) session
	    .createQuery("FROM User WHERE login = :login")
	    .setParameter("login", login).uniqueResult();
		
	    if(user != null){
	    	 return user;
	    }else{
	    	return null;
	    }
	}
	
	@Override
	public User findUserById(int user_id) throws ObjectNotFoundException {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, user_id);
		
		return user;
	}
	
	@Override
	public UserInformation getUserInformationById(int user_id) {
		Session session = this.sessionFactory.getCurrentSession();
		
		UserInformation userInformation = 
				(UserInformation) session.get(UserInformation.class, user_id);
		
		if(userInformation != null){
		    return userInformation;
		}else{
		    return new UserInformation();
		}
	}

	@Override
	public void updateUserInformation(int user_id, String address, String firstName) 
			    throws ObjectNotFoundException {
		
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, user_id);
		user.setAddress(address);
		user.setFirstName(firstName);
		session.update(user);
	}
	
	@Override
	public void updatePassword(int user_id, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		
		User user = (User) session.load(User.class, user_id);
		String encodePassword = passwordEncoder.encode(password);
		user.setPassword(encodePassword);
		session.persist(user);
	}
	
	@Override
	public void updateEmail(int user_id, String email)
			throws ObjectNotFoundException {
		Session session = this.sessionFactory.getCurrentSession();
		
		User user = (User) session.load(User.class, user_id);
		user.setEmail(email);
		session.update(user);		
	}
	
	@Override
	public void updateOrSaveUserInformation(int user_id,
			UserInformation userInformation) throws ObjectNotFoundException {
		Session session = this.sessionFactory.getCurrentSession();
	
		User user = (User) session.get(User.class, user_id);
		
		
	    UserInformation userInfo = user.getUserInformation();
		userInfo.setAge(userInformation.getAge());
		userInfo.setInterest(userInformation.getInterest());
		userInfo.setProgrammingSkill(userInformation.getProgrammingSkill());
		userInfo.setSex(userInformation.getSex());
		userInfo.setSurname(userInformation.getSurname());
		userInfo.setUser(user);
		user.setUserInformation(userInfo);
		
	    session.merge(user);
	}

	@Override
	public boolean checkPassword(int user_id, String CurrentPassword) {
		Session session = this.sessionFactory.getCurrentSession();
		
		User user  = (User) session.load(User.class, user_id);
		String passwordUser = user.getPassword();
		
		boolean passwordMatch = passwordEncoder.matches(CurrentPassword, passwordUser);
		return passwordMatch;
	}

	@Override
	public void saveUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		
		
		session.persist(user);
		
		RoleUser roleUser = new RoleUser();
		roleUser.setAuthority("ROLE_USER");
		roleUser.setLogin(user.getLogin());
		roleUser.setUser(user);
		
		user.getRoleUser().add(roleUser);
		
		session.persist(roleUser);
	}

	@Override
	public boolean checkUserVerify(int user_id, int active_code)
			throws ObjectNotFoundException {
		
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, user_id);
		
		if(user.getActive_cod() != active_code){
			return false;
		}
		return true;
	}

	@Override
	public void setEnabledTrue(int user_id) throws HibernateException {
		Session session = this.sessionFactory.getCurrentSession();
		
		User user = (User) session.get(User.class, user_id);
		user.setEnabled(true);
		
		session.update(user);
	}
}


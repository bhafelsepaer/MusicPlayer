package adrian.example.musicplayer.service.user;


import adrian.example.musicplayer.model.User.User;

public interface UserService {

	/**
	 * Retrive a <code>User</code> from data store by login.
	 * 
	 * @param login the login to search for
	 * @return the <code>User</user> if found 
	 * null if not found
	 */
	User findLogin(String login);
	
	/**
	 * Save new User to database
	 * 
	 * @param user the user to save who 
	 * passed succesful registration
	 * 
	 */
	void saveUser(User user);
	
	/**
	 * Generate random code for user
	 * 
	 * @return random int
	 */
	int active_code();
	
	
}

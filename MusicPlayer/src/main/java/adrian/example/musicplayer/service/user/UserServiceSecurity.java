package adrian.example.musicplayer.service.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import adrian.example.musicplayer.dao.user.UserDao;
import adrian.example.musicplayer.model.User.RoleUser;
import adrian.example.musicplayer.model.User.User;

@Service("userServiceSecurity")
public class UserServiceSecurity implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		try {
		 User user = this.userDao.findLogin(username);
		 List<GrantedAuthority> authorites = buildUserAuthority(user.getRoleUser());
		 
		 return convertionUseDetails(user,authorites);
		}catch(NullPointerException message){
			throw new UsernameNotFoundException("user " + username +  "dont exist");
		}
	}
   
	//Convertions adrian.example.musicplayer.model.User.User to
	// org.springframework.security.core.userdetails.User
	private org.springframework.security.core.userdetails.User convertionUseDetails(User user,
			List<GrantedAuthority> authorites) {
		
		return new org.springframework.security.core.userdetails.User(
				user.getLogin(), user.getPassword(),user.isEnabled(),
				true, true, true, authorites);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<RoleUser> roleUser) {
		
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		
		for(RoleUser user : roleUser){
			setAuths.add(new SimpleGrantedAuthority(user.getAuthority()));
		}
		
		List<GrantedAuthority> resultAuthority = new ArrayList<GrantedAuthority>(setAuths);
		
		return resultAuthority;
	}
	
}

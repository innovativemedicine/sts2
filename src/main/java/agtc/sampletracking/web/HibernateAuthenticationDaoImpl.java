package agtc.sampletracking.web;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import agtc.sampletracking.dao.UserDAO;
import agtc.sampletracking.model.User;
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.AuthenticationDao;
import net.sf.acegisecurity.providers.dao.UsernameNotFoundException;

public class HibernateAuthenticationDaoImpl 
				implements AuthenticationDao{
	
	UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDetails loadUserByUsername(String username)
						throws UsernameNotFoundException, 
						DataAccessException {
		//userDAO = (UserDAO) new UserDAOHbImpl();
		System.out.println("User Name="+username);
		User user = userDAO.getUser(username);
		if (user==null) {
			throw new UsernameNotFoundException("User: "+username+" not Found");
		}
		Integer userid = user.getUserId();
		Set s = userDAO.findRolesByUserId(userid);
		//boolean b = s.addAll(userDAO.findRolesByUserId(userid));
		//if(!b) throw new UsernameNotFoundException("can not get role");
		user.setROLESs(s);
		 
		return user;
	}
	
	
}

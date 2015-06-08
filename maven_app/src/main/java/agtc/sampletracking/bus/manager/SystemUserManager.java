package agtc.sampletracking.bus.manager;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;

public class SystemUserManager {
	private Log log = LogFactory.getLog(SystemUserManager.class);
	private UserDAO userDAO;
	private RoleDAO roleDAO;
	private UserRolesDAO userRoleDAO;
	private List  users;
	private List roles;
	private boolean refresh = false;
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public List getRoles() {
		roles = roleDAO.getRoles();
		return roles;
	}
	public void setRoles(List roles) {
		this.roles = roles;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public UserRolesDAO getUserRoleDAO() {
		return userRoleDAO;
	}
	public void setUserRoleDAO(UserRolesDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	public List getUsers() {
		users = userDAO.getUsers();
		Iterator it = users.iterator();
		while(it.hasNext()){
			User user = (User)it.next();
			user.setROLESs(userDAO.findRolesByUserId(user.getUserId()));
		}
		return users;
	}
	public void setUsers(List users) {
		this.users = users;
	}
	
	public void saveUser(User stock) throws Exception {
		userDAO.saveUser(stock);
		refresh = true;
	}
	public void saveUserRoles(User user, String[] roles){
		//delete old user-roles relationship
		userRoleDAO.deleteUserRoles(user.getUserId());
		
		//create new user-roles relationship
		for(int i=0;i<roles.length;i++){
			Roles r = roleDAO.getRole(new Integer(roles[i]));
			UserRoles ur = new UserRoles();
			ur.setRole(r);
			ur.setUser(user);
			userRoleDAO.saveUserRoles(ur);
		}
		
		refresh = true;
	}
}

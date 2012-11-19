package agtc.sampletracking.dao;

import java.util.List;

import org.hibernate.HibernateException;

import agtc.sampletracking.model.Roles;
import agtc.sampletracking.model.User;

public interface RoleDAO {
	public List getRoles();
	public Roles getRole(Integer userId)throws HibernateException;
	 
	public void saveRoles(Roles roles);
	public void removeRoles(Integer roleId); 
}

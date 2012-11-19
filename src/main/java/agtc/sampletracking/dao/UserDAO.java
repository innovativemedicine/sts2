package agtc.sampletracking.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import agtc.sampletracking.model.User;

public interface UserDAO {
	public List getUsers(); 
	public User getUser(Integer userId) throws HibernateException;
	public User getUser(String userName); 
	public void saveUser(User user); 
    public Set findRolesByUserId(Integer userid ) throws HibernateException;
	public void removeUser(Integer userId);  
}
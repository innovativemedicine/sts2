package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.support.TransactionTemplate;

import agtc.sampletracking.dao.UserRolesDAO;
import agtc.sampletracking.model.User;
import agtc.sampletracking.model.UserRoles;

public class UserRolesDAOHbImpl 
			extends HibernateDaoSupport 
			implements UserRolesDAO {
	private Log log = LogFactory.getLog(UserRolesDAOHbImpl.class);

	public List getUserRoles() {
		// TODO Auto-generated method stub
		return  getHibernateTemplate().find("from UserRoles");
	}

	public UserRoles getUserRoles(Integer userRoleId) {
		// TODO Auto-generated method stub
		return (UserRoles)(getHibernateTemplate().get(UserRoles.class,userRoleId));
	}

	public void saveUserRoles(UserRoles userRoles) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(userRoles);	
	}

	public void removeUserRoles(UserRoles userRoles) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(userRoles);

	}
	
	//Save or update User_Roles with user_id and role_id'es
	//Jianan Xiao 2005-09-30
	public void deleteUserRoles(Integer userid){
		String hql=null;
		 		
		hql = "delete UserRoles where user_id = :userid";
		Session hbsession= getSession();
		int result = hbsession.createQuery(hql)
						.setString("userid",userid.toString())
						.executeUpdate();
		
	}
	
	public List findUserRoles(User user){
		return getHibernateTemplate().find("from UserRoles where User="+user);
	}

}

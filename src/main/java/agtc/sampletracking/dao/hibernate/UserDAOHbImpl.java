/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.dao.UserDAO;
import agtc.sampletracking.model.Roles;
import agtc.sampletracking.model.User;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.*;
import org.springframework.transaction.support.*;
import org.springframework.orm.hibernate3.*;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class UserDAOHbImpl extends STSBasicDAO implements UserDAO {
	private Log log = LogFactory.getLog(UserDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.UserDAO#getUsers()
	 */
	public List getUsers() {
		return  getHibernateTemplate().find("from User");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.UserDAO#getUser(java.lang.Integer)
	 */
	public User getUser(Integer userId) throws HibernateException {
		//User user = (User)(getHibernateTemplate().find("from User u where u.userId=?",userId).get(0));	
		//return user;
		return (User)(getHibernateTemplate().get(User.class,userId));
	}
	
	public User getUser(String userName) {
		List l =getHibernateTemplate().find("from User u where u.loginname=?",userName);
		if (l.size()<1)return null;
		User user = (User)(l.get(0));
		return user;
		//return (User)(getHibernateTemplate().get(User.class,userName));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.UserDAO#saveUser(agtc.sampletracking.model.User)
	 */
	public void saveUser(User user) {
		getHibernateTemplate().saveOrUpdate(user);
		if(log.isDebugEnabled()){
			log.debug("user ID set to :" + user.getUserId());
		}

	}

	/*Find user's roles by user_id
	 * Using hibernate sql query
	 * Jianan Xiao 2005-09-27
	 * */
    public Set findRolesByUserId(Integer userid ) throws HibernateException
    {
      final String hql = "select {aTable.*} from USER_ROLES ur,"
    	  			+" ROLES aTable WHERE ur.USER_ID= '"
                    +userid+"' and ur.ROLE_ID=aTable.ROLE_ID ";
      //hql = "from Roles where id in (select ur.role.id from UserRoles ur where ur.user.userId=?)";
      //List objects = getHibernateTemplate().find(hql,userid);
      
      /*
      List objects = getSession().
      					createSQLQuery(hql).
      					addEntity("aTable", Roles.class).
      					list();
      */
      List objects = (List)
      getHibernateTemplate().execute(
    		  new HibernateCallback(){
    			  public Object doInHibernate(Session session) throws HibernateException{
    				  List result=session.createSQLQuery(hql).addEntity("aTable", Roles.class).list();
    				  return result;
    			  }
    		  }
    		  );
      HashSet hs = new HashSet();
      hs.addAll(objects);
      return hs;
    }


	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.UserDAO#removeUser(java.lang.Integer)
	 */
	public void removeUser(Integer userId) {
		Object user = getHibernateTemplate().load(User.class,userId);
		getHibernateTemplate().delete(user);

	}

}

package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import agtc.sampletracking.dao.RoleDAO;
import agtc.sampletracking.model.Roles;

public class RoleDAOHbImpl extends STSBasicDAO implements RoleDAO {
	private Log log = LogFactory.getLog(RoleDAOHbImpl.class);
	
	public List getRoles(){
		return getHibernateTemplate()
				.find("from Roles r order by r.name");
	}
	public Roles getRole(Integer roleId)
		throws HibernateException {
		return (Roles)(getHibernateTemplate().get(Roles.class,roleId));
	}
	 
	public void saveRoles(Roles roles){
		getHibernateTemplate().saveOrUpdate(roles);
		if(log.isDebugEnabled()){
			log.debug("run ID set to :" + roles.getId());
		}

	}
	public void removeRoles(Integer roleId){
		Object role = getHibernateTemplate().load(Roles.class,roleId);
		getHibernateTemplate().delete(role);
	}
}

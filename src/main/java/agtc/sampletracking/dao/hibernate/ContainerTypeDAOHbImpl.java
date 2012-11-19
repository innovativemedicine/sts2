/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.dao.ContainerTypeDAO;
import agtc.sampletracking.model.ContainerType;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ContainerTypeDAOHbImpl
	extends HibernateDaoSupport
	implements ContainerTypeDAO {
	private Log log = LogFactory.getLog(ContainerTypeDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ContainerTypeDAO#getContainerTypes()
	 */
	public List getContainerTypes() {
		return  getHibernateTemplate().find("select c from ContainerType c order by c.name");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ContainerTypeDAO#getContainerType(java.lang.Integer)
	 */
	public ContainerType getContainerType(Integer containerTypeId) {
		return (ContainerType)(getHibernateTemplate().get(ContainerType.class,containerTypeId));
	}
	
	public ContainerType getContainerType(String name) {
		return (ContainerType)(getHibernateTemplate().find("from ContainerType c where c.name=?",name).get(0));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ContainerTypeDAO#saveContainerType(agtc.sampletracking.model.ContainerType)
	 */
	public void saveContainerType(ContainerType containerType) throws Exception {
		getHibernateTemplate().saveOrUpdate(containerType);
		if(log.isDebugEnabled()){
			log.debug("containertype ID set to :" + containerType.getContainerTypeId());
		}
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ContainerTypeDAO#removeContainerType(java.lang.Integer)
	 */
	public void removeContainerType(Integer containerTypeId) {
		Object containerType = getHibernateTemplate().load(ContainerType.class,containerTypeId);
		getHibernateTemplate().delete(containerType);
	}

}

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
import agtc.sampletracking.ConstantInterface;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ContainerTypeDAOHbImpl
	extends HibernateDaoSupport
	implements ContainerTypeDAO, ConstantInterface {
	private Log log = LogFactory.getLog(ContainerTypeDAOHbImpl.class);

	public List getContainerTypes() {
		return  getHibernateTemplate().find("select c from ContainerType c order by c.name");
	}

	public List getPlateTypes() {
		return getHibernateTemplate().find("from ContainerType c where c.name like '" + PLATE + "%' order by c.id ");
	}
	
	public ContainerType getContainerType(Integer containerTypeId) {
		return (ContainerType)(getHibernateTemplate().get(ContainerType.class,containerTypeId));
	}
	
	public ContainerType getContainerType(String name) {
		return (ContainerType)(getHibernateTemplate().find("from ContainerType c where c.name=?",name).get(0));
	}

	public void saveContainerType(ContainerType containerType) throws Exception {
		getHibernateTemplate().saveOrUpdate(containerType);
		if(log.isDebugEnabled()){
			log.debug("containertype ID set to :" + containerType.getContainerTypeId());
		}
	}

	public void removeContainerType(Integer containerTypeId) {
		Object containerType = getHibernateTemplate().load(ContainerType.class,containerTypeId);
		getHibernateTemplate().delete(containerType);
	}

}

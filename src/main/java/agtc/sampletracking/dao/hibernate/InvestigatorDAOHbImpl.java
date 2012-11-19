/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.InvestigatorDAO;
import agtc.sampletracking.model.Investigator;
import java.util.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class InvestigatorDAOHbImpl extends HibernateDaoSupport implements InvestigatorDAO {

	private Log log = LogFactory.getLog(InvestigatorDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InvestigatorDAO#getInvestigators()
	 */
	public List getInvestigators() {
		return  getHibernateTemplate().find("select i from Investigator i order by i.name.fname");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InvestigatorDAO#getInvestigator(java.lang.Integer)
	 */
	public Investigator getInvestigator(Integer investigatorId) {
		return (Investigator)(getHibernateTemplate().get(Investigator.class,investigatorId));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InvestigatorDAO#saveInvestigator(agtc.sampletracking.model.Investigator)
	 */
	public void saveInvestigator(Investigator investigator) throws Exception {
		getHibernateTemplate().saveOrUpdate(investigator);
		if(log.isDebugEnabled()){
			log.debug("investigator ID set to :" + investigator.getInvestigatorId());
		}

	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.InvestigatorDAO#removeInvestigator(java.lang.Integer)
	 */
	public void removeInvestigator(Integer investigatorId) {
		Object investigator = getHibernateTemplate().load(Investigator.class,investigatorId);
		getHibernateTemplate().delete(investigator);
	}

}

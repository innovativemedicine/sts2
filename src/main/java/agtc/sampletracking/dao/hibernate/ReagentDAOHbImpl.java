/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.*;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import agtc.sampletracking.dao.ReagentDAO;
import agtc.sampletracking.model.Reagent;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.ReagentSearchFields;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ReagentDAOHbImpl extends STSBasicDAO implements ReagentDAO {
	private Log log = LogFactory.getLog(ReagentDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ReagentDAO#getReagents()
	 */
	public List getReagents() {
		return  getHibernateTemplate().find("select r from Reagent r order by r.name");
	}

	
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ReagentDAO#getReagent(java.lang.Integer)
	 */
	public Reagent getReagent(Integer reagentId) {
		return (Reagent)(getHibernateTemplate().get(Reagent.class,reagentId));
	}

	public List getReagents(List crtList,List lgcList){
		Session session = getSession();
		Criteria crt = session.createCriteria(Reagent.class);
		
		
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				ReagentSearchFields.getExpression(crt,searchCommand);
				if(lgcList.size()>i){
					String logicalOperator = (String)lgcList.get(i);
					getExpression(crt,logicalOperator);
				}
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		ReagentSearchFields.getExpression(crt,searchCommand);
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ReagentDAO#saveReagent(agtc.sampletracking.model.Reagent)
	 */
	public void saveReagent(Reagent reagent) throws Exception {
		getHibernateTemplate().saveOrUpdate(reagent);
		if(log.isDebugEnabled()){
			log.debug("reagent ID set to :" + reagent.getReagentId());
		}

	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ReagentDAO#removeReagent(java.lang.Integer)
	 */
	public void removeReagent(Integer reagentId) {
		Object reagent = getHibernateTemplate().load(Reagent.class,reagentId);
		getHibernateTemplate().delete(reagent);

	}

}

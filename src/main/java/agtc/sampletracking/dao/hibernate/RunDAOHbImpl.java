/*
 * Created on Dec 3, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.RunDAO;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.RunSearchFields;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RunDAOHbImpl extends STSBasicDAO implements RunDAO {
	private Log log = LogFactory.getLog(RunDAOHbImpl.class);
	public List getRuns(){
		return  getHibernateTemplate().find("from Run r order by r.project.name,r.runDate desc");
	}

	public List getRuns(List crtList,List lgcList){
//		log.debug("the whereString is " + whereString);
		Session session = getSession();
		Criteria crt = session.createCriteria(Run.class);
		
		
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				RunSearchFields.getExpression(crt,searchCommand);
				if(lgcList.size()>i){
					String logicalOperator = (String)lgcList.get(i);
					getExpression(crt,logicalOperator);
				}
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		RunSearchFields.getExpression(crt,searchCommand);
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}

	public Run getRun(Integer runId){
		return (Run)(getHibernateTemplate().get(Run.class,runId));
	}
	public void saveRun(Run run){
		getHibernateTemplate().saveOrUpdate(run);
		if(log.isDebugEnabled()){
			log.debug("run ID set to :" + run.getRunId());
		}
	}
	public void removeRun(Integer runId){
		Object run = getHibernateTemplate().load(Run.class,runId);
		getHibernateTemplate().delete(run);

	}
}

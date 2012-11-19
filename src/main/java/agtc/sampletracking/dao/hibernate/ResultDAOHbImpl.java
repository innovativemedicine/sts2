/*
 * Created on Oct 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;


import agtc.sampletracking.dao.ResultDAO;
import agtc.sampletracking.model.Result;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.ResultSearchFields;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ResultDAOHbImpl extends STSBasicDAO implements ResultDAO {
	private Log log = LogFactory.getLog(ResultDAOHbImpl.class);
	
	
	public List getResults(List crtList,List lgcList){
//		log.debug("the whereString is " + whereString);
		Session session = getSession();
		Criteria crt = session.createCriteria(Result.class);
				
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				
				ResultSearchFields.getExpression(crt,searchCommand);
				 
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		ResultSearchFields.getExpression(crt,searchCommand);
		crt.addOrder(Order.asc("intSampleId"));		
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}
	
	public List getResults(String sql){
		return getHibernateTemplate().find(sql);
	}
	
	public List getResultsByRunId(int runId){
		Session session = getSession();
		String queryString = "from Result r where r.run.runId = :runId ";
		return session.createQuery(queryString).setInteger("runId",runId).list();
	}
	
	public Result getResult(Integer resultId){
		return (Result)(getHibernateTemplate().get(Result.class,resultId));
	}
	
	public void saveResult(Result result){
		getHibernateTemplate().saveOrUpdate(result);
		if(log.isDebugEnabled()){
			log.debug("result ID set to :" + result.getResultId());
		}
	}
	public void removeResult(Integer resultId){
		Object result = getHibernateTemplate().load(Result.class,resultId);
		getHibernateTemplate().delete(result);

	}
	
	public void removeResultByAssay(Integer runId, String[] assays){
		String sql = " from Result r where r.run.runId="+runId
						+ " and r.assay.assayId in (";
		for ( int i=0; i < assays.length; i++){
			if (i==0)
				sql = sql + assays[i];
			else sql = sql+"," + assays[i];
		}
		sql=sql+")";
		List results = getHibernateTemplate().find(sql);
		Iterator it = results.iterator();
		while(it.hasNext()){
			Result r = (Result)it.next();
			getHibernateTemplate().delete(r);
		}
	}

	public void refreshResult(Result result){
		getHibernateTemplate().refresh(result);
	}
	
	
}

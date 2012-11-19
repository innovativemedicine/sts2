/*
 * Created on Sep 29, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.AssayDAO;
import agtc.sampletracking.model.Assay;
import agtc.sampletracking.web.searchFields.*;
import agtc.sampletracking.web.command.*;
import org.hibernate.*;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AssayDAOHbImpl extends STSBasicDAO implements AssayDAO {
	private Log log = LogFactory.getLog(AssayDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.AssayDAO#getAssays()
	 */

	public List getAssays() {
		return  getHibernateTemplate().find("from Assay a order by a.name");
		
	}
	
	public List getAssays(Integer testId) {
		return (new LinkedList());
	}
	
	public List getAssays(List crtList,List lgcList) {
		Session session = getSession();
		Criteria crt = session.createCriteria(Assay.class);
		
		
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				AssaySearchFields.getExpression(crt,searchCommand);
				if(lgcList.size()>i){
					String logicalOperator = (String)lgcList.get(i);
					getExpression(crt,logicalOperator);
				}
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		AssaySearchFields.getExpression(crt,searchCommand);
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}
	
	public Assay getAssayByName(String name){
	    log.debug("the assay name is " +name);
	    //return  (Assay)(getHibernateTemplate().find("from Assay t where t.name='"+name+"'").get(0));
	    List results=getHibernateTemplate().find("from Assay t where t.name='"+name+"'");
	    if (results.size()>0){
	    	Assay assay=(Assay)results.get(0);
	    	return assay;
	    }else return null;
	}

	//Get Assays List belong to a RUN
	// Jianan Xiao 2006-03-01
	public List getAssayByRunId(Integer id){
	    log.debug("the run id is " +id);
	    //return  (Assay)(getHibernateTemplate().find("from Assay t where t.name='"+name+"'").get(0));
	    List results=getHibernateTemplate().find("from Assay t where t.assayId in "
	    		+"(select distinct r.assay.assayId from Result r where r.run.runId="+id+")");
	    if (results.size()>0){	    	 
	    	return results;
	    }else return null;
	}
	
	public Assay getAssay(Integer assayId) {
		return (Assay)(getHibernateTemplate().get(Assay.class,assayId));
	}

	
	public void saveAssay(Assay assay)throws Exception {
		getHibernateTemplate().saveOrUpdate(assay);
	}

	public void removeAssay(Integer assayId) {
		Object assay = getHibernateTemplate().load(Assay.class,assayId);
		getHibernateTemplate().delete(assay);
	
	}

}

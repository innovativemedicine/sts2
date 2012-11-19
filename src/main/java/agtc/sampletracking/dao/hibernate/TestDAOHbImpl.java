/*
 * Created on Oct 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.TestDAO;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.TestSearchFields;

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
public class TestDAOHbImpl extends STSBasicDAO implements TestDAO {
	private Log log = LogFactory.getLog(TestDAOHbImpl.class);
	public List getTests(){
		return  getHibernateTemplate().find("from Test t order by t.name");
	}
	
	public List getTests(List crtList,List lgcList){
		Session session = getSession();
		Criteria crt = session.createCriteria(Test.class);
		
		
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				TestSearchFields.getExpression(crt,searchCommand);
				if(lgcList.size()>i){
					String logicalOperator = (String)lgcList.get(i);
					getExpression(crt,logicalOperator);
				}
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		TestSearchFields.getExpression(crt,searchCommand);
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}
	
	public Test getTest(String  testName){
		return (Test)(getHibernateTemplate().find("from Test t where t.=?",testName).get(0));

	}
	public Test getTest(Integer testId){
		return (Test)(getHibernateTemplate().get(Test.class,testId));
	}
	public void saveTest(Test test) throws Exception {
		getHibernateTemplate().saveOrUpdate(test);
		if(log.isDebugEnabled()){
			log.debug("test ID set to :" + test.getTestId());
		}
	}
	public void removeTest(Integer testId){
		Object test = getHibernateTemplate().load(Test.class,testId);
		getHibernateTemplate().delete(test);

	}
}

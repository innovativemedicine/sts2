/*
 * Created on Apr 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.web.controller.SamplesSearchController;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class STSBasicDAO extends HibernateDaoSupport{
	private Log log = LogFactory.getLog(SamplesSearchController.class);
	protected void getExpression(Criteria crt,String logicalOperator){
		if(logicalOperator.equals("AND")){
			log.debug("add and restriction");
			crt.add(Restrictions.conjunction());
		}else if(logicalOperator.equals("OR")){
			log.debug("add or restriction");
			crt.add(Restrictions.disjunction());
		}
		
	}
}

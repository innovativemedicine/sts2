/*
 * Created on Apr 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class STSBasicDAO extends HibernateDaoSupport{
	protected void getExpression(Criteria crt,String logicalOperator){
		if(logicalOperator.equals("AND")){
			crt.add(Restrictions.conjunction());
		}else if(logicalOperator.equals("OR")){
			crt.add(Restrictions.disjunction());
		}
		
	}
}

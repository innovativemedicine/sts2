/*
 * Created on Oct 19, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.ContainerDAO;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.ContainerSearchFields;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import agtc.sampletracking.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ContainerDAOHbImpl
	extends STSBasicDAO
	implements ContainerDAO, ConstantInterface{
	private Log log = LogFactory.getLog(ContainerDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.StockContainerDAO#getStockContainers()
	 */
	public List getContainers() {
		return  getHibernateTemplate().find("from Container c left join fetch c.location left join fetch c.project left join fetch c.containerType order by c.name");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.StockContainerDAO#getStockContainer(java.lang.Integer)
	 */
	public Container getContainer(Integer stockId) {
		return (Container)(getHibernateTemplate().get(Container.class,stockId));
	}
	
	public Container getContainer(String name) {
		return (Container)(getHibernateTemplate().find("from Container c where c.name=?",name).get(0));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.StockContainerDAO#saveStockContainer(agtc.sampletracking.model.StockContainer)
	 */
	public void saveContainer(Container container) throws Exception {
		getHibernateTemplate().saveOrUpdate(container);
		if(log.isDebugEnabled()){
			log.debug("container ID set to :" + container.getContainerId());
		}

	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.StockContainerDAO#removeStockContainer(java.lang.Integer)
	 */
	public void removeContainer(Integer stockId) {
		Object container = getHibernateTemplate().load(Container.class,stockId);
		getHibernateTemplate().delete(container);

	}
	
	public List getContainers(List crtList,List lgcList){
		Session session = getSession();
		Criteria crt = session.createCriteria(Container.class);
		
		
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				ContainerSearchFields.getExpression(crt,searchCommand);
				if(lgcList.size()>i){
					String logicalOperator = (String)lgcList.get(i);
					getExpression(crt,logicalOperator);
				}
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		ContainerSearchFields.getExpression(crt,searchCommand);
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}
	
	public List getAllReagentBoxes(){
		return getHibernateTemplate().find("from Container c where c.containerType.name like '" + REAGENT_BOX + "%'");
	}
	
	public List getAllSampleBoxes(){
		return getHibernateTemplate().find("from Container c where c.containerType.name like '" + SAMPLE_BOX + "%'");
	}
	
	public List getAllPlates(){
		return getHibernateTemplate().find("from Container c where c.containerType.name like '" 
				+ PLATE + "%' order by c.name ");
	}

}

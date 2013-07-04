/*
 * Created on Oct 19, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import agtc.sampletracking.dao.ContainerDAO;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.ContainerSearchFields;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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

	public List getContainers() {
		return  getHibernateTemplate().find("from Container c left join fetch c.location left join fetch c.project left join fetch c.containerType order by c.name");
	}

	public Container getContainer(Integer stockId) {
		return (Container)(getHibernateTemplate().get(Container.class,stockId));
	}
	
	public Container getContainer(String name) {
		List result = getHibernateTemplate().find("from Container c where c.name=?",name);
		
		if (result.isEmpty()) {
			return null;
		}
		else {
			return (Container)result.get(0);
		}
	}
	
	

	public String getLargestPlateId(String platePrefix)
	{
		Session session = getSession();
		Criteria crit = session.createCriteria(Container.class).addOrder(Order.desc("containerId"));
		crit.add(Restrictions.like("name",platePrefix + "%"));
		crit.setMaxResults(1);
		
		List results = crit.list();
		
		if(results.size() < 1)
		{
			return platePrefix + "0";
		}
		else
		{
			Container container = (Container) results.get(0);
			return container.getName();
		}
	}

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
	
	public List getAllBoxes(){
		return getHibernateTemplate().find("from Container c where c.containerType.name like '%" + BOX + "'");
	}
	
	public List getAllPlates(){
		return getHibernateTemplate().find("from Container c where c.containerType.name like '" 
				+ PLATE + "%' order by c.name ");
	}
	
	public List simpleSearchContainers(List containerIds, List containerTypeIds, List projectIds){
		Session session = getSession();
		List patients = new ArrayList();
						
		Criteria crt = session.createCriteria(Container.class);
		crt.setFetchMode("project", FetchMode.JOIN);
		crt.setFetchMode("containerType", FetchMode.JOIN);

		if(!containerIds.isEmpty())
		{
			crt.add(Restrictions.in("name",containerIds));
		}
		if(!containerTypeIds.isEmpty())
		{
			crt.add(Restrictions.in("containerType.containerTypeId",containerTypeIds));
		}
		if(!projectIds.isEmpty())
		{
			crt.add(Restrictions.in("project.projectId",projectIds));
		}

		return crt.list();
	}
	
	public List<Container> simpleSearchContainers(String containerIdFrom, String containerIdTo, String extIdFrom, String extIdTo, List containerTypeIds, List projectIds)
	{
		Session session = getSession();
				
		Criteria crt = session.createCriteria(Container.class);
		crt.setFetchMode("containerType", FetchMode.JOIN);
		crt.setFetchMode("project", FetchMode.JOIN);
		crt.setFetchMode("location", FetchMode.JOIN);

		if(!containerIdFrom.isEmpty() && containerIdTo.isEmpty())
		{
			crt.add(Restrictions.like("name",containerIdFrom, MatchMode.START));
		}
		else if(!containerIdTo.isEmpty())
		{
			crt.add(Restrictions.between("name",containerIdFrom, containerIdTo));
		}
		
		if(!extIdFrom.isEmpty() && extIdTo.isEmpty())
		{
			crt.add(Restrictions.like("extContainerId",extIdFrom, MatchMode.START));
		}
		else if(!extIdTo.isEmpty())
		{
			crt.add(Restrictions.between("extContainerId",extIdFrom,extIdTo));
		}
		
		if(!containerTypeIds.isEmpty())
		{
			crt.add(Restrictions.in("containerType.containerTypeId",containerTypeIds));
		}
		if(!projectIds.isEmpty())
		{
			crt.add(Restrictions.in("project.projectId",projectIds));
		}

		return crt.list();
	}	
	

}

/*
 * Created on Jan 21, 2005
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.SamplesInContainerDAO;
import agtc.sampletracking.model.SamplesInContainer;

public class SamplesInContainerDAOHbImpl extends HibernateDaoSupport implements
		SamplesInContainerDAO {

	public SamplesInContainer getSamplesInContainer(Integer sicId) {
		return (SamplesInContainer)(getHibernateTemplate().get(SamplesInContainer.class,sicId));
	}

	public void saveSamplesInContainer(SamplesInContainer result) {
		getHibernateTemplate().saveOrUpdate(result);
	}

	public void removeSamplesInContainer(Integer sicId) {
		Object sic = getHibernateTemplate().load(SamplesInContainer.class,sicId);
		getHibernateTemplate().delete(sic);

	}
	
	public List getSamplesInContainersByContainer(Integer containerId){
		return getHibernateTemplate().find("from SamplesInContainer s where s.container.containerId="+containerId);
	}
	

	public List getWellsInContainersByContainer(Integer containerId) {
		String query = "select s.well from SamplesInContainer s where s.container.containerId=?";
		List results = getHibernateTemplate().find(query, containerId);
		
		return results;
	}
	
	public List getSamplesInContainersInBySample(Integer sampleId){
		return getHibernateTemplate().find("from SamplesInContainer s where s.operation='I' and s.sample.sampleId="+sampleId);
	}
	
	public boolean containsSample(Integer sampleId){
		List results = getHibernateTemplate().find("from SamplesInContainer s where s.operation='I' and s.sample.sampleId="+sampleId);
		if (results.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void removeSamplesInContainersByContainer(Integer containerId){
		List deletedSics = getHibernateTemplate().find("select s from SamplesInContainer s where s.container.containerId="+containerId);
		
		getHibernateTemplate().deleteAll(deletedSics);
	}

}

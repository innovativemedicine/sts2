/*
 * Created on Jan 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.SamplesInContainerDAO;
import agtc.sampletracking.model.Assay;
import agtc.sampletracking.model.SamplesInContainer;
import org.hibernate.type.*;

/**
 *   Gloria
 *   
 */
public class SamplesInContainerDAOHbImpl extends HibernateDaoSupport implements
		SamplesInContainerDAO {

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplesInContainerDAO#getSamplesInContainer(java.lang.Integer)
	 */
	public SamplesInContainer getSamplesInContainer(Integer sicId) {
		return (SamplesInContainer)(getHibernateTemplate().get(SamplesInContainer.class,sicId));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplesInContainerDAO#saveSamplesInContainer(agtc.sampletracking.model.SamplesInContainer)
	 */
	public void saveSamplesInContainer(SamplesInContainer result) {
		getHibernateTemplate().saveOrUpdate(result);

	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplesInContainerDAO#removeSamplesInContainer(java.lang.Integer)
	 */
	public void removeSamplesInContainer(Integer sicId) {
		Object sic = getHibernateTemplate().load(SamplesInContainer.class,sicId);
		getHibernateTemplate().delete(sic);

	}
	
	public List getSamplesInContainersByContainer(Integer containerId){
		return getHibernateTemplate().find("from SamplesInContainer s where s.container.containerId="+containerId);
	}
	
	public List getSamplesInContainersInBySample(Integer sampleId){
		return getHibernateTemplate().find("from SamplesInContainer s where s.operation='I' and s.sample.sampleId="+sampleId);
	}
	
	public void removeSamplesInContainersByContainer(Integer containerId){
		List deletedSics = getHibernateTemplate().find("select s from SamplesInContainer s where s.container.containerId="+containerId);
		
		getHibernateTemplate().deleteAll(deletedSics);
	}

}

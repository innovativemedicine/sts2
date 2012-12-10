/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.SampleTypeDAO;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.SampleType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SampleTypeDAOHbImpl
	extends HibernateDaoSupport
	implements SampleTypeDAO {
	private Log log = LogFactory.getLog(SampleTypeDAOHbImpl.class);
		
	public List getSampleTypes() {
		return  getHibernateTemplate().find("select s from SampleType s order by s.name");
	}

	public SampleType getSampleType(Integer sampleTypeId) {
		return (SampleType)(getHibernateTemplate().get(SampleType.class,sampleTypeId));
	}
	
	public SampleType getSampleTypeBySuffix(String suffix){
		List results = getHibernateTemplate().find("select s from SampleType s where s.suffix='"+suffix+"'");
		if(results.size()>0){
			return (SampleType)results.get(0);
		}else{
			return null;
		}
	}
	
	public SampleType getSampleTypeByName(String name){
		List results = getHibernateTemplate().find("select s from SampleType s where s.name='"+name+"'");
		if(results.size()>0){
			return (SampleType)results.get(0);
		}else{
			return null;
		}
	}

	public void saveSampleType(SampleType sampelType) throws Exception {
		getHibernateTemplate().saveOrUpdate(sampelType);
	}
	
	public void updateSampleType(SampleType sampleType){
		getHibernateTemplate().update(sampleType);
	}
 
	 public void removeSampleType(Integer sampleTypeId) {
		Object sampleType = getHibernateTemplate().load(SampleType.class,sampleTypeId);
		getHibernateTemplate().delete(sampleType);
	}

}

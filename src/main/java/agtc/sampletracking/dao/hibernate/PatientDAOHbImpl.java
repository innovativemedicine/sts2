/*
 * Created on Apr 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.PatientDAO;
import agtc.sampletracking.model.Assay;
import agtc.sampletracking.model.Patient;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PatientDAOHbImpl extends HibernateDaoSupport implements PatientDAO {
	private Log log = LogFactory.getLog(PatientDAOHbImpl.class);
	public Patient getPatient(String intSampleId){
		intSampleId = intSampleId.toUpperCase();
		return (Patient)(getHibernateTemplate().get(Patient.class,intSampleId));
	}
	public boolean containsPatient(String intSampleId){
		intSampleId = intSampleId.toUpperCase();
		if((getHibernateTemplate().get(Patient.class,intSampleId))==null){
			return false;
		}else{
			return true;
		}
		
	}
	public void savePatient(Patient patient){
		patient.setIntSampleId(patient.getIntSampleId().toUpperCase());
		getHibernateTemplate().save(patient);
		log.debug("the patientId set to " + patient.getIntSampleId());
	}
	public void updatePatient(Patient patient){
		getHibernateTemplate().update(patient);
	}
	public void removePatient(String intSampleId){
		Object patient = getHibernateTemplate().load(Patient.class,intSampleId);
		getHibernateTemplate().delete(patient);
	}
}

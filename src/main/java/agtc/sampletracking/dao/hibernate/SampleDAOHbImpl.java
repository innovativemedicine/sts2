/*
 * Created on Oct 5, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.SampleDAO;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.SampleType;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.web.searchFields.*;
import org.hibernate.criterion.*;
import org.hibernate.*;

/**
 * @author Hongjing
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SampleDAOHbImpl extends STSBasicDAO implements SampleDAO {
	private Log log = LogFactory.getLog(SampleDAOHbImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see agtc.sampletracking.dao.SampleDAO#getSamples()
	 */
	public List getSamples() {
		return getHibernateTemplate().find("from Sample");
	}

	public String getLargestSampleId(String intSamplePrefix) {
		Session session = getSession();
		String regex = "'^" + intSamplePrefix.toUpperCase() + "[0-9]+$'";
		String sql = "SELECT INT_SAMPLE_ID FROM sts.SAMPLE WHERE INT_SAMPLE_ID RLIKE "
				+ regex + " ORDER BY SAMPLE_ID DESC LIMIT 1;";

		Query query= session.createSQLQuery(sql).addScalar("INT_SAMPLE_ID", Hibernate.STRING);
		
		List results = query.list();

		if(results.isEmpty())
		{
			return intSamplePrefix.toUpperCase() + "0";
		}
		else
		{
			return results.get(0).toString();
		}

	}

	public List getSamples(List sampleIds, List sampleTypeSuffixes,
			Integer sampleDupNo) {
		Session session = getSession();
		Criteria crt = session.createCriteria(Sample.class);
		crt.add(Restrictions.in("patient.intSampleId", sampleIds));
		crt.add(Restrictions.in("sampleType.suffix", sampleTypeSuffixes));
		crt.add(Restrictions.eq("sampleDupNo", sampleDupNo));
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}

	public Sample getSample(String intSampleId, String sampleTypeSuffix,
			Integer sampleDupNo) {
		Session session = getSession();
		Criteria crt = session.createCriteria(Sample.class);
		crt.createAlias("sampleType", "sampleType");
		crt.add(Restrictions.eq("patient.intSampleId", intSampleId));
		crt.add(Restrictions.eq("sampleType.suffix", sampleTypeSuffix));
		crt.add(Restrictions.eq("sampleDupNo", sampleDupNo));
		log.debug(" the criteria is " + crt.toString());

		List result = crt.list();

		if (result.isEmpty()) {
			return null;
		} else {
			return (Sample) result.get(0);
		}
	}

	public Sample getSample(String intSampleId) {
		Session session = getSession();
		Criteria crt = session.createCriteria(Sample.class);
		crt.add(Restrictions.eq("patient.intSampleId", intSampleId));

		log.debug(" the criteria is " + crt.toString());

		List result = crt.list();

		if (result.isEmpty()) {
			return null;
		} else {
			return (Sample) result.get(0);
		}
	}

	public List simpleSearchSamples(List sampleIds, List sampleTypeIds,
			List projectIds) {
		Session session = getSession();
		List patients = new ArrayList();

		// Search for patients that are in projectId
		if (!projectIds.isEmpty()) {
			Criteria patientCrt = session.createCriteria(Patient.class);
			patientCrt = patientCrt.add(Restrictions.in("project.projectId",
					projectIds));
			patients = patientCrt.list();
		}

		Criteria crt = session.createCriteria(Sample.class);
		crt.setFetchMode("patient", FetchMode.JOIN);
		crt.setFetchMode("sampleType", FetchMode.JOIN);
		crt.setFetchMode("patient.project", FetchMode.JOIN);

		if (!sampleIds.isEmpty()) {
			crt.add(Restrictions.in("patient.intSampleId", sampleIds));
		}
		if (!sampleTypeIds.isEmpty()) {
			crt.add(Restrictions.in("sampleType.sampleTypeId", sampleTypeIds));
		}
		if (!projectIds.isEmpty()) {
			crt.add(Restrictions.in("patient", patients));
		}

		return crt.list();
	}

	public List<Sample> simpleSearchSamples(String sampleIdFrom,
			String sampleIdTo, String externalIdFrom, String externalIdTo, List sampleTypeIds, List projectIds) {
		Session session = getSession();

		Criteria crt = session.createCriteria(Sample.class);
		crt.setFetchMode("patient", FetchMode.JOIN).createAlias("patient", "patient");
		crt.setFetchMode("sampleType", FetchMode.JOIN);
		crt.setFetchMode("patient.project", FetchMode.JOIN).createAlias("patient.project", "patient.project");

		if (!sampleIdFrom.isEmpty() && sampleIdTo.isEmpty()) {
			crt.add(Restrictions.like("patient.intSampleId", sampleIdFrom,
					MatchMode.START));
		} else if (!sampleIdTo.isEmpty()) {
			crt.add(Restrictions.between("patient.intSampleId", sampleIdFrom,
					sampleIdTo));
		}
		
		if (!externalIdFrom.isEmpty() && externalIdTo.isEmpty()) {
			crt.add(Restrictions.like("patient.extSampleId", externalIdFrom,
					MatchMode.START));
		} else if (!sampleIdTo.isEmpty()) {
			crt.add(Restrictions.between("patient.extSampleId", externalIdFrom,
					externalIdTo));
		}
		
		if (!sampleTypeIds.isEmpty()) {
			crt.add(Restrictions.in("sampleType.sampleTypeId", sampleTypeIds));
		}
		if (!projectIds.isEmpty()) {
			crt.add(Restrictions.in("patient.project.projectId", projectIds));
		}

		return crt.list();
	}

	public List getExistingSampleTypes(String intSampleId) {
		intSampleId = intSampleId.toUpperCase();
		String query = "select distinct s.sampleType from Sample s where s.patient.intSampleId = ? ";
		return getHibernateTemplate().find(query, intSampleId);
	}

	public List getAllSampleTypes() {
		String query = "select s.sampleType from Sample s group by s.sampleType order by count(s.sampleId) desc";
		return getHibernateTemplate().find(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see agtc.sampletracking.dao.SampleDAO#getSample(java.lang.Integer)
	 */
	public Sample getSample(Integer sampleId) {

		return (Sample) (getHibernateTemplate().get(Sample.class, sampleId));
	}

	public Sample getSampleByIntSampleIdUniKey(String intId,
			SampleType sampleType, Integer sampleDupNo) {
		// log.debug("the intSample Id is " + intId);
		intId = intId.toUpperCase();
		List results = getHibernateTemplate().find(
				"from Sample s where s.patient.intSampleId='" + intId
						+ "' and s.sampleType.sampleTypeId="
						+ sampleType.getSampleTypeId() + " and s.sampleDupNo="
						+ sampleDupNo);
		if (results.size() > 0) {
			return (Sample) results.get(0);
		} else {
			return null;
		}
	}

	public List getSampleByIntSampleIdSampleType(String intId,
			SampleType sampleType) {
		intId = intId.toUpperCase();
		List results = getHibernateTemplate()
				.find("from Sample s left join fetch s.sampleType left join fetch s.patient where s.patient.intSampleId='"
						+ intId
						+ "' and s.sampleType.sampleTypeId="
						+ sampleType.getSampleTypeId());
		if (results.size() > 0) {
			return results;
		} else {
			return new ArrayList();
		}
	}

	/*
	 * Jianan Xiao 2005-09-09
	 */
	public List getSampleByIntSampleIdSampleType(String intId,
			String[] sampleType) {
		List results = null;
		intId = intId.toUpperCase();
		if (sampleType.length > 0) {
			String types = " (";
			for (int i = 0; i < sampleType.length; i++) {
				if (i > 0)
					types += ",";
				types += sampleType[i];
			}
			types += " )";
			results = getHibernateTemplate().find(
					"from Sample s left join fetch s.sampleType"
							+ " left join fetch s.patient"
							+ " where s.patient.intSampleId='" + intId
							+ "' and s.sampleType.sampleTypeId in" + types);
		} else {
			results = getSampleByIntSampleId(intId);
		}

		return results;
	}

	public List getSampleByIntSampleId(String intId) {
		intId = intId.toUpperCase();
		List results = getHibernateTemplate()
				.find("from Sample s left join fetch s.sampleType left join fetch s.patient where s.patient.intSampleId='"
						+ intId + "'");
		if (results.size() > 0) {
			return results;
		} else {
			return null;
		}
	}

	public boolean containsIntSampleId(String intSampleId) {
		intSampleId = intSampleId.toUpperCase();
		List result = getHibernateTemplate().find(
				"from Sample s where s.patient.intSampleId='" + intSampleId
						+ "'");
		if (result.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * agtc.sampletracking.dao.SampleDAO#saveSample(agtc.sampletracking.model
	 * .Sample)
	 */
	public void saveSample(Sample sample) throws Exception {

		sample.getPatient().setIntSampleId(
				sample.getPatient().getIntSampleId().toUpperCase());

		sample.getSampleType().setSampleTypeId(
				sample.getSampleType().getSampleTypeId());

		Integer sampleId = sample.getSampleId();
		if (sampleId.intValue() == -1) {
			if (sample.getSampleDupNo() == null) {
				sample.setSampleDupNo(new Integer(1));
			}
			getHibernateTemplate().save(sample);
		} else {
			getHibernateTemplate().update(sample);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see agtc.sampletracking.dao.SampleDAO#removeSample(java.lang.Integer)
	 */
	public void removeSample(Integer sampleId) {
		Object sample = getHibernateTemplate().load(Sample.class, sampleId);
		getHibernateTemplate().delete(sample);
	}

}

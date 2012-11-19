/*
 * Created on Oct 5, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.SampleDAO;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SampleType;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.web.searchFields.*;
import org.hibernate.criterion.*;
import org.hibernate.*;


/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SampleDAOHbImpl extends STSBasicDAO implements SampleDAO {
	private Log log = LogFactory.getLog(SampleDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SampleDAO#getSamples()
	 */
	public List getSamples() {
		return  getHibernateTemplate().find("from Sample");
	}
	
	public List getSamples(List sampleIds,List sampleTypeSuffixes,Integer sampleDupNo){
		Session session = getSession();
		Criteria crt = session.createCriteria(Sample.class);
		crt.createAlias("sampleType","sampleType");
		crt.add(Restrictions.in("patient.intSampleId",sampleIds));
		crt.add(Restrictions.in("sampleType.suffix",sampleTypeSuffixes));
		crt.add(Restrictions.eq("sampleDupNo",sampleDupNo));
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}
	
	
	
	public List searchSamples(List crtList,List lgcList) {
		//log.debug("the whereString is " + whereString);
		Session session = getSession();
		Criteria crt = session.createCriteria(Sample.class);
		
		
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				SampleSearchFields.getExpression(crt,searchCommand);
				/* or search is not supported yet
				if(lgcList.size()>i){
					String logicalOperator = (String)lgcList.get(i);
					getExpression(crt,logicalOperator);
				}
				*/
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		SampleSearchFields.getExpression(crt,searchCommand);
		crt.addOrder(Order.asc("patient.intSampleId"));
		log.debug(" the criteria is " + crt.toString());
		return crt.list();

	}
	
	public List getExistingSampleTypes(String intSampleId){
		intSampleId = intSampleId.toUpperCase();
		String query = "select distinct s.sampleType from Sample s where s.patient.intSampleId = ? ";
		return getHibernateTemplate().find(query,intSampleId);
	}
	

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SampleDAO#getSample(java.lang.Integer)
	 */
	public Sample getSample(Integer sampleId) {
		
		return (Sample)(getHibernateTemplate().get(Sample.class,sampleId));
	}
	
	public Sample getSampleByIntSampleIdUniKey(String intId,
			SampleType sampleType,Integer sampleDupNo) {
		//log.debug("the intSample Id is " + intId);
		intId = intId.toUpperCase();
		List results = getHibernateTemplate().
						find("from Sample s where s.patient.intSampleId='"
								+intId+"' and s.sampleType.sampleTypeId=" 
								+ sampleType.getSampleTypeId() 
								+ " and s.sampleDupNo=" 
								+ sampleDupNo);
		if(results.size()>0){
			return (Sample)results.get(0);
		}else{
			return null;
		}
	}
	
	public List getSampleByIntSampleIdSampleType(String intId,SampleType sampleType){
		intId = intId.toUpperCase();
		List results = getHibernateTemplate().find("from Sample s left join fetch s.sampleType left join fetch s.patient where s.patient.intSampleId='"+intId+"' and s.sampleType.sampleTypeId=" + sampleType.getSampleTypeId());
		if(results.size()>0){
			return results;
		}else{
			return null;
		}
	}
	
	/*
	 * Jianan Xiao 2005-09-09
	 * */
	public List getSampleByIntSampleIdSampleType(String intId,
											String[] sampleType)
	{
		List results=null;
		intId = intId.toUpperCase();
		if (sampleType.length>0) {
			String types=" (";
			for(int i=0;i<sampleType.length;i++){
				if (i>0) types+=",";
				types+=sampleType[i];
			}
			types+=" )";
			results = getHibernateTemplate().
					find("from Sample s left join fetch s.sampleType"
					+" left join fetch s.patient"
					+" where s.patient.intSampleId='"
					+intId+"' and s.sampleType.sampleTypeId in" 
					+ types);
		} else {
			results = getSampleByIntSampleId(intId);
		}
			
		return results;
	}
	
	public List getSampleByIntSampleIdSampleProject(String[] sampleProject)
	{
		List results=null;
		if (sampleProject.length>0)
		{
			String projects=" (";
			for(int i=0;i<sampleProject.length;i++){
				if (i>0) projects+=",";
				projects+=sampleProject[i];
			}
			projects+=" )";
			results = getHibernateTemplate().
				find("from Sample s right join fetch s.patient"
				+" where s.patient.project in" 
				+ projects);
		}
		else
		{
			results = null;
		}
			
		return results;
	}

	public List getSampleByIntSampleId(String intId){
		intId = intId.toUpperCase();
		List results = getHibernateTemplate().find("from Sample s left join fetch s.sampleType left join fetch s.patient where s.patient.intSampleId='"+intId+"'");
		if(results.size()>0){
			return results;
		}else{
			return null;
		}
	}
	
	
	public boolean containsIntSampleId(String intSampleId){
		intSampleId = intSampleId.toUpperCase();
		List result = getHibernateTemplate().find("from Sample s where s.patient.intSampleId='"+intSampleId+"'");
		if(result.size()==0){
			return false;
		}else{
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SampleDAO#saveSample(agtc.sampletracking.model.Sample)
	 */
	public void saveSample(Sample sample) throws Exception{
		sample.getPatient().setIntSampleId(
				sample.getPatient().getIntSampleId().toUpperCase());
		Integer sampleId = sample.getSampleId();
		if(sampleId.intValue()==-1){
			if(sample.getSampleDupNo()==null){
				sample.setSampleDupNo(new Integer(1));
			}
			getHibernateTemplate().save(sample);
		}else{
			getHibernateTemplate().update(sample);
		}
		if(log.isDebugEnabled()){
			log.debug("sample ID set to :" + sample.getSampleId());
		}
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SampleDAO#removeSample(java.lang.Integer)
	 */
	public void removeSample(Integer sampleId) {
		Object sample = getHibernateTemplate().load(Sample.class,sampleId);
		getHibernateTemplate().delete(sample);
	}

}

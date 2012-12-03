 /*
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus.manager;

import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.web.command.BarcodeCommand;

/**
 * @author Bei Jin
 *
 * This class is the middleclass between web object and DAO. It is also the target class spring framework
 * provide transaction management
 */
public class BarcodeManager {
	private Log log = LogFactory.getLog(BarcodeManager.class);
	private SampleDAO sampleDAO;
	private ContainerDAO containerDAO;
	private PatientDAO patientDAO;
	private SamplesInContainerDAO samplesInContainerDAO;
	private SampleTypeDAO sampleTypeDAO;
		
	
	
	public List searchSampleBySampleIntIdList(List sampleIds,String sampleTypeSuffix){
		List results = new ArrayList();
		SampleType sampleType = null;
		if(!sampleTypeSuffix.equals("")){
			sampleType = sampleTypeDAO.getSampleTypeBySuffix(sampleTypeSuffix);
		}
		Iterator i = sampleIds.iterator();
		while(i.hasNext()){
			String intSampleId = ((String)i.next()).trim();
			List oneResults = null;
			
			if(sampleType==null){
				oneResults = sampleDAO.getSampleByIntSampleId(intSampleId);
			}else{
				oneResults = sampleDAO.getSampleByIntSampleIdSampleType(intSampleId,sampleType);
			}
			
			if(oneResults!=null){
				results.addAll(oneResults);
			}
			
		}
		return results;
	}

	public void removeSample(Integer sampleId){
		log.debug("remove one sample");
		Sample sample = sampleDAO.getSample(sampleId);		
		sampleDAO.removeSample(sampleId);
	}
	
	public void removeAllSamplesInContainer(Container container){
		Set sics = container.getSamplesInContainers();
		
		Iterator i = sics.iterator();
		
		while(i.hasNext()){
			
			SamplesInContainer sic = (SamplesInContainer)i.next();
			Sample sample = sic.getSample();
			removeSample(sample.getSampleId());
		}
		sics.clear();
	}

	public void saveSample(Sample sample) throws Exception{

		System.out.println("Saving Sample Now!");

		if(sample.getSampleId().intValue()==-1){
			
			Patient patient = sample.getPatient();
			log.debug("internalId is "+patient.getIntSampleId());
			if(!patientDAO.containsPatient(patient.getIntSampleId())){
				patientDAO.savePatient(patient);
	
			}else{
				patient = patientDAO.getPatient(patient.getIntSampleId());
			}
			sample.setPatient(patient);
			
		}
		
		sampleDAO.saveSample(sample);	
		
		log.debug("end of save sample");
	}
	
	/**
	 * This method is for add a batch of new samples in a container
	 * @param container
	 * @param sampleType
	 * @param samples key id the well location in plate, value is sample
	 * return the list of updated samples
	 */
	public List saveSamples  (Container container, SampleType sampleType, Map samples, boolean setWell) throws Exception {
		Set wells = samples.keySet();
		Iterator i = wells.iterator();
		String result = "";
		List results = new ArrayList();
		while(i.hasNext()){
			String well = (String)i.next();
			Sample sample = (Sample)samples.get(well);
			
			Sample sample1 = sampleDAO.getSampleByIntSampleIdUniKey(sample.getPatient().getIntSampleId(),sampleType,sample.getSampleDupNo());
			// check here in order to get a whole list of troubled samples once 
			if(sample1!=null){
				result += "duplicate sample:" + sample.getPatient().getIntSampleId() + sampleType.getSuffix() + sample.getSampleDupNo() + "<br>";
			}
		
		}
		if(result.length()==0){
			i = wells.iterator();
			while(i.hasNext()){
				String well = (String)i.next();
				Sample sample = (Sample)samples.get(well);
				
				sample.setSampleId(new Integer(-1));
				sample.setSampleType(sampleType);
				SamplesInContainer samplesInContainer = new SamplesInContainer();
				samplesInContainer.setSicId(new Integer(-1));
				samplesInContainer.setContainer(container);
				samplesInContainer.setOperation("I");
				samplesInContainer.setOperationDate(new Date());
				if(setWell){
					samplesInContainer.setWell(well);
				}
				
				Set samplesInContainers = new HashSet();
				sample.setSamplesInContainersIn(samplesInContainers);
				sample.getSamplesInContainersIn().add(samplesInContainer);
				samplesInContainer.setSample(sample);
				saveSample(sample);		
				results.add(sample);
			}
		}else{
			throw new Exception(result);
		}
		return results;
	}
	
	/**
	 * This method is for update a batch of samples, will just overwrite those fields appeared in the
	 * updating file
	 * @param container
	 * @param sampleType
	 * @param samples
	 */
	public List updateSamples (SampleType sampleType, List samples) throws Exception {
		log.debug("in updateSamples");
		Iterator i = samples.iterator();
		String result = "";
		List results = new ArrayList();
		while(i.hasNext()){
			Sample sample = (Sample)i.next();
			sample.setSampleType(sampleType);
			String intSampleId = sample.getPatient().getIntSampleId();
			
			Sample sample1 = sampleDAO.getSampleByIntSampleIdUniKey(sample.getPatient().getIntSampleId(),sampleType,sample.getSampleDupNo());
			if(sample1==null){
				result += "no sample:" + sample.getPatient().getIntSampleId() + sampleType.getSuffix() + sample.getSampleDupNo() + "<br>";
			}
		}
			
		if(result.length()==0){
			i = samples.iterator();	
			while(i.hasNext()){
				Sample sample = (Sample)i.next();
				sample.setSampleType(sampleType);
				String intSampleId = sample.getPatient().getIntSampleId();
				
				Sample sample1 = sample1 = sampleDAO.getSampleByIntSampleIdUniKey(sample.getPatient().getIntSampleId(),sampleType,sample.getSampleDupNo());
				
				if(sample.getPatient().getAnotherExtSampleId()!=null){
					sample1.getPatient().setAnotherExtSampleId(sample.getPatient().getAnotherExtSampleId());
				}
				
				if(sample.getPatient().getExtSampleId()!=null){
					sample1.getPatient().setExtSampleId(sample.getPatient().getExtSampleId());
				}
						
				if(sample.getMadeDate()!=null){
					sample1.setMadeDate(sample.getMadeDate());
				}
				
				if(sample.getNotes()!=null){
					sample1.setNotes(sample.getNotes());
				}
				
				if(sample.getOd()!=null){
					sample1.setOd(sample.getOd());
				}
				
				if(sample.getOdDate()!=null){
					sample1.setOdDate(sample.getOdDate());
				}
				
				if(sample.getReceiveDate()!=null){
					sample1.setReceiveDate(sample.getReceiveDate());
				}
				
				if(sample.getRefillDate()!=null){
					sample1.setRefillDate(sample.getRefillDate());
				}
				
				if(sample.getRemoveDate()!=null){
					sample1.setRemoveDate(sample.getRemoveDate());
				}
				
				if(sample.getSampleType()!=null){
					sample1.setSampleType(sample.getSampleType());
				}
				
				if(sample.getStatus()!=null){
					sample1.setStatus(sample.getStatus());
				}
				
				if(sample.getTransDate()!=null){
					sample1.setTransDate(sample.getTransDate());
				}
				
				if(sample.getVolumn()!=null){
					sample1.setVolumn(sample.getVolumn());
				}
				
				if(sample.getVolumnDate()!=null){
					sample1.setVolumnDate(sample.getVolumnDate());
				}
				if(sample.getPatient().getNote()!=null){
					sample1.getPatient().setNote(sample.getPatient().getNote());
				}
				
				if(sample.getPatient().getIsControl()!=null){
					sample1.getPatient().setIsControl(sample.getPatient().getIsControl());
				}
				
				if(sample.getPatient().getFamilyId()!=null){
					sample1.getPatient().setFamilyId(sample.getPatient().getFamilyId());
				}

				if(sample.getPatient().getProject()!=null){
					sample1.getPatient().setProject(sample.getPatient().getProject());
				}

				saveSample(sample1);
				results.add(sample1);
			}
			
		}else{
			throw new Exception(result);
		}
		return results;
	}

	/**
	 * This method is for make a plate out of existing samples, allocate well position
	 * @param container
	 * @param sampleType
	 * @param samples
	 */

	public void saveSamplesInContainer(Container container, Map results,boolean allocateWellPosition) throws Exception{
		Set wells = results.keySet();
		Iterator i = wells.iterator();
		Set sics = new HashSet();
		String result = "";
		Map sampleTypes = new HashMap();
		while(i.hasNext()){
			String well = (String)i.next();
			SampleUniKey sampleUniKey = (SampleUniKey)results.get(well);
			String sampleTypeSuffix = sampleUniKey.getSampleTypeSuffix();
			String intSampleId = sampleUniKey.getIntSampleId();
			Integer sampleDupNo = sampleUniKey.getSampleDupNo();
			if(!sampleTypes.containsKey(sampleTypeSuffix)){
				SampleType sampleType = sampleTypeDAO.getSampleTypeBySuffix(sampleTypeSuffix);
				if(sampleType!=null){
					sampleTypes.put(sampleTypeSuffix,sampleType);
				}else{
					result += "no sample type:" + sampleTypeSuffix + "<br>";
				}
			}
			
			Sample sample1 = sampleDAO.getSampleByIntSampleIdUniKey(intSampleId,(SampleType)sampleTypes.get(sampleTypeSuffix),sampleDupNo);
			if(sample1==null){
				result += "no sample:" + intSampleId + sampleTypeSuffix + sampleDupNo + "<br>";
			}
		}
		
		if(result.length()==0){
			i = wells.iterator();
			while(i.hasNext()){
				String well = (String)i.next();
				SampleUniKey sampleUniKey = (SampleUniKey)results.get(well);
				SamplesInContainer samplesInContainer = new SamplesInContainer();
				samplesInContainer.setSicId(new Integer(-1));
				samplesInContainer.setContainer(container);
				if(allocateWellPosition){
					samplesInContainer.setWell(well);
				}
				samplesInContainer.setOperation("I");
				samplesInContainer.setOperationDate(new Date());
				
				Sample sample = sampleDAO.getSampleByIntSampleIdUniKey(sampleUniKey.getIntSampleId(),(SampleType)sampleTypes.get(sampleUniKey.getSampleTypeSuffix()),sampleUniKey.getSampleDupNo());
				samplesInContainer.setSample(sample);
				sics.add(samplesInContainer);
				
			}
			container.setSamplesInContainers(sics);
			containerDAO.saveContainer(container);
		}else{
			throw new Exception(result);
		}
		
	}
	
	/**
	 * @return
	 */
	public SampleDAO getSampleDAO() {
		return sampleDAO;
	}

	/**
	 * @param sampleDAO
	 */
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}

	

	/**
	 * @return
	 */
	public ContainerDAO getContainerDAO() {
		return containerDAO;
	}

	/**
	 * @param containerDAO
	 */
	public void setContainerDAO(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}
	


	/**
	 * @return Returns the samplesInContainerDAO.
	 */
	public SamplesInContainerDAO getSamplesInContainerDAO() {
		return samplesInContainerDAO;
	}
	/**
	 * @param samplesInContainerDAO The samplesInContainerDAO to set.
	 */
	public void setSamplesInContainerDAO(
			SamplesInContainerDAO samplesInContainerDAO) {
		this.samplesInContainerDAO = samplesInContainerDAO;
	}
	
	
	/**
	 * @return Returns the patientDAO.
	 */
	public PatientDAO getPatientDAO() {
		return patientDAO;
	}
	/**
	 * @param patientDAO The patientDAO to set.
	 */
	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}
	
	
	/**
	 * @return Returns the sampleTypeDAO.
	 */
	public SampleTypeDAO getSampleTypeDAO() {
		return sampleTypeDAO;
	}
	/**
	 * @param sampleTypeDAO The sampleTypeDAO to set.
	 */
	public void setSampleTypeDAO(SampleTypeDAO sampleTypeDAO) {
		this.sampleTypeDAO = sampleTypeDAO;
	}
}
	

 /*
 * Created on Nov 2, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus.manager;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;
import java.text.*;
import agtc.sampletracking.bus.*;

/**
 * @author Gloria Deng
 *
 * This class is the middleclass between web object and DAO. It is also the target class spring framework
 * provide transaction management
 */
public class SampleManager implements ConstantInterface {
	private Log log = LogFactory.getLog(SampleManager.class);
	private SampleDAO sampleDAO;
	private ContainerDAO containerDAO;
	private PatientDAO patientDAO;
	private SamplesInContainerDAO samplesInContainerDAO;
	private SampleTypeDAO sampleTypeDAO;
	
	public Sample getSample(Integer sampleId){
		return sampleDAO.getSample(sampleId);
	}

	public List getSamples(List sampleIds,List sampleTypeSuffixes,Integer sampleDupNo){
		return sampleDAO.getSamples(sampleIds,sampleTypeSuffixes,sampleDupNo);
	}
	
	public List getExistingSampleTypes(String intSampleId){
		return sampleDAO.getExistingSampleTypes(intSampleId);
	}
	
	public Sample getSample(String intSampleId,String sampleTypeSuffix,Integer dupNo){
		Sample sample = sampleDAO.getSample(intSampleId,sampleTypeSuffix,dupNo);
		return sample;
	}
	
	public Sample getSample(String intSampleId){
		Sample sample = sampleDAO.getSample(intSampleId);
		return sample;
	}
	
	public void saveSamplesInContainerList(List samplesInContainers){
		for(int i=0;i<samplesInContainers.size();i++){
			SamplesInContainer sic = (SamplesInContainer)samplesInContainers.get(i);
			saveSamplesInContainer(sic);
			log.debug("have saved one samplesInContainer!");
		}
	}
	
	public String getLargestSampleId(String intSamplePrefix){
		String largestId = sampleDAO.getLargestSampleId(intSamplePrefix);
		
		return largestId;
	}
	
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

	// Remove both sample and patient reference
	public void removeSample(Integer sampleId){
		Sample sample = sampleDAO.getSample(sampleId);		
		sampleDAO.removeSample(sampleId);

	}
	
	public void removeSampleAndPatient(Integer sampleId){
		Sample sample = sampleDAO.getSample(sampleId);		
		patientDAO.removePatient(sample.getPatient().getIntSampleId());

	}
	// Remove all records of samples and patient from DB
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
	
	public void removeAllSamplesAndPatientsInContainer(Container container){
		Set sics = container.getSamplesInContainers();		
		Iterator i = sics.iterator();
		
		while(i.hasNext()){
			
			SamplesInContainer sic = (SamplesInContainer)i.next();
			Sample sample = sic.getSample();
			removeSampleAndPatient(sample.getSampleId());
		}
		sics.clear();
		
	}
	
	public void saveSample(Sample sample) throws Exception{
		
		if(sample.getSampleId().intValue()==-1){
			
			Patient patient = sample.getPatient();
			if(!patientDAO.containsPatient(patient.getIntSampleId())){
				patientDAO.savePatient(patient);
				sample.setPatient(patient);	
			}
			else{ // Patient already exists. Check if sampleType exists.
				List existingSample = sampleDAO.getSampleByIntSampleIdSampleType(sample.getPatient().getIntSampleId(),sample.getSampleType());

				if(!existingSample.isEmpty())
				{
					throw new Exception("Error: Sample already exists. Cannot save sample.");
				}
			}
		}
		
//		// Option 2: Check Sample ID and Sample Type. IF exists, then update Dup No. Automatically 
//
//		if(existingSample != null) {
//			sample.setSampleDupNo(existingSample.size()+1);
//		}
		
		if(sample.getStatus() == null) {
			sample.setStatus("Registered");
		}
		sampleDAO.saveSample(sample);			
	}
	
	public void updateSampleStatus(Sample sample, String sampleStatus) throws Exception{
		sample.setStatus(sampleStatus);
		sampleDAO.saveSample(sample);	
	}
	
	public Container storeSampleInContainer(Container container, Sample sample) throws Exception{
		
		// Instantiate new SIC
		SamplesInContainer samplesInContainer = new SamplesInContainer();
		samplesInContainer.setSicId(new Integer(-1));
		// Set container to SIC
		samplesInContainer.setContainer(container);

		// Set well to SIC. Look for empty wells??
		ContainerType ct = container.getContainerType();
		
		Integer maxCol = ct.getColumnNo();
		Integer maxRow = ct.getRowNo();
		
		Integer containerId = container.getContainerId();
		List wells = samplesInContainerDAO.getWellsInContainersByContainer(containerId);

		// Set operation to SIC
		samplesInContainer.setOperation("I");
		samplesInContainer.setOperationDate(new Date());

		// Add Sample to SIC
		Integer sampleId = sample.getSampleId();
		
		if(samplesInContainerDAO.containsSample(sampleId))
		{
			throw new Exception("<b>Error</b>(Sample already exists in container):");
		}
		else
		{
			samplesInContainer.setSample(sample);
		}
		
		// Set well
		for(int r = 1; r <= maxRow; r++)
		{
			int rowASCII = r + 64;
			char rowLabel = (char) rowASCII;
			
			for(int c = 1; c <= maxCol; c++)
			{				
				String wellLabel = rowLabel + String.valueOf(c);
				if(!wells.contains(wellLabel))
				{
					samplesInContainer.setWell(wellLabel);
					
					// Add SIC into a set and save. 
					Set sics = new HashSet();
					sics.add(samplesInContainer);
					container.setSamplesInContainers(sics);
					
					return container;
				}
			}
		}
		
		// Return null - Container is full
		throw new Exception("<b>Error</b>(Container is full):");
	}
	
	public void saveSamples(List samples) throws Exception{
		
		Iterator i = samples.iterator();
		while(i.hasNext()){
			Sample s = (Sample)i.next();
			saveSample(s);
		}
	}
	
	public Patient getPatient(String intSampleId){
		return patientDAO.getPatient(intSampleId);
	}
	
	public void updatePatient(Patient patient){
		patientDAO.updatePatient(patient);
	}
	
	public SampleType getSampleType(Integer sampleTypeId){
		return sampleTypeDAO.getSampleType(sampleTypeId);
	}
	
	public void updateSampleType(SampleType sampleType){
		sampleTypeDAO.updateSampleType(sampleType);
	}
	
	public List getAllSampleTypes(){
		return sampleDAO.getAllSampleTypes();
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
	 * This method is for add a batch of new samples without a container
	 * @param container
	 * @param sampleType
	 * @param samples
	 * return the list of saved samples
	 */
	public List saveSamplesOnly (SampleType sampleType, List samples) throws Exception {
		log.debug("inside saveSamplesOnly");
		Iterator i = samples.iterator();
		String result = "";
		List results = new ArrayList();
		while(i.hasNext()){
			Sample sample = (Sample)i.next();
			
			Sample sample1 = sampleDAO.getSampleByIntSampleIdUniKey(sample.getPatient().getIntSampleId(),sampleType,sample.getSampleDupNo());
			if(sample1!=null){
				result += "duplicate sample:" + sample.getPatient().getIntSampleId() + sampleType.getSuffix() + sample.getSampleDupNo() + "<br>";
			}	
		}
		
		//Row = alphabet, Col = numbers. If, Box + Row + Col = null, then add.
		if(result.length()==0){
			i = samples.iterator();
			while(i.hasNext()){
				Sample sample = (Sample)i.next();
				sample.setSampleType(sampleType);
				sample.setSampleId(new Integer(-1));
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
				
				saveSample(sample);
				results.add(sample);
			}
			
		}else{
			throw new Exception(result);
		}
		return results;
	}
	
	/**
	 * This method is for make a plate out of given samples, if a sample not existed, but the intSampleId exists (meaning another sample type), create a new record.  allocate well position
	 * @param container
	 * @param sampleType
	 * @param samples
	 */

	public void saveSamplesInContainerBlindly(Container container, Map results,boolean allocateWellPosition) throws Exception{
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
			
			// do not check if intSampleId in STS, if not, create a new patient record
			/*
			if(!patientDAO.containsPatient(intSampleId)){
				result += "internal sample id: " + intSampleId + " is not in STS yet ! <br>";
	
			}
			*/
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
				if(sample == null){
					sample = new Sample();
					Patient p =new Patient();
					p.setIntSampleId(sampleUniKey.getIntSampleId());
					sample.setSampleType((SampleType)sampleTypes.get(sampleUniKey.getSampleTypeSuffix()));
					sample.setSampleDupNo(sampleUniKey.getSampleDupNo());
					sample.setPatient(p);
					sample.setSampleId(new Integer(-1));
					saveSample(sample);
				}
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
	
	public List getSamplesInContainersInBySample(Integer sampleId){
		return samplesInContainerDAO.getSamplesInContainersInBySample(sampleId);
	}
	
	public void saveSamplesInContainer(SamplesInContainer sic){
		samplesInContainerDAO.saveSamplesInContainer(sic);
	}
	
	// Edit Container Content: Remove the sample from container by SIC. Keeps sample record.
	public void removeSamplesInContainer(Integer sicId){
		// Update Sample Status
		SamplesInContainer sic = samplesInContainerDAO.getSamplesInContainer(sicId);
		Sample sample = sic.getSample();
		sample.setStatus("Removed");
		try {
			sampleDAO.saveSample(sample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		samplesInContainerDAO.removeSamplesInContainer(sicId);
	}
	
	// Empty Container: Remove the sample from container by container ID. Keeps sample record
	public void removeSamplesInContainerByContainer(Integer containerId){
		// Update Sample Status for all samples in container
		List sics = samplesInContainerDAO.getSamplesInContainersByContainer(containerId);
		Iterator i = sics.iterator();
		while(i.hasNext()){
			SamplesInContainer sic = (SamplesInContainer)i.next();
			Sample sample = sic.getSample();
			sample.setStatus("Removed");
			try {
				sampleDAO.saveSample(sample);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		samplesInContainerDAO.removeSamplesInContainersByContainer(containerId);
	}
	
	public SamplesInContainer getSamplesInContainer(Integer sicId){
		return samplesInContainerDAO.getSamplesInContainer(sicId);
	}


	public SampleDAO getSampleDAO() {
		return sampleDAO;
	}

	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}

	public ContainerDAO getContainerDAO() {
		return containerDAO;
	}

	public void setContainerDAO(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}
	
	public SamplesInContainerDAO getSamplesInContainerDAO() {
		return samplesInContainerDAO;
	}

	public void setSamplesInContainerDAO(
			SamplesInContainerDAO samplesInContainerDAO) {
		this.samplesInContainerDAO = samplesInContainerDAO;
	}
	
	public PatientDAO getPatientDAO() {
		return patientDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}
	
	public SampleTypeDAO getSampleTypeDAO() {
		return sampleTypeDAO;
	}

	public void setSampleTypeDAO(SampleTypeDAO sampleTypeDAO) {
		this.sampleTypeDAO = sampleTypeDAO;
	}
}

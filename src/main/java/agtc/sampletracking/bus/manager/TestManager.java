/*
 * Created on Nov 3, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus.manager;

import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.*;
 

/**
 * @author Gloria Deng
 * 
 * Modified by Jianan Xiao  2005-09-06
 * Delete the reference of study group to CGG
 *
 * This is the middle class between web object and DAO object, it is also the target class that spring framework
 * provide transaction management
 */
public class TestManager {
	private Log log = LogFactory.getLog(TestManager.class);
	private TestDAO testDAO;
	private AssayDAO assayDAO;
	private ResultDAO resultDAO;
	/*Modified by Jianan Xiao  2005-09-06*/
//	private CGGStudyGroupDAO cggStudyGroupDAO;	
//	private List allCGGStudyGroups;
	private RunDAO runDAO;
	private SampleDAO sampleDAO;
	private ContainerDAO containerDAO;
	private List allTests;
	private List allAssays;
	private List allRuns;
	private boolean refresh = false;
	
   /**
     * @return Returns the sampleDAO.
     */
    public SampleDAO getSampleDAO() {
        return sampleDAO;
    }
    /**
     * @param sampleDAO The sampleDAO to set.
     */
    public void setSampleDAO(SampleDAO sampleDAO) {
        this.sampleDAO = sampleDAO;
    }
	public Test getTest(Integer i){
		return testDAO.getTest(i);
	}
	
	
	
	public Assay getAssay(Integer i){
		return assayDAO.getAssay(i);
	}
	
	public Assay getAssay(String name){
		return assayDAO.getAssayByName(name);
	}
	
	//Get Assays List belong to a RUN
	// Jianan Xiao 2006-03-01
	public List getAssayByRunId(Integer id){
		return assayDAO.getAssayByRunId(id);
	}
	
	public Result getResult(Integer i){
		return resultDAO.getResult(i);
	}
	
	
	public Run getRun(Integer i){
		return runDAO.getRun(i);
	}

	public void saveTest(Test test) throws Exception {
		testDAO.saveTest(test);
		refresh = true;
	}
	
	public void saveAssay(Assay assay) throws Exception {
		assayDAO.saveAssay(assay);
		refresh = true;
	}
	
	public void saveRun(Run run){
		runDAO.saveRun(run);
		refresh = true;
	}
	
	
	
	public void saveResult(Result result) throws Exception{
		 if(sampleDAO.containsIntSampleId(result.getIntSampleId())){
	     	saveRun(result.getRun());
	    }else{
	    	throw new Exception("could not find the sample with internalSampleId " + result.getIntSampleId() + "<br>");
	    }
	}
	
	public void saveResult4update(Result result) throws Exception{
		resultDAO.saveResult(result);
	}
	
	
	//here always save the results as the resultset of run
	public void saveResults(List results,Run run,Integer studyGroupId) throws Exception{
		Iterator i = results.iterator();
		Set runResults = new HashSet();
		String result = "";
		Collection samples = new LinkedList();
		Map assays = new HashMap();
		
		while(i.hasNext()){
		   
			MiniResultInfo mini = (MiniResultInfo)i.next();
		    String intSampleId = mini.getSampleId();
		    String assayName = mini.getAssayName();
		    Result r = new Result();
		    r.setResultId(new Integer(-1));
		    
		    if(samples.contains(intSampleId)){
		    	r.setIntSampleId(intSampleId);
		    }else{
			    if(sampleDAO.containsIntSampleId(intSampleId)){
			    	
		    		samples.add(intSampleId);
		    		r.setIntSampleId(intSampleId);
		    	
		    	}else{
		    		
		    		result += "no sample with internal sampleId " + intSampleId + "<br>";
		    	}
				
		    }
		    
		    if(assays.containsKey(assayName)){
		    	 r.setAssay((Assay)assays.get(assayName));
		    	 
		    }else{
			    
		    	try{
		    		Assay assay=null;
		    		assay = assayDAO.getAssayByName(assayName);
		    		if(assay==null){
		    			//Added By Jianan Xiao  2006-01-25
		    			//If this assay does not exist, create this assay
		    			assay = new Assay();
		    			assay.setAssayId(new Integer(-1));
		    			assay.setName(assayName);
		    			assayDAO.saveAssay(assay);	
		    		}
		    		r.setAssay(assay);
		    		assays.put(assayName,assay);
		    	}
		    	catch(Exception e){
		    		result += "no assay with assay name " + assayName + "<br>";
		    	}
				
		    }
		    
		    
		    r.setResult(mini.getResult());
		    
		    r.setRun(run);
		    
		    if(studyGroupId != null){
		    	r.setStudyGroupId(studyGroupId);
		    }
		    
		    runResults.add(r);
		   
		    
		   
		    
		}
		
		if(result.length()==0){
			//log.debug("going to setResults of run");
			run.setResults(runResults);
			//log.debug("going to saveRun");
			runDAO.saveRun(run); 
		}else{
			log.debug("going to throw exception" + result);
			throw new Exception(result);
		}
	}
	
	
	
	/**
	 * 
	 * see comments of TestSearchField.java
	 */
	public List searchTest(List crtList,List lgcList){
		return testDAO.getTests(crtList,lgcList);
		
	}
	/**
	 * 
	 * see comments of ResultSearchFields.java
	 */
	public List searchResult(List crtList,List lgcList){
		return resultDAO.getResults(crtList,lgcList);
	}
	

	
	// parameter is the array of assayId
	public List searchResultByAssays(String[] assayIds){
		
		String sql = "select r from Result r left join fetch r.run left join fetch r.assay ";
		String whereString = "where ";
		
		int i=0;
		for (i=0;i<(assayIds.length-1);i++){
			whereString += "r.assay.assayId=" + assayIds[i] + " or ";
		}
		// the last one in the assays
		whereString += "r.assay.assayId=" + assayIds[i];
		sql = sql + whereString;
		return resultDAO.getResults(sql);
	}
	
//	 parameter is the array of assayId
	public List searchResultByRunId(int runId){
		return resultDAO.getResultsByRunId(runId);
	}
	
	public void refreshResult(Result result){
		resultDAO.refreshResult(result);
	}
	
	/**
	 * 
	 * see comments of AssaySearchFields.java
	 */
	public List searchAssay(List crtList,List lgcList){
		return assayDAO.getAssays(crtList,lgcList);
	}
	
	/**
	 * 
	 * see comments of RunSearchFields.java
	 */
	public List searchRun(List crtList,List lgcList){
		return runDAO.getRuns(crtList,lgcList);
	}
	

	public void removeTest(Integer testId){
		testDAO.removeTest(testId);
	}
	
	public void removeResult(Integer resultId){
		resultDAO.removeResult(resultId);
	}
	
	public void removeRun(Integer runId){
		runDAO.removeRun(runId);
	}
	
	public void removeAssay(Integer assayId){
		assayDAO.removeAssay(assayId);
	}
	
	public void removeResultByAssay(Integer runId, String[] assays){
		resultDAO.removeResultByAssay(runId,assays);
	}
	
	
	
	
	/**
	 * @return
	 */
	public AssayDAO getAssayDAO() {
		return assayDAO;
	}

	/**
	 * @return
	 */
	public TestDAO getTestDAO() {
		return testDAO;
	}

	/**
	 * @param assayDAO
	 */
	public void setAssayDAO(AssayDAO assayDAO) {
		this.assayDAO = assayDAO;
	}

	/**
	 * @param testDAO
	 */
	public void setTestDAO(TestDAO testDAO) {
		this.testDAO = testDAO;
	}

	/**
	 * @return
	 */
	public ResultDAO getResultDAO() {
		return resultDAO;
	}

	/**
	 * @param resultDAO
	 */
	public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}

	/**
	 * @return
	 */
	public RunDAO getRunDAO() {
		return runDAO;
	}

	/**
	 * @param runDAO
	 */
	public void setRunDAO(RunDAO runDAO) {
		this.runDAO = runDAO;
	}
	
	

	/**
	 * @return Returns the cggStudyGroupDAO.
	 */
	/*Modified by Jianan Xiao  2005-09-06*/
	/*
	public CGGStudyGroupDAO getCggStudyGroupDAO() {
		return cggStudyGroupDAO;
	}
	*/
	/**
	 * @param cggStudyGroupDAO The cggStudyGroupDAO to set.
	 */
	/*Modified by Jianan Xiao  2005-09-06*/
	/*
	public void setCggStudyGroupDAO(CGGStudyGroupDAO cggStudyGroupDAO) {
		this.cggStudyGroupDAO = cggStudyGroupDAO;
	}
	*/
	
	
	
	
	
	/**
	 * @return Returns the containerDAO.
	 */
	public ContainerDAO getContainerDAO() {
		return containerDAO;
	}
	/**
	 * @param containerDAO The containerDAO to set.
	 */
	public void setContainerDAO(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}
	/**
	 * @return Returns the allCGGStudyGroups.
	 */
	/*Modified by Jianan Xiao  2005-09-06*/
	/*
	public List getAllCGGStudyGroups() {
		if(allCGGStudyGroups==null){
			allCGGStudyGroups =  cggStudyGroupDAO.getAllStudyGroups();
		}
		return allCGGStudyGroups;
	}
	
	public void setAllCGGStudyGroups(List list) {
		allCGGStudyGroups =  list;
		
	}
	*/
	
	/**
	 * @return Returns the allTests.
	 */
	public List getAllTests() {
		//if(allTests==null || refresh == true){
			allTests = testDAO.getTests();
			//refresh = false;
		//}
		return allTests;
	}
	
	
	/**
	 * @return Returns the allAssays.
	 */
	public List getAllAssays() {
		//if(allAssays==null || refresh == true ){
			allAssays = assayDAO.getAssays();
		//	refresh = false;
		//}
		return allAssays;
	}
	
	/**
	 * @return Returns the allAssays.
	 */
	public List getAllRuns() {
		//if(allRuns==null || refresh == true){
			allRuns = runDAO.getRuns();
			//refresh = false;
		//}
		return allRuns;
	}
	
	
}

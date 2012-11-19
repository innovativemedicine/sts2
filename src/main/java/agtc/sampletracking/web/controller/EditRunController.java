/*
 * Created on Dec 3, 2004
 * 
 *Modified by Jianan Xiao 2005-09-06
		 * delete reference of study group to CGG
		 * 
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.RequestUtils;
import org.springframework.validation.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.parser.ResultParser;
import java.util.*;
import org.springframework.web.multipart.*;
import java.io.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditRunController extends BasicController {
	private TestManager testManager;
	private ProjectManager projectManager;
	private ContainerManager containerManager;
	private AGTCManager agtcManager;
	private IdListResolver idListResolver;
	private Log log = LogFactory.getLog(EditRunController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public EditRunController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("run name is " + runManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "runId"))).getName());
		int i = RequestUtils.getIntParameter(request, "runId",-1);
		if(i==-1){
			Run run = new Run();
			run.setRunId(new Integer(-1));
			
			int ip = RequestUtils.getIntParameter(request, "projectId",-1);
			
			if(ip != -1){
				run.setProject(projectManager.getProject(new Integer(ip)));
			}
			
			return run;
		}else{
			Run run = testManager.getRun(new Integer(i));
			run.setTestNameList(idListResolver.resolveTestIdList(run.getTestList()));
			run.setPlateNameList(idListResolver.resolvePlateIdList(run.getPlateList()));
			
			return run;
		}
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
								HttpServletResponse response,
								Object command,
								BindException errors)
						 throws Exception {
		Run run = (Run) command;
		log.debug("the runid is " +run.getRunId());
		//log.debug("runDate name is " + run.getRunDate().toString());

		//int studyGroupId = RequestUtils.getIntParameter(request, "studyGroupId",-1);
		// the following column no is 1 based, for the convience of user, will change to 0 based one 
		// construct a ResultParse
		int sampleIdColumnNo = RequestUtils.getIntParameter(request, "sampleIdColumnNo",-1);
		int assayNameColumnNo = RequestUtils.getIntParameter(request, "assayNameColumnNo",-1);
		int resultColumnNo = RequestUtils.getIntParameter(request, "resultColumnNo",-1);
	
		/*
		String format = RequestUtils.getStringParameter(request, "format","");
		if(format == null || format.length()==0){
			errors.reject("error.required",new String[]{"Sample file type"},"Sample file type is required");
			return showForm(request, response, errors);
		}
		SamplePrefix samplePrefix = agtcManager.getSamplePrefixByDescription(format);
		*/
		String[] pIds = request.getParameterValues("plateIds");
		String plateIdList = "";
		if (pIds != null) { 
			for (int i = 0; i < pIds.length; i++) { 
				plateIdList +=","+pIds[i]+","; 
			} 
			
		}
		
		
		String[] tIds = request.getParameterValues("testIds");
		String testIdList = "";
		
		if (tIds != null) { 
			for (int i = 0; i < tIds.length; i++) { 
				testIdList +=","+tIds[i]+","; 
			} 
		}
		run.setPlateList(plateIdList);
		run.setTestList(testIdList);
	
		log.debug(run);
		
		MultipartHttpServletRequest  mrequest = (MultipartHttpServletRequest)request;
		MultipartFile aFile = mrequest.getFile("file");
		if(aFile.isEmpty()){
			errors.reject( "error.emptyFile","Uploaded file is empty");
			return showForm(request, response, errors);
		}
		String note = run.getNote();
		if(note == null || note.trim().length()==0){
			note = aFile.getOriginalFilename();
			run.setNote(note);
		}
		InputStream is = aFile.getInputStream();
		
		/* Modified by Jianan Xiao 2005-09-06
		 * Because all sample id have prefix now, so we need not them
		 * from this time, and alos deleted "sample file type" and
		 * "study group id" 
		 * */
		ResultParser rp = new ResultParser(is,null,sampleIdColumnNo-1,assayNameColumnNo-1,resultColumnNo-1);
		String parseResult = rp.parseResults();
		List results = rp.getResults();
/*		Integer studyGroupIdO = null;
		if(studyGroupId != -1){
			studyGroupIdO = new Integer(studyGroupId);
		}
*/
		is.close();
		
		if(parseResult.length() == 0){ // parsing success, save the samples
			try{
				testManager.saveResults(results,run,null);
				//ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
				
				Map myModel = new HashMap();
				myModel.put("message","Have successfully save the run and load the result !");
				//myModel.put("runId",run.getRunId());
				return new ModelAndView("successComplete",myModel);
			}catch(Exception e){
				parseResult = "Could not load the results. Please make sure that all samples and assays in your file are already in the STS ! <br>";
				parseResult += e.toString();
			}
		}
		
		// there is some correctable error in the file, let user correct it and upload again
		Map myModel = new HashMap();
		String message = "Please correct the following error and upload your file again ! <br>";
		
		myModel.put("message",message+parseResult);
	
		return new ModelAndView("errorPage",myModel);
		
	}
	
	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
													 javax.servlet.http.HttpServletResponse response,
													 java.lang.Object command,
													 BindException errors)
											  throws java.lang.Exception
		{
			
			log.debug(errors);
			return null;
		}
		*/

	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   throws java.lang.Exception
	{
		Run run = (Run)command;
		Map models = new HashMap();
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		List allProjects = projectManager.getAllProjects();
		List allTests = testManager.getAllTests();
		List allPlates = containerManager.getAllPlates();
		
		/* Modified by Jianan Xiao 2005-09-06
		 * delete reference of study group to CGG
		 * */
	//	List allStudyGroups = testManager.getAllCGGStudyGroups();
		
		List allColumnNumbers = new ArrayList();
		
		CGGStudyGroup empty = new CGGStudyGroup();
	//	allStudyGroups.add(0,empty);
		List allSamplePrefixes = agtcManager.getAllSamplePrefixes();
		
		for(int i = 1; i<21;i++){
			allColumnNumbers.add(new Integer(i));
		}
		
		
		models.put("allTests",allTests);
		models.put("allPlates",allPlates);
		models.put("allProjects",allProjects);
	//	models.put("allStudyGroups",allStudyGroups);
		models.put("allSamplePrefixes",allSamplePrefixes);
		models.put("allColumnNumbers",allColumnNumbers);
		return models;
	}
	
	/**
	 * @return
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	/**
	 * @return
	 */
	public ProjectManager getProjectManager() {
		return projectManager;
	}

	/**
	 * @return
	 */
	public TestManager getTestManager() {
		return testManager;
	}

	/**
	 * @param manager
	 */
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

	/**
	 * @param manager
	 */
	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	/**
	 * @param manager
	 */
	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

	/**
	 * @return
	 */
	public IdListResolver getIdListResolver() {
		return idListResolver;
	}

	/**
	 * @param resolver
	 */
	public void setIdListResolver(IdListResolver resolver) {
		idListResolver = resolver;
	}
	
	

	/**
	 * @return Returns the agtcManager.
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}
	/**
	 * @param agtcManager The agtcManager to set.
	 */
	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}

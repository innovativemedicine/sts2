/*
 * Created on Feb 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.CGGStudyGroup;
import agtc.sampletracking.bus.IdListResolver;
import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.bus.parser.ResultParser;
import agtc.sampletracking.model.Run;
import agtc.sampletracking.model.SamplePrefix;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ModifyRunController extends BasicController {
	private TestManager testManager;
	private ProjectManager projectManager;
	private ContainerManager containerManager;
	private AGTCManager agtcManager;
	private IdListResolver idListResolver;
	private Log log = LogFactory.getLog(ModifyRunController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public ModifyRunController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("run name is " + runManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "runId"))).getName());
		int i = RequestUtils.getRequiredIntParameter(request, "runId");
		
		Run run = testManager.getRun(new Integer(i));
		run.setTestNameList(idListResolver.resolveTestIdList(run.getTestList()));
		run.setPlateNameList(idListResolver.resolvePlateIdList(run.getPlateList()));
		
		return run;
	
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
								HttpServletResponse response,
								Object command,
								BindException errors)
						 throws Exception {
		Run run = (Run) command;
		log.debug("the runid is " +run.getRunId());

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
		testManager.saveRun(run);
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this run !");
		myModel.put("runId",run.getRunId());
		return view;	
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
	
		
		
		models.put("allTests",allTests);
		models.put("allPlates",allPlates);
		models.put("allProjects",allProjects);
		
		return models;
	}
	/**
	 * @return Returns the containerManager.
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}
	/**
	 * @param containerManager The containerManager to set.
	 */
	public void setContainerManager(ContainerManager containerManager) {
		this.containerManager = containerManager;
	}
	/**
	 * @return Returns the idListResolver.
	 */
	public IdListResolver getIdListResolver() {
		return idListResolver;
	}
	/**
	 * @param idListResolver The idListResolver to set.
	 */
	public void setIdListResolver(IdListResolver idListResolver) {
		this.idListResolver = idListResolver;
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
	/**
	 * @return Returns the projectManager.
	 */
	public ProjectManager getProjectManager() {
		return projectManager;
	}
	/**
	 * @param projectManager The projectManager to set.
	 */
	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	/**
	 * @return Returns the testManager.
	 */
	public TestManager getTestManager() {
		return testManager;
	}
	/**
	 * @param testManager The testManager to set.
	 */
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}
}

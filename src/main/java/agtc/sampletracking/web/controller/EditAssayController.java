/*
 * Created on Nov 11, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
 
import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.RequestUtils;
 

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.manager.*;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditAssayController extends BasicController {
	private TestManager testManager;
	private ProjectManager projectManager;
	private Log log = LogFactory.getLog(EditAssayController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public EditAssayController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getRequiredIntParameter(request, "assayId");
		
		if(i==-1){
			Assay assay = new Assay();
			assay.setAssayId(new Integer(-1));
			int ip = RequestUtils.getRequiredIntParameter(request, "projectId");
			Project project = projectManager.getProject(new Integer(ip));
			Set projects = new HashSet();
			assay.setProjects(projects);
			assay.getProjects().add(project);
			//project.getAssays().add(assay);
			//log.debug("the assayid in the formBackingObject is " + assay.getAssayId());
			
			
			
			return assay;
		}else{
			Assay assay = testManager.getAssay(new Integer(i));
			return assay;
		}
	}

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception  {
		Assay assay = (Assay) command;
		
		/**  the following two line of codes cause org.hibernate.LazyInitializationException: 
		 * Failed to lazily initialize a collection - no session or session was closed

		Project project = (Project)(assay.getProjects().iterator().next());
		log.debug("the project name for this assay is "+project.getName());
		*/
		log.debug("the assay id for this assay is "+assay.getAssayId());
		
		log.debug(assay);
		
		try{
			testManager.saveAssay(assay);
		}catch(Exception e){
			errors.rejectValue( "name","error.notUnique",new String[]{assay.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this assay !");
		myModel.put("assayId",assay.getAssayId());
		return view;
	}
	
	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
												 javax.servlet.http.HttpServletResponse response,
												 java.lang.Object command,
												 BindException errors)
										  throws java.lang.Exception
	{
		Assay assay = (Assay) command;
		log.debug(assay);
		log.debug(errors);
		return null;
	}
	*/
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
	{
		Map models = new HashMap();
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
		models.put("message",message);
		}
		return models;
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
	public void setTestManager(TestManager manager) {
		testManager = manager;
	}
	
	/**
	 * @return
	 */
	public ProjectManager getProjectManager() {
		return projectManager;
	}

	/**
	 * @param manager
	 */
	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

}


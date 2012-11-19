/*
 * Created on Oct 27, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditProjectController extends BasicController {
	private ProjectManager projectManager;
	private AGTCManager agtcManager;
	private Log log = LogFactory.getLog(EditProjectController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public EditProjectController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getIntParameter(request, "projectId",-1);
		if(i==-1){
			Project project = new Project();
			project.setProjectId(new Integer(-1));
			return project;
		}else{
			//Project project = projectManager.getProject(new Integer(i));
			return projectManager.getProject(new Integer(i));
		}
	}

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception {
		Project project = (Project) command;
		log.debug(project);
		// delegate the update to the Business layer
		try{
			projectManager.saveProject(project);
		}catch(Exception e){
			errors.rejectValue( "name","error.notUnique",new String[]{project.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		log.debug("success view is " + getSuccessView());
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this project !");
		myModel.put("projectId",project.getProjectId());
		return view;
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   throws java.lang.Exception
	{
		Map models = new HashMap();
		List allInvgs = agtcManager.getInvestigators();
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		/*
		Project project = (Project) command;
		Investigator currentInvg = project.getInvestigator();
		if(currentInvg == null){
			allInvgs.add(0,null);
		}else{
			allInvgs.remove(currentInvg);
			allInvgs.add(0,currentInvg);
		}
		*/
		
		
		models.put("availableIvgs",allInvgs);
		log.debug("referenceData is called");
		return models;
	}
	

	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
												 javax.servlet.http.HttpServletResponse response,
												 java.lang.Object command,
												 BindException errors)
										  throws java.lang.Exception
	{
		Project project = (Project) command;
		log.debug(project);
		log.debug(errors);
		return null;
	}
	*/
	

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

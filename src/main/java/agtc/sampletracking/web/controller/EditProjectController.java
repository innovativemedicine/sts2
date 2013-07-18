/*
 * Created on Oct 27, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.model.Project;

/**
 * @author Gloria Deng
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditProjectController extends BasicController {
	private ProjectManager	projectManager;
	private AGTCManager		agtcManager;
	private Log				log	= LogFactory.getLog(EditProjectController.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed
	 * (null, null, org.springframework.validation.BindException)
	 */
	public EditProjectController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);

	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		// log.debug("project name is " + projectManager.getProject(new
		// Integer(ServletRequestUtils.getRequiredIntParameter(request,
		// "projectId"))).getName());
		int i = ServletRequestUtils.getIntParameter(request, "projectId", -1);
		if (i == -1) {
			Project project = new Project();
			project.setProjectId(new Integer(-1));
			return project;
		} else {
			// Project project = projectManager.getProject(new Integer(i));
			return projectManager.getProject(new Integer(i));
		}
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Project project = (Project) command;
		log.debug(project);
		// delegate the update to the Business layer
		try {
			projectManager.saveProject(project);
		} catch (Exception e) {
			errors.rejectValue("name", "error.notUnique", new String[] { project.getName() }, "Not unique");
			return showForm(request, response, errors);
		}
		log.debug("success view is " + getSuccessView());
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message", "Have successfully saved this project !");
		myModel.put("projectId", project.getProjectId());
		return view;
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws java.lang.Exception {
		Map models = new HashMap();
		List allInvgs = agtcManager.getInvestigators();
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		/*
		 * Project project = (Project) command; Investigator currentInvg =
		 * project.getInvestigator(); if(currentInvg == null){
		 * allInvgs.add(0,null); }else{ allInvgs.remove(currentInvg);
		 * allInvgs.add(0,currentInvg); }
		 */

		models.put("availableIvgs", allInvgs);

		return models;
	}

	/**
	 * protected ModelAndView
	 * processFormSubmission(javax.servlet.http.HttpServletRequest request,
	 * javax.servlet.http.HttpServletResponse response, java.lang.Object
	 * command, BindException errors) throws java.lang.Exception { Project
	 * project = (Project) command; log.debug(project); log.debug(errors);
	 * return null; }
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
	 * @param agtcManager
	 *            The agtcManager to set.
	 */
	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}

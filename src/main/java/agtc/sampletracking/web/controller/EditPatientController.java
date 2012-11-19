/*
 * Created on Apr 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.model.Patient;
import agtc.sampletracking.model.Sample;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EditPatientController extends BasicController {
	private Log log = LogFactory.getLog(EditPatientController.class);
	private SampleManager sampleManager;
	private ProjectManager projectManager;

	public EditPatientController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getproject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		String intSampleId = RequestUtils.getRequiredStringParameter(request, "intSampleId");
		
		
		Patient patient = sampleManager.getPatient(intSampleId);
		return patient;
	
	}

	/**
	 * @return Returns the sampleManager.
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}
	/**
	 * @param sampleManager The sampleManager to set.
	 */
	public void setSampleManager(SampleManager sampleManager) {
		this.sampleManager = sampleManager;
	}
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception {
		Patient patient = (Patient)command;
		sampleManager.updatePatient(patient);
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this sample !");
		myModel.put("intSampleId",patient.getIntSampleId());
		return view;
	
	}

	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   
	{
		Map models = new HashMap();
		
		List allProjects = projectManager.getAllProjects();
		
		models.put("allProjects",allProjects);
		return models;
	}
	
	
	
	/*
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
													 javax.servlet.http.HttpServletResponse response,
													 java.lang.Object command,
													 BindException errors)
											  throws java.lang.Exception
	{
		Sample sample = (Sample) command;
		log.debug(sample);
		log.debug(errors);
		return null;
	}
	*/
	
	
	

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
}

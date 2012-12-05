/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.RequestUtils;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddMultiSamplesController extends BasicController {
	private Log log = LogFactory.getLog(AddMultiSamplesController.class);
	private SampleManager sampleManager;
	private ContainerManager containerManager;
	private AGTCManager agtcManager;
	private ProjectManager projectManager;
	
	public AddMultiSamplesController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {

			MultiSamples multiSampleClass = new MultiSamples();

			return multiSampleClass;
			
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,BindException errors) throws Exception {
		
		MultiSamples multiSamples = (MultiSamples) command;

		System.out.println("Entering onSubmit");
		
		// List to store results.
		List<Sample> sampleList = (List<Sample>) multiSamples.getMultiSamples();

		for (int i=0; i<sampleList.size(); i++)
		{
			Sample sample = sampleList.get(i);
			
			if(sample.getSampleType()==null){
				errors.reject("error.required",new String[]{"sample type"},"Sample Type is required");
					System.out.println("Sample Type Error.");
					return showForm(request, response, errors);
				}
				
				if(sample.getPatient().getProject()==null){
					errors.reject("error.required",new String[]{"sample source"},"Project is required");
					return showForm(request, response, errors);
				}
			}
			
			String message = "";
			
			// Save samples to DB
			try{
				sampleManager.saveSamples(sampleList);
				message += "Samples successfully added to database.";
			}catch(Exception e){
				log.debug(e.getMessage());
					errors.rejectValue( "patient.intSampleId","error.notUnique",new String[]{"Internal Sample ID not unique for selected sample type "},"Not unique");
				return showForm(request, response, errors);
			}
			
			ModelAndView mav = new ModelAndView(new RedirectView(getSuccessView()));
			WebUtils.setSessionAttribute(request,"sampleList",sampleList);
			mav.addObject("message", message);
	
			return mav;		
	}

	protected Map referenceData(HttpServletRequest request,
									  	Object command,Errors errors)
							   
	{
		Map models = new HashMap();
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		List allSampleTypes = agtcManager.getSampleTypesWithVials();
		List allProjects = projectManager.getAllProjects();
		
		models.put("allSampleTypes",allSampleTypes);
		
		log.debug("referenceData is called");
		models.put("allProjects",allProjects);
		return models;
	}
	
		
	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}
	
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
	
	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
}


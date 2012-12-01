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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter
;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;
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
		List<Sample> printSampleList = new ArrayList<Sample>();
		List<Sample> sampleList = multiSamples.getMultiSamples();

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
		
				// Add number of labels to print queue
//				Sample st = agtcManager.getSampleType(sample)
				Integer initialLabelNo = sample.getSampleType().getInitialLabelNo();
				
				System.out.println(initialLabelNo);
				
				if(initialLabelNo!=null){
					int b = initialLabelNo.intValue();
					for(int j=0;j<b;j++){
						printSampleList.add(sample);
					}
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
	
			String labelnu = request.getParameter("labelNo");
			if(labelnu!=null){
				SatoLabelPrinter satoP = new SatoLabelPrinter();
				satoP.printSatoLabel(printSampleList);
				message += " (Labels are available at the Label printer.)";
			}		
			
			ModelAndView mav = new ModelAndView("sampleList");
			mav.addObject("sampleList", sampleList);
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
	
		
	/**
	 * @return
	 */
	public SampleManager getSampleManager() {
		return sampleManager;
	}

	/**
	 * @param manager
	 */
	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	/**
	 * @return
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	/**
	 * @param manager
	 */
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
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
}


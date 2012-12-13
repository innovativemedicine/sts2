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

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.model.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;
/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddMultiSamplesController extends BasicController implements ConstantInterface {
	private Log log = LogFactory.getLog(AddMultiSamplesController.class);
	private SampleManager sampleManager;

	private ProjectManager projectManager;
	
	public AddMultiSamplesController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {

			MultiSamples multiSampleClass = new MultiSamples();
			
			Integer numSamples = RequestUtils.getIntParameter(request, "ns");
			
			String largestSampleId = sampleManager.getLargestSampleId(SAMPLE_PREFIX);
			Integer largestSampleNum = Integer.parseInt(largestSampleId.replace(SAMPLE_PREFIX, ""));
			
			List autoSampleHolder = new ArrayList();
			
			for(int i = 1; i <= numSamples; i++)
			{
				Integer intSampleNum = largestSampleNum + i;
				String formatNum = String.format("%06d", intSampleNum);
				String intSampleId = SAMPLE_PREFIX + formatNum;
				Sample sample = new Sample(intSampleId);
				autoSampleHolder.add(sample);
			}
			
			multiSampleClass.setMultiSamples(autoSampleHolder);
			
			return multiSampleClass;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,BindException errors) throws Exception {
		
		MultiSamples multiSamples = (MultiSamples) command;
		
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
				message += "Samples saved successfully. Labels have been printed";
			}catch(Exception e){
				log.debug(e.getMessage());
					errors.rejectValue( "patient.intSampleId","error.notUnique",new String[]{"Internal Sample ID not unique for selected sample type "},"Not unique");
				return showForm(request, response, errors);
			}
			
			// Print
			SatoLabelPrinter satoP = new SatoLabelPrinter();
			satoP.printSampleLabel(sampleList);
			
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
		
		List allSampleTypes = sampleManager.getAllSampleTypes();
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
	
	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
}


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
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.report.SatoLabelPrinter;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddSingleSampleController extends BasicController {
	private Log log = LogFactory.getLog(AddSingleSampleController.class);
	private SampleManager sampleManager;
	private ContainerManager containerManager;
	private AGTCManager agtcManager;
	private ProjectManager projectManager;
	
	public AddSingleSampleController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
			Sample sample = new Sample();
			
			Patient patient = new Patient();
			sample.setPatient(patient);
			

			sample.setSampleId(new Integer(-1));
			return sample;
		
	}

	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception {
		Sample sample = (Sample) command;
		List samples = new ArrayList();
		List printSampleList = new ArrayList();
		List allSampleTypes = agtcManager.getSampleTypesWithVials();
		Iterator ist = allSampleTypes.iterator();
		int counter = 0;
		if(sample.getPatient().getProject()==null){
			errors.reject("error.required",new String[]{"sample source"},"Sample Source is required");
			return showForm(request, response, errors);
		}
		while(ist.hasNext()){
			SampleType st = (SampleType)ist.next();
			String suffix = st.getSuffix();
			String submitted = RequestUtils.getStringParameter(request, suffix,"");
			if(!submitted .equals("")){
				Sample s1 = (Sample)sample.clone();
			
				s1.setSampleType(st);
				samples.add(s1);
				counter++;
				Integer initialLabelNo = st.getInitialLabelNo();
				if(initialLabelNo!=null){
					int b = initialLabelNo.intValue();
					for(int i=0;i<b;i++){
						printSampleList.add(s1);
					}
				}
			}
		}
		
		if(counter==0){
			errors.reject("error.required",new String[]{"Sample Type"},"Sample Type is required");
			return showForm(request, response, errors);
		}
		
	
		try{
			sampleManager.saveSamples(samples);
		}catch(Exception e){
			log.debug(e.getMessage());
			errors.rejectValue( "patient.intSampleId","error.notUnique",new String[]{sample.getPatient().getIntSampleId()+ " for selected sample type "},"Not unique");
			return showForm(request, response, errors);
		}
		String message = "Have successfully saved this sample !";
		
		//Print sample's label
		//get label quantity
		String labelnu = request.getParameter("labelNo");
		if(labelnu!=null){
			SatoLabelPrinter satoP = new SatoLabelPrinter();
			satoP.printSatoLabel(printSampleList);
			message += "<br> Please pick up your labels at the Label printer !";
		}
		
		String intSampleId = sample.getPatient().getIntSampleId();
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("intSampleId",intSampleId);
		return view;
	}

	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   
	{
		Map models = new HashMap();
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		
		List allSampleTypes = agtcManager.getSampleTypesWithVials();
		List allProjects = projectManager.getAllProjects();
		
		models.put("availableSampleTypes",allSampleTypes);
		
		
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


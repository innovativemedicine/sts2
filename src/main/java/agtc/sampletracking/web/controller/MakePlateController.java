/*
 * Created on Jan 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Location;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.SamplePrefix;
import agtc.sampletracking.model.SampleType;
import agtc.sampletracking.model.SamplesInContainer;
import agtc.sampletracking.web.command.SaveSamplesCommand;
import agtc.sampletracking.bus.manager.*;

import agtc.sampletracking.bus.parser.SamplePlateParser;
import agtc.sampletracking.bus.parser.SampleParser;
import agtc.sampletracking.bus.parser.SamplesNoWellParser;
import agtc.sampletracking.bus.parser.SamplesWithWellParser;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MakePlateController extends BasicController {
	
	private ContainerManager containerManager;
	private SampleManager sampleManager;
	private ProjectManager projectManager;
	private AGTCManager agtcManager;
	private Log log = LogFactory.getLog(MakePlateController.class);
	public MakePlateController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);

	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		Container container = new Container();
		container.setContainerId(new Integer(-1));
		int ip = RequestUtils.getIntParameter(request, "projectId",-1);
		int im = RequestUtils.getIntParameter(request, "motherContainerId",-1);
		if(ip != -1){
			container.setProject(projectManager.getProject(new Integer(ip)));
		}
		
		
		if(im != -1){
			container.setMotherContainer(containerManager.getContainer(new Integer(im)));
	
		}
	
		return container;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors) throws Exception {
		Container container = (Container) command;
		
		String plateFormat = RequestUtils.getStringParameter(request, "plateFormat","");
		String  sampleTypeSuffix = RequestUtils.getStringParameter(request, "sampleType","");
		String  makePlateBlindly = RequestUtils.getStringParameter(request, "makePlateBlindly","");
		String wellPosition = RequestUtils.getStringParameter(request, "wellPosition","");
		
		log.debug("the container type is " + container.getContainerType().getName());
		log.debug("the container name is " + container.getName());
		
		MultipartHttpServletRequest  mrequest = (MultipartHttpServletRequest)request;
		MultipartFile aFile = mrequest.getFile("file");
		InputStream is = aFile.getInputStream();
		
		if(container.getContainerType().getName().equals("-")){
			errors.rejectValue( "containerType","error.required",new String[]{"Container Type"},"Container Type is required");
			return showForm(request, response, errors);
		}
		
		if(plateFormat == null || plateFormat.length()==0){
			errors.reject("error.required",new String[]{"Plate file format"},"Plate file format is required");
			return showForm(request, response, errors);
		}
		
		
		if(aFile.isEmpty()){
			errors.reject( "error.emptyFile","Uploaded file is empty");
			return showForm(request, response, errors);
		}
		
		if(wellPosition == null || wellPosition.length()==0){
			errors.reject("error.required",new String[]{"Allocate well location"},"Allocate well location is required");
			return showForm(request, response, errors);
		}

		
		Map results = null;
		String parseResult = "";
		boolean allocateWellPosition = false;
		if(wellPosition.equals("Yes")){
			allocateWellPosition = true;
		}
		
		int plateFormatI = 0;
		if(plateFormat.equals("plate")){
			plateFormatI = PLATE_FORMAT;
		}else if(plateFormat.equals("sampleId")){
			plateFormatI = SAMPLEID_FORMAT;
		}else if(plateFormat.equals("sampleIdWell")){
			plateFormatI = SAMPLEID_WELL_FORMAT;
		}else if(plateFormat.equals("sampleIdSampleType")){
			plateFormatI = SAMPLEID_SAMPLETYPE_FORMAT;
		}else if(plateFormat.equals("sampleIdSampleTypeWell")){
			plateFormatI = SAMPLEID_SAMPLETYPE_WELL_FORMAT;
		}else if(plateFormat.equals("sampleIdDupNo")){
			plateFormatI = SAMPLEID_DUPNO_FORMAT;
		}
		
		SamplePlateParser sp = new SamplePlateParser(is,container.getContainerType(),plateFormatI,sampleTypeSuffix);

		parseResult = sp.parseSample();
		results = sp.getSamplesInWell();
		log.debug("the number of sample jis " + results.size());
		is.close();
		
		
		if(parseResult.length() == 0){ // parsing success, save the samples
			try{
				if(!makePlateBlindly.equals("")){
					sampleManager.saveSamplesInContainerBlindly(container,results,allocateWellPosition);
				}else{
					sampleManager.saveSamplesInContainer(container,results,allocateWellPosition);
				}
				ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
				Map myModel = view.getModel();
				myModel.put("message","Have successfully made the plate !");
				myModel.put("containerId",container.getContainerId());
				return view;
			}catch(Exception e){
				parseResult = "Could not make this plate. <br>";
				parseResult += e.toString();
			}
		}
		
		// there is some correctable error in the file, let user correct it and upload again
		Map myModel = new HashMap();
		String message = "Please correct the following error and upload your file again ! <br>";
		
		myModel.put("message",message+parseResult);
	
		return new ModelAndView("errorPage",myModel);


	
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
	{
		Map models = new HashMap();
		Container container = (Container) command;
		
		
		//the allProjects, allLocations and allContainerTypes is all except the current one
		List allProjects = projectManager.getAllProjects();
		List allLocations = agtcManager.getLocations();
		List allContainerTypes = agtcManager.getContainerTypes();
		
		List allSamplePrefixes = agtcManager.getAllSamplePrefixes();
		List allSampleTypes = agtcManager.getSampleTypes();
		
		models.put("availableSampleTypes",allSampleTypes);
	
		models.put("allLocations",allLocations);
		models.put("allContainerTypes",allContainerTypes);
		models.put("allProjects",allProjects);
		
		
		log.debug("referenceData is called");
		return models;
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

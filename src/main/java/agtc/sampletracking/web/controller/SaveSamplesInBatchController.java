/*
 * Created on Dec 7, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.RequestUtils;
import org.springframework.validation.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.comparator.SampleComparator;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.parser.*;
import agtc.sampletracking.bus.report.SatoLabelPrinter;
import org.springframework.web.multipart.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class SaveSamplesInBatchController extends BasicController {
	private Log log = LogFactory.getLog(EditSampleController.class);
	private SampleManager sampleManager;
	private ContainerManager containerManager;
	private AGTCManager agtcManager;
	private ProjectManager projectManager;
	
	public ProjectManager getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	public SaveSamplesInBatchController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
	
	
		SaveSamplesCommand ssc = new SaveSamplesCommand();
		SampleType sampleType = new SampleType();
		ssc.setSampleType(sampleType);
		
		return ssc;
		
	}
	protected ModelAndView onSubmit(HttpServletRequest request,
									HttpServletResponse response,
									Object command,
									BindException errors) throws Exception {
		SaveSamplesCommand ssc = (SaveSamplesCommand) command;
		String format = RequestUtils.getStringParameter(request, "format","");
		//String project = RequestUtils.getStringParameter(request, "","");
		String operation = RequestUtils.getStringParameter(request, "operation","");
		String labelNo = RequestUtils.getStringParameter(request, "labelNo","");
		SampleType sampleType = ssc.getSampleType();
		String isControl = ssc.getIsControl();
		
		String projects = ssc.getProject();
		Project project=null;
		if (projects!=null && projects.length()>0){
			project = projectManager.getProject(new Integer(projects));
		}
		
		MultipartHttpServletRequest  mrequest = (MultipartHttpServletRequest)request;
		MultipartFile aFile = mrequest.getFile("file");
		
		
		if(format == null || format.length()==0){
			errors.reject("error.required",new String[]{"Sample file type"},"Sample file type is required");
			return showForm(request, response, errors);
		}
		
		if(operation == null || operation.length()==0){
			errors.reject("error.required",new String[]{"Data operation"},"Data operation is required");
			return showForm(request, response, errors);
		}
		if(aFile.isEmpty()){
			errors.reject( "error.emptyFile","Uploaded file is empty");
			return showForm(request, response, errors);
		}
		
		InputStream is = aFile.getInputStream();
		String parseResult = "";
		SamplePrefix samplePrefix = agtcManager.getSamplePrefixByDescription(format);
		String message = "";
		SamplesNoWellParser sp = null;
		List printLabels = null;
		
		//Add project for each samples
		// Jianan Xiao  2006-01-25
//		sp = new SamplesNoWellParser(is,samplePrefix,isControl);
		sp = new SamplesNoWellParser(is,samplePrefix,isControl,project);
		
		
		parseResult = sp.parseSample();
		is.close();
		
		if(parseResult.length() == 0){ // parsing success, save the samples
			if(operation.equals("update")){
				List samples = sp.getSampleList();
				log.debug("have got the sample list ");
				try{
					printLabels = sampleManager.updateSamples(sampleType,samples);
					message = "have successfully updated these samples ! <br>";
				}catch(Exception e){
					parseResult = "Could not update the samples. Please make sure that all samples in your file are already in the STS ! <br>";
					parseResult += e.toString();
				}
			}else{				
				List samples = sp.getSampleList();
				log.debug("have got the sample list ");
				try{
					printLabels = sampleManager.saveSamplesOnly(sampleType,samples);
					message = "have successfully saved these samples ! <br>";
				}catch(Exception e){
					parseResult = "Could not save these samples. Please make sure that all samplesIds in your file are new in the STS ! <br>";
					log.debug(e);
					parseResult += e.toString();
				}
			}		
		}
		
		if(parseResult.length()==0){
			// if user want to print labels at the same time
			try{
				List printSampleList = new ArrayList();
				int labels = Integer.parseInt(labelNo);
				if(labels>0){
					for(int i=1;i<=labels;i++){
						printSampleList.addAll(printLabels);
					}
				}
				SatoLabelPrinter satoP = new SatoLabelPrinter();
				Collections.sort(printSampleList,new SampleComparator());
				satoP.printSatoLabel(printSampleList);
				message += "<br> Please pick up your labels at the Label printer !";
				
			}catch(Exception e){
				
			}
			
			return new ModelAndView("successComplete","message",message);
			
		}
		
//		if it comes here, something is wrong, either because of parsing, or because of database
		// operation, parseResult get the message from catch in the above code
		
		Map myModel = new HashMap();
		message = "Please correct the following error and upload your file again ! <br>";
		
		myModel.put("message",message+parseResult);
	
		return new ModelAndView("errorPage",myModel);		
	}

	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   throws java.lang.Exception
	{
		Map models = new HashMap();
		
		List allSampleTypes = agtcManager.getSampleTypes();
		List allSamplePrefixes = agtcManager.getAllSamplePrefixes();
		List allProjects = projectManager.getAllProjects();
		models.put("availableSampleTypes",allSampleTypes);
		models.put("allSamplePrefixes",allSamplePrefixes);
		models.put("allProjects",allProjects);

		return models;
	}

	/**
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
	 * @return
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
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
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

	/**
	 * @param manager
	 */
	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
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

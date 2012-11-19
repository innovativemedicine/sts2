/*
 * Created on Feb 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.model.SamplePrefix;

/**
 * @author Gloria
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SamplePrefixController extends BasicController {
	private AGTCManager agtcManager;
	private List allFields;
	private List allSamplePrefixes;
	private int FIELD_LENGTH = 20;
	private Log log = LogFactory.getLog(SamplePrefixController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController
	 * #showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public SamplePrefixController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		SamplePrefix samplePrefix=null;
		int i = RequestUtils.getIntParameter(request, "samplePrefixId",-1);

		if(i==-1){
			samplePrefix = new SamplePrefix();
			samplePrefix.setSamplePrefixId(new Integer(-1));
			String[] a = new String[FIELD_LENGTH];
			for(int idx=0;idx<FIELD_LENGTH;idx++){
				a[idx] = "";
			
			}
			samplePrefix.setColumnArray(a);
			
		}else{
			samplePrefix = agtcManager.getSamplePrefix(new Integer(i));
			String columns = samplePrefix.getColumns();
			
			if(columns != null && columns.length()>0){
				String[] cols = columns.split(",");
				String[] columnArray = new String[FIELD_LENGTH];
				for(int b=0;b<FIELD_LENGTH;b++){
					if(b<cols.length){
						columnArray[b] = cols[b];
					}else{
						columnArray[b] = "";
					}
				}
				samplePrefix.setColumnArray(columnArray);
			}
		}

		return samplePrefix;
	}

//	protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
			throws Exception {
		String submit = RequestUtils.getStringParameter(request, "Submit",null);
			
		SamplePrefix samplePrefix = (SamplePrefix) command;
		log.debug("the prefix is " + samplePrefix.getPrefix());
		if (samplePrefix.getPrefix()==null || samplePrefix.getPrefix().equals("null")|| samplePrefix.getPrefix().equals("")){
			samplePrefix.setPrefix("");
		}
		String s = "";
		for (int i = 0; i < samplePrefix.getColumnArray().length; i++) {
			log.debug("the " + i  + " " + samplePrefix.getColumnArray()[i]);
		   if(!samplePrefix.getColumnArray()[i].trim().equals("")){
		   	if(i == 0){
		   		s += samplePrefix.getColumnArray()[i].trim();
		   	}else{
		   		s += ","+samplePrefix.getColumnArray()[i].trim();
		   	}
		   }
		}
		
		

		log.debug("the first column is " + samplePrefix.getColumnArray()[0]);
		
		if(submit.compareTo("New")==0)
			samplePrefix.setSamplePrefixId(new Integer(-1));
		samplePrefix.setColumns(s);
		try{
			agtcManager.saveSamplePrefix(samplePrefix);
		}catch(Exception e){
			errors.rejectValue("description","error.notUnique",new String[]{samplePrefix.getDescription()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Map referenceData(HttpServletRequest request)
				throws java.lang.Exception{
		//super.referenceData(request);
		Map m = new HashMap();
		allSamplePrefixes = agtcManager.getAllSamplePrefixes();
		allFields = new ArrayList();
		allFields.add("");
		allFields.add("Internal ID");
		allFields.add("External ID");
		allFields.add("Patient First Name");
		allFields.add("Patient Middle Name");
		allFields.add("Patient Last Name");
		allFields.add("Patient Gender");
		allFields.add("Patient Age");
		allFields.add("Patient Birth Date");
		allFields.add("Patient Note");
		allFields.add("Sample Receive Date");
		allFields.add("Sample Made Date");
		allFields.add("Sample Transfer Date");
		allFields.add("Sample Concentration");
		allFields.add("Sample Concentration Reading Date");
		allFields.add("Sample Volumn");
		allFields.add("Sample Volumn Reading Date");
		allFields.add("Sample Another External ID");
		allFields.add("Family ID");
		allFields.add("Sample Note");
		allFields.add("Sample Status");
		allFields.add("Sample Duplication No.");
		
		m.put("allSamplePrefixes",allSamplePrefixes);
		m.put("allFields",allFields);
		return m;
	}

	/**
	 * This method is for debug during development when ther is binding errors
	 */
	
	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
			 javax.servlet.http.HttpServletResponse response,
			 java.lang.Object command,
			 BindException errors)
	  throws java.lang.Exception
	{
		SamplePrefix samplePrefix = (SamplePrefix) command;
		log.debug(samplePrefix);
		log.debug(errors);
		return null;
	}
	*/
 
	
	/**
	 * @return
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	/**
	 * @param manager
	 */
	public void setAgtcManager(AGTCManager manager) {
		agtcManager = manager;
	}
	
	

	/**
	 * @return Returns the allFields.
	 */
	public List getAllFields() {
		return allFields;
	}
	/**
	 * @param allFields The allFields to set.
	 */
	public void setAllFields(List allFields) {
		this.allFields = allFields;
	}
	/**
	 * @return Returns the allSamplePrefixes.
	 */
	public List getAllSamplePrefixes() {
		return allSamplePrefixes;
	}
	/**
	 * @param allSamplePrefixes The allSamplePrefixes to set.
	 */
	public void setAllSamplePrefixes(List allSamplePrefixes) {
		this.allSamplePrefixes = allSamplePrefixes;
	}
}

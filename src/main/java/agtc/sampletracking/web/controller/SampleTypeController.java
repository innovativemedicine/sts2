/**
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.RequestUtils;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.manager.*;
import java.util.*;

 
public class SampleTypeController extends BasicController {
	private AGTCManager agtcManager;
	private List LSampleTypes;
	private Log log = LogFactory.getLog(SampleTypeController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController
	 * #showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public SampleTypeController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		SampleType sampleType=null;
		int i = RequestUtils.getIntParameter(request, "sampleTypeId",-1);

		if(i==-1){
			sampleType = new SampleType();
			sampleType.setSampleTypeId(new Integer(-1));
		}else{
			sampleType = agtcManager.getSampleType(new Integer(i));
		}

		return (Object)sampleType;
	}

//	protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
			throws Exception{
		String submit = RequestUtils.getStringParameter(request, "Submit",null);
			
		SampleType sampleType = (SampleType) command;
		String labelnu = request.getParameter("isSource");
		//System.out.println("Is Source="+labelnu);
		if ((labelnu!=null)&&(labelnu.equals("on"))) sampleType.setIsSource("on");
		else sampleType.setIsSource("off");
		if(submit.compareTo("New")==0){
			sampleType.setSampleTypeId(new Integer(-1));
		}
		
		try{
			agtcManager.saveSampleType(sampleType);
			
			//refersh Sample types for register sample
			agtcManager.getSampleTypesWithVials();
			//refersh Sample Types List
			LSampleTypes = agtcManager.getSampleTypes();
		}catch(Exception e){
			errors.reject("error.notUnique",new String[]{sampleType.getName()+ " or " + sampleType.getSuffix()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Map referenceData(HttpServletRequest request)
				throws java.lang.Exception{
		//super.referenceData(request);
		Map m = new HashMap();
		LSampleTypes = agtcManager.getSampleTypes();
		m.put("LSampleTypes",LSampleTypes);
		return m;
	}

 
	
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
	
	public List getSampleTypes(){
		return LSampleTypes;
	}

	public void setLSampleTypes(List stock){
		LSampleTypes=stock;
	}

}

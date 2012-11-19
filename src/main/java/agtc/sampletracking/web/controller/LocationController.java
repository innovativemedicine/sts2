/*
 * Created on Jan 20, 2005
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
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.RequestUtils;
import org.springframework.validation.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import java.util.*;

 
public class LocationController extends BasicController {
	private AGTCManager agtcManager;
	private List LLocations;
	private Log log = LogFactory.getLog(LocationController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController
	 * #showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public LocationController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		Location location=null;
		int i = RequestUtils.getIntParameter(request, "locationId",-1);

		if(i==-1){
			location = new Location();
			location.setLocationId(new Integer(-1));
		}else{
			location = agtcManager.getLocation(new Integer(i));
		}

		return (Object)location;
	}

//	protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
			throws Exception {
		String submit = RequestUtils.getStringParameter(request, "Submit",null);
			
		Location location = (Location) command;
		
//		System.out.println("command fax="+(location.getContact()).getFax());
//		System.out.println("request fax="+request.getParameterValues("fax")[0]);
		
		if(submit.compareTo("New")==0){
			location.setLocationId(new Integer(-1));
		}
		try{
			agtcManager.saveLocation(location);
		}catch(Exception e){
			errors.rejectValue("name","error.notUnique",new String[]{location.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Map referenceData(HttpServletRequest request)
				throws java.lang.Exception{
		//super.referenceData(request);
		Map m = new HashMap();
		LLocations = agtcManager.getLocations();
		m.put("LLocations",LLocations);
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
	
	public List getLocations(){
		return LLocations;
	}

	public void setLLocations(List stock){
		LLocations=stock;
	}

}

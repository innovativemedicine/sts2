package agtc.sampletracking.web.controller;
/**
 * @author Jianan Xiao, Dec 22, 2004
 *
 * Management Instrument
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

 
public class InstrumentController extends BasicController {
	private AGTCManager agtcManager;
	private List LInstruments;
	private Log log = LogFactory.getLog(InstrumentController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController
	 * #showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public InstrumentController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		Instrument instrument=null;
		int i = RequestUtils.getIntParameter(request, "instrumentId",-1);

		if(i==-1){
			instrument = new Instrument();
			instrument.setInstrumentId(new Integer(-1));
		}else{
			instrument = agtcManager.getInstrument(new Integer(i));
		}

		return (Object)instrument;
	}

//	protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
			throws Exception {
		String submit = RequestUtils.getStringParameter(request, "Submit",null);
			
		Instrument instrument = (Instrument) command;
		if(submit.compareTo("New")==0){
			instrument.setInstrumentId(new Integer(-1));
		}
		
		try{
			agtcManager.saveInstrument(instrument);
		}catch(Exception e){
			errors.rejectValue( "name","error.notUnique",new String[]{instrument.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Map referenceData(HttpServletRequest request)
				throws java.lang.Exception{
		//super.referenceData(request);
		Map m = new HashMap();
		LInstruments = agtcManager.getInstruments();
		m.put("LInstruments",LInstruments);
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
	
	public List getInstruments(){
		return LInstruments;
	}

	public void setLInstruments(List stock){
		LInstruments=stock;
	}

}

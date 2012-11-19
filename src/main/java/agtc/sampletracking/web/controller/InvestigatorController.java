package agtc.sampletracking.web.controller;
/**
 * @author Jianan Xiao, Dec 22, 2004
 *
 * Management Investigator
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

 
public class InvestigatorController extends BasicController {
	private AGTCManager agtcManager;
	private List LInvestigators;
	private Log log = LogFactory.getLog(InvestigatorController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController
	 * #showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public InvestigatorController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		Investigator investigator=null;
		int i = RequestUtils.getIntParameter(request, "investigatorId",-1);

		if(i==-1){
			investigator = new Investigator();
			investigator.setInvestigatorId(new Integer(-1));
		}else{
			investigator = agtcManager.getInvestigator(new Integer(i));
			if (investigator.getContact()==null)
				investigator.setContact( new Contact());
		}

		return (Object)investigator;
	}

//	protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
			throws Exception {
		String submit = RequestUtils.getStringParameter(request, "Submit",null);
			
		Investigator investigator = (Investigator) command;
		
//		System.out.println("command fax="+(investigator.getContact()).getFax());
//		System.out.println("request fax="+request.getParameterValues("fax")[0]);
		
		if(submit.compareTo("New")==0){
			investigator.setInvestigatorId(new Integer(-1));
		}
		try{
			agtcManager.saveInvestigator(investigator);
		}catch(Exception e){
			errors.reject("error.notUnique",new String[]{investigator.getName().getFname() + " " + investigator.getName().getLname()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Map referenceData(HttpServletRequest request)
				throws java.lang.Exception{
		//super.referenceData(request);
		Map m = new HashMap();
		LInvestigators = agtcManager.getInvestigators();
		m.put("LInvestigators",LInvestigators);
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
	
	public List getInvestigators(){
		return LInvestigators;
	}

	public void setLInvestigators(List stock){
		LInvestigators=stock;
	}

}

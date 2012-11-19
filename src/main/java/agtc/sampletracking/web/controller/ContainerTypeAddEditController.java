package agtc.sampletracking.web.controller;
/**
 * @author Jianan Xiao, Dec 13, 2004
 *
 * Management Container type
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

 
public class ContainerTypeAddEditController extends BasicController {
	private AGTCManager agtcManager;
	private List containerTypes;
	private Log log = LogFactory.getLog(ContainerTypeAddEditController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController
	 * #showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public ContainerTypeAddEditController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		ContainerType containerType=null;
		int i = RequestUtils.getIntParameter(request, "containerTypeId",-1);
		
		if(i==-1){
			containerType = new ContainerType();
			containerType.setContainerTypeId(new Integer(-1));
		}else{
			containerType = agtcManager.getContainerType(new Integer(i));
		}

		return (Object)containerType;
	}

//	protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
			throws Exception {
		String submit = RequestUtils.getStringParameter(request, "Submit",null);
		try{
			int typeId = Integer.parseInt(request.getParameter("capacity"));
			}  catch (NumberFormatException e){
				errors.rejectValue("capacity", "integer", "integer");
			}
			
		ContainerType containerType = (ContainerType) command;
		String name = containerType.getName();
		if(!name.startsWith(SAMPLE_BOX) || !name.startsWith(REAGENT_BOX) || !name.startsWith(PLATE)){
			errors.rejectValue("name","error.nameRule",new String[]{containerType.getName()},"Container type name not confirm to naming rule");
			return showForm(request, response, errors);
		}
		if(submit.compareTo("New")==0){
			containerType.setContainerTypeId(new Integer(-1));
		}
		
		try{
			agtcManager.saveContainerType(containerType);
		}catch(Exception e){
			errors.rejectValue("name","error.notUnique",new String[]{containerType.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	protected Map referenceData(HttpServletRequest request)
				throws java.lang.Exception{
		//super.referenceData(request);
		Map m = new HashMap();
		containerTypes = agtcManager.getContainerTypes();
		m.put("containerTypes",containerTypes);
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
	
	public List getContainerTypes(){
		return containerTypes;
	}

	public void setContainerTypes(List stock){
		containerTypes=stock;
	}

}

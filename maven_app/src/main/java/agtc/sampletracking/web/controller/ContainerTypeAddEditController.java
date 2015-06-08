package agtc.sampletracking.web.controller;

/**
 * @author Jianan Xiao, Dec 13, 2004
 *
 * Management Container type
 */

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.model.ContainerType;

public class ContainerTypeAddEditController extends BasicController {
	private AGTCManager	agtcManager;
	private List		containerTypes;
	private Log			log	= LogFactory.getLog(ContainerTypeAddEditController.class);

	public ContainerTypeAddEditController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);

	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		// log.debug("project name is " + projectManager.getProject(new
		// Integer(RequestUtils.getRequiredIntParameter(request,
		// "projectId"))).getName());
		ContainerType containerType = null;
		int i = ServletRequestUtils.getIntParameter(request, "containerTypeId", -1);

		if (i == -1) {
			containerType = new ContainerType();
			containerType.setContainerTypeId(new Integer(-1));
		} else {
			containerType = agtcManager.getContainerType(new Integer(i));
		}

		return (Object) containerType;
	}

	// protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		
		String submit = ServletRequestUtils.getStringParameter(request, "Submit", null);
		
		try {
			int typeId = Integer.parseInt(request.getParameter("capacity"));
		} catch (NumberFormatException e) {
			errors.rejectValue("capacity", "integer", "integer");
		}

		
		ContainerType containerType = (ContainerType) command;
		String name = containerType.getName();
		String message = "";
		
		if (!name.startsWith(SAMPLE_BOX) && !name.startsWith(REAGENT_BOX) && !name.startsWith(PLATE)) {

			message = "Container type name does not conform to naming rule";

			ModelAndView mav = new ModelAndView(new RedirectView("containerType.htm"));
			mav.addObject("err", message);

			return mav;
		}
		if (submit.compareTo("New") == 0) {
			containerType.setContainerTypeId(new Integer(-1));
		}

		try {
			agtcManager.saveContainerType(containerType);
		} catch (Exception e) {
			message = "Container name not unique";
			
			ModelAndView mav = new ModelAndView(new RedirectView("containerType.htm"));
			mav.addObject("err", message);

			return mav;			
		}

		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	protected Map referenceData(HttpServletRequest request) throws java.lang.Exception {
		Map m = new HashMap();
		containerTypes = agtcManager.getContainerTypes();
		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		
		m.put("containerTypes", containerTypes);
		m.put("err", err);
		return m;
	}

	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager manager) {
		agtcManager = manager;
	}

	public List getContainerTypes() {
		return containerTypes;
	}

	public void setContainerTypes(List stock) {
		containerTypes = stock;
	}

}

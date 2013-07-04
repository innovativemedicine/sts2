/*
 * Created on Apr 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.report.SatoLabelPrinter;

public class ContainerListController extends BasicController {
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new ContainerListController();
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,BindException errors) throws Exception{
		String action = ServletRequestUtils.getStringParameter(request, "action", "");
		List containerList = (List) WebUtils.getSessionAttribute(request,"containerList");
		String message = "";
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		
		if (action.equalsIgnoreCase("Print Labels")) {
		// Print
		SatoLabelPrinter satoP = new SatoLabelPrinter();
		satoP.printPlateLabel(containerList, contextPath);
				
		message = "Labels are available at the Label printer.";
		} else if (action.equalsIgnoreCase("Export Data")) {
		
			
		}
		
		ModelAndView mav = new ModelAndView("containerList");
		WebUtils.setSessionAttribute(request,"containerList",containerList);
		mav.addObject("containerList", containerList);
		mav.addObject("message", message);

		return mav;
	}
	
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors)

	{
		Map models = new HashMap();
		List<Container> containerList = (List<Container>) WebUtils.getSessionAttribute(request,
				"containerList");
		
		String message = RequestUtils
				.getStringParameter(request, "message", "");
	
		if (!message.equals("")) {
			models.put("message", message);
		}

		models.put("containerList", containerList);
		return models;
	}
}

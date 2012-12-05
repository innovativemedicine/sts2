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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.web.command.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.comparator.SampleComparator;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.report.SatoLabelPrinter;

public class SampleListController extends BasicController {
	
	private SampleManager sampleManager;
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new SampleListController();
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,BindException errors) throws Exception{
		
		List sampleList = (List) WebUtils.getSessionAttribute(request,"sampleList");
		
		// Print
		SatoLabelPrinter satoP = new SatoLabelPrinter();
		//Collections.sort(sampleList,new SampleComparator());
		satoP.printSampleLabel(sampleList);
		
		System.out.println("Print");
		
		String message = "Labels are available at the Label printer.";
		
		ModelAndView mav = new ModelAndView("sampleList");
		WebUtils.setSessionAttribute(request,"sampleList",sampleList);
		mav.addObject("sampleList", sampleList);
		mav.addObject("message", message);

		return mav;
	}
	
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors)

	{
		Map models = new HashMap();
		List<Sample> sessionAttribute = (List<Sample>) WebUtils.getSessionAttribute(request,
				"sampleList");
		List<Sample> sampleList = sessionAttribute;
		String message = RequestUtils
				.getStringParameter(request, "message", "");
		if (!message.equals("")) {
			models.put("message", message);
		}

		models.put("sampleList", sampleList);
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
}

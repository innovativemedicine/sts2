/*
 * Created on Jun 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.model.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchPatientController extends SimpleFormController {
	private Log log = LogFactory.getLog(SearchPatientController.class);
	private SampleManager sampleManager;
	
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		
		String intSampleId = RequestUtils.getStringParameter(request, "intSampleId","");
		Patient patient = sampleManager.getPatient(intSampleId.trim());
		
		if (patient == null) {
			errors.reject("error.sampleNotExisted",new String[]{intSampleId},intSampleId + " not in STS !");
			return showForm(request, response, errors);
		}


		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("intSampleId",intSampleId);
		return view;
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

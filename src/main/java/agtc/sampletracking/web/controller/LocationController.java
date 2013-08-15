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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import agtc.sampletracking.model.Location;

public class LocationController extends BasicController {
	private AGTCManager	agtcManager;
	private List		LLocations;
	private Log			log	= LogFactory.getLog(LocationController.class);

	public LocationController() {
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		Location location = null;
		int i = ServletRequestUtils.getIntParameter(request, "locationId", -1);

		if (i == -1) {
			location = new Location();
			location.setLocationId(new Integer(-1));
		} else {
			location = agtcManager.getLocation(new Integer(i));
		}

		return (Object) location;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		String submit = ServletRequestUtils.getStringParameter(request, "Submit", null);

		Location location = (Location) command;

		if (submit.compareTo("New") == 0) {
			location.setLocationId(new Integer(-1));
		}
		try {
			agtcManager.saveLocation(location);
		} catch (Exception e) {
			String err = "Location name not nunique";

			ModelAndView mav = new ModelAndView(new RedirectView("location.htm"));
			mav.addObject("err", err);

			return mav;

		}

		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	protected Map referenceData(HttpServletRequest request) throws java.lang.Exception {

		Map m = new HashMap();
		LLocations = agtcManager.getLocations();
		m.put("LLocations", LLocations);
		
		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		m.put("err", err);

		return m;
	}


	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager manager) {
		agtcManager = manager;
	}

	public List getLocations() {
		return LLocations;
	}

	public void setLLocations(List stock) {
		LLocations = stock;
	}

}

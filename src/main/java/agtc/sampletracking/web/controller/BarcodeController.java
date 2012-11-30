package agtc.sampletracking.web.controller;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import org.springframework.validation.BindException;

public class BarcodeController extends BasicController {
	private BarcodeManager barcodeManager;
	private Log log = LogFactory.getLog(BarcodeController.class);

	public BarcodeController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new BarcodeCommand();
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		
		ModelAndView view = new ModelAndView(new RedirectView("barcode.htm"));
		Map model = view.getModel();
		
		// Handle Submit is where all the actions are processed. Defines all the processing in barcodeManager
		handleSubmit(request, command, model);

		return view;
	}

	 protected String processBarcode(BarcodeCommand barcodeItem) {
		 return barcodeManager.processBarcode(barcodeItem);
	 }

	protected void handleSubmit(HttpServletRequest request, Object command, Map model) {
		BarcodeCommand barcodeItem = (BarcodeCommand) command;
		System.out.println(barcodeItem.getBarcodeItem());
		List actionList = (List) WebUtils.getOrCreateSessionAttribute(request.getSession(), "actionedList", ArrayList.class);
		String action = RequestUtils.getStringParameter(request, "action", "");
		
		if (action.equals("REVIEW")) {
						
			actionList = new ArrayList();
			model.put("message", "Actions Reviewed");
		}
		else if (action.equals("ADD")) {
			String barcodeResponse = processBarcode(barcodeItem);
			
			// Change to barcodeResponse later
			actionList.add(barcodeItem);
			WebUtils.setSessionAttribute(request, "actionedList", actionList);
			
			model.put("message", "Action Added");
		}
		else if (action.equals("RESET")) {
			actionList = new ArrayList();
		}

		WebUtils.setSessionAttribute(request, "actionedList", actionList);
		
		return;
	}

	protected Map referenceData(HttpServletRequest request) throws java.lang.Exception {
		// Needed to populate the search fields
		Map models = new HashMap();

		String message = RequestUtils.getStringParameter(request, "message", "");
				
		List actionedList = (List) WebUtils.getOrCreateSessionAttribute(request.getSession(), "actionedList", ArrayList.class);

		models.put("actionedList", actionedList);

		if (!message.equals("")) {
			models.put("message", message);
		}

		return models;
	}

	public BarcodeManager getBarcodeManager() {
		return barcodeManager;
	}

	public void setBarcodeManager(BarcodeManager manager) {
		barcodeManager = manager;
	}
}

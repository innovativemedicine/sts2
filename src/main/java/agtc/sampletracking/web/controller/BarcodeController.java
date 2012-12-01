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
	private ProjectManager projectManager;
	private SampleManager sampleManager;

	private Log log = LogFactory.getLog(BarcodeController.class);

	public BarcodeController(){
		//initialize the form from the formBackingObject
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

	 protected String processBarcode(BarcodeCommand barcodeItem, String storeContainer) {
			String barcodeString = barcodeItem.toString();
			String response = new String() ;
			
		if (barcodeString.matches("S-[a-zA-Z]+\\d+-[A-Z]+-\\d+")) {
			String[] bcArray = barcodeString.split("-");
			String sampleId = bcArray[1];
			String sampleTypeSuffix = bcArray[2];
			Integer sampleDupNo = Integer.parseInt(bcArray[3]);

			Sample sample = barcodeManager.getSample(sampleId,
					sampleTypeSuffix, sampleDupNo);
			if (sample == null) {
//				response += barcodeManager.addSample(sampleId, sampleTypeSuffix,
//						sampleDupNo);
				sample = barcodeManager.addSample(sampleId,  sampleTypeSuffix,  sampleDupNo);

				System.out.println("Patient2" + sample.getPatient().getIntSampleId());
				System.out.println("Sample Type2" + sample.getSampleType());
				System.out.println("dupNo2" + sample.getSampleDupNo());
				
				try {
					sampleManager.saveSample(sample);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (sample.getStatus() != null) {
					if (sample.getStatus().equalsIgnoreCase("stored")) {
						response += "RemoveSample:";
					} else if (sample.getStatus()
							.equalsIgnoreCase("registered")) {
						if (storeContainer.isEmpty()) {
							response += "ReceiveSample:";
						} else {
							response += "StoreSample(";
							response += storeContainer;
							response += ")";
							response += ":";
						}
					}
				}
				else{
					response += "UpdateSampleStatus:";
				}
			}
		}
			else if(barcodeString.matches("C-\\w+")) {
				
				String[] bcArray = barcodeString.split("-");
				String containerName = bcArray[1];
				Container container = barcodeManager.getContainer(containerName);
				
				if(container == null) {
					response += "AddContainer:";	
				}
				else
				{
					response += "StoreContainer:";
				}			
			}
			else
			{
				response += "Error(Unrecognized barcode format):";
			}

			response += barcodeString;

			return response;
	 }

	protected void handleSubmit(HttpServletRequest request, Object command, Map model) {
		BarcodeCommand barcodeItem = (BarcodeCommand) command;
		
//		System.out.println(barcodeItem.getBarcodeItem());
		List actionList = (List) WebUtils.getOrCreateSessionAttribute(request.getSession(), "actionedList", ArrayList.class);
		String storeContainer = (String) WebUtils.getOrCreateSessionAttribute(request.getSession(), "storeContainer", String.class);

		String action = RequestUtils.getStringParameter(request, "action", "");
		
		if (action.equals("REVIEW")) {						
			actionList = new ArrayList();
		}
		else if (action.equals("ADD")) {
			String barcodeResponse = processBarcode(barcodeItem, storeContainer);
			
			if(barcodeResponse.startsWith("StoreContainer:"))
			{
				storeContainer = barcodeResponse.split(":")[1];
			}
			// Change to barcodeResponse later
			actionList.add(barcodeResponse);
		}
		else if (action.equals("RESET")) {
			actionList = new ArrayList();
			storeContainer = new String();
		}

		WebUtils.setSessionAttribute(request, "actionedList", actionList);
		WebUtils.setSessionAttribute(request, "storeContainer", storeContainer);
		System.out.println(storeContainer);
		
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
	
	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}
	
	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}


}

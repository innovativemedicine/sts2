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
	private ProjectManager projectManager;
	private SampleManager sampleManager;
	private AGTCManager agtcManager;
	private ContainerManager containerManager;
	
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
			
			Sample sample = sampleManager.getSample(sampleId, sampleTypeSuffix, sampleDupNo);
			if (sample == null) {
				response += addSampleFromBarcode(sampleId,  sampleTypeSuffix,  sampleDupNo);
				
			} 
			else {
				// Update sample status and sample location
				response += updateSampleFromBarcode(sample, storeContainer);
			}
			
			response += sampleId;
			response += sampleTypeSuffix;
			response += sampleDupNo;
		}
			else if(barcodeString.matches("C-\\w+")) {
				
				String[] bcArray = barcodeString.split("-");
				String containerName = bcArray[1];
				Container container = containerManager.getContainer(containerName);
				
				if(container == null) {
					response += "<b>Error</b>(Contained does not exist in database):";
					//To make this work properly, need to open a add Container form and disable barcodes until form is filled.
				}
				else if(containerName.equalsIgnoreCase(storeContainer))
				{
					response += "<b>FinishedStoreToContainer</b>:";
				}
				else
				{
					response += "<b>StoreToContainer</b>:";
				}
				
				response += containerName;
			}
			else
			{
				response += "<b>Error(Unrecognized barcode format)</b>:";
				response += barcodeString;
			}

			return response;
	 }
	 
	 protected String addContainerFromBarcode(String containerName)
		{
		 	//containerManager.saveContainer(containerObject);
		 	return "<b>AddedContainer</b>:";
		}

	protected String updateSampleFromBarcode(Sample sample, String storeContainer) {
		String response = "<b>" + sample.getStatus() + "Sample</b>:";
		String sampleStatus = sample.getStatus(); // Default is unchanged

		// Do we allow sample to be scanned without an associated container??
		if (storeContainer.isEmpty()) {
			if (sampleStatus.equalsIgnoreCase("registered")) {
				response = "<b>ReceivedSample:</b>";
				sampleStatus = "Received";
			} else if (sampleStatus.equalsIgnoreCase("stored")) {
				
				// Retrieving Sample by removing sample from SIC table
				List sics = sampleManager.getSamplesInContainersInBySample(sample.getSampleId());
				SamplesInContainer sic = (SamplesInContainer) sics.get(0);
				sampleManager.removeSamplesInContainer(sic.getSicId());
				
				response = "<b>RetrievedSample:</b>";
				sampleStatus = "Retrieved";
			}
		} else { // Container scanned. Save Samples in Container
			
			Container container = containerManager.getContainer(storeContainer);
			
			try {
				container = sampleManager.storeSampleInContainer(container,
						sample);
			} catch (Exception e1) {
				response = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + e1.getMessage();
				e1.printStackTrace();
				return response;
			}

			try {
				containerManager.saveContainer(container);

				response = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>StoredSample</b>(";
				response += storeContainer;
				response += ")";
				response += ":";
				sampleStatus = "Stored";

			} catch (Exception e) {
				response = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Error</b>(Can't store sample)";
				e.printStackTrace();
			}
		}

		// Special case to deal with legacy samples with no status.
		if (sample.getStatus() == null) {
			sampleStatus = "Registered";
			response = "<b>RegisteredSample:</b>";
		}
		
		// Update Sample Status
		try {
			sampleManager.updateSampleStatus(sample, sampleStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = "<b>Error</b>(Failed to update sample):";
		}
		
		return response;
	}

	 protected String addSampleFromBarcode(String sampleId, String sampleTypeSuffix, Integer sampleDupNo)
		{
		 	String response = "";
		 	
			Sample newSample = new Sample();
				
			SampleType st = agtcManager.getSampleTypeBySuffix(sampleTypeSuffix);

			if (st == null) {
				response = "<b>Error</b>(Unrecognized sample type):";
			} 
			else {
				newSample.setSampleType(st);
				newSample.setSampleDupNo(sampleDupNo);
				
				Patient patient = new Patient(sampleId);
				newSample.setPatient(patient);
				
				try {
					sampleManager.saveSample(newSample);
					response = "<b>AddedSample</b<:";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					response = "<b>Error</b>(Failed to save sample):";
					e.printStackTrace();
				}
				// Activate if we want to prompt user to fill in patient info
//				patient = sampleManager.getPatient(sampleId);
//				if(patient != null){
//					response += "AddPatientAndSample:";
//				}
			}
				return response;
		}
	 
	protected void handleSubmit(HttpServletRequest request, Object command, Map model) {
		BarcodeCommand barcodeItem = (BarcodeCommand) command;
		
		List actionList = (List) WebUtils.getOrCreateSessionAttribute(request.getSession(), "actionedList", ArrayList.class);
		String storeContainer = (String) WebUtils.getOrCreateSessionAttribute(request.getSession(), "storeContainer", String.class);

		String action = RequestUtils.getStringParameter(request, "action", "");
		
		if (action.equals("REVIEW")) {						
			actionList = new ArrayList();
		}
		else if (action.equals("ADD")) {
			String barcodeResponse = processBarcode(barcodeItem, storeContainer);
			
			if(barcodeResponse.startsWith("<b>StoreToContainer</b>:"))
			{
				storeContainer = barcodeResponse.split(":")[1];
			} 
			else if(barcodeResponse.startsWith("<b>FinishedStoreToContainer</b>:"))
			{
				storeContainer = new String();
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

	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager manager) {
		agtcManager = manager;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

}

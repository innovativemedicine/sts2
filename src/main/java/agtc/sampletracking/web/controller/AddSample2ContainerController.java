/*
 * Edited Jan 2013 - Andrew Wong
 *
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SamplesInContainer;
import agtc.sampletracking.web.command.ContainerContentCellUnit;
import agtc.sampletracking.web.command.ContainerContentCommand;

public class AddSample2ContainerController extends ContainerContentsController{
	public AddSample2ContainerController(){
		super();
	}
	
	protected Map referenceData(HttpServletRequest request,
			  Object command,Errors errors)
	   throws Exception
	{
		Container container = containerManager.getContainer(new Integer(RequestUtils.getIntParameter(request, "containerId", -1)));
		Map models = new HashMap();
		if(container!=null){
			models.put("container", container);
		}
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		List numbers = new ArrayList();
		for(int i = 1;i<11;i++){
			numbers.add(new Integer(i));
		}
		models.put("numbers",numbers);
		
		List samples = (List)WebUtils.getSessionAttribute(request,"sampleList");
		List containerList = containerManager.getAllContainers();
		models.put("sampleList",samples);
		models.put("containerList",containerList);
		return models;
	}
	
protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,BindException errors) throws Exception {
		
		int containerId = RequestUtils.getIntParameter(request, "containerId", -1);
		String[] samplesToAdd = RequestUtils.getStringParameters(request, "samplesToAdd");
		List samplesToAddList = Arrays.asList(samplesToAdd);
		List samplesList = (List)WebUtils.getSessionAttribute(request,"sampleList");
		List samplesRemainingList = new ArrayList();
		Container container = containerManager.getContainer(new Integer(containerId));
		
		String message = "";
		boolean ordered = true;

		int spaceAvailable = container.getContainerType().getCapacity()-container.getTotalSamples();
		if(samplesToAddList.size() > spaceAvailable)
		{
			message = "Error: There are only " + spaceAvailable + " spaces available in container. You tried to store " + samplesToAdd.length + " samples.";
		}
		else
		{		
			Iterator sampleListIr = samplesToAddList.iterator();
			
			while(sampleListIr.hasNext())
			{
				int curSampleId = Integer.parseInt((String) sampleListIr.next());
					
				Sample curSample = sampleManager.getSample(curSampleId);
				
				try {
					container = sampleManager.storeSampleInContainer(container,
							curSample);
				} catch (Exception e1) {
					message = e1.getMessage();
					e1.printStackTrace();
				}
	
				try {
					containerManager.saveContainer(container);
					message = samplesToAdd.length + " samples saved successfully";	
				} catch (Exception e) {
					message = e.getMessage();
					e.printStackTrace();
				}
			}
			
			// Remove saved samples from sample list
			Iterator sampleRemainIr = samplesList.iterator();

			while(sampleRemainIr.hasNext()) {
				Sample chkSample = (Sample) sampleRemainIr.next();
				
				if(Arrays.toString(samplesToAdd).contains(String.valueOf(chkSample.getSampleId()))) {
					//System.out.println(chkSample.getPatient().getIntSampleId() + "-" + chkSample.getSampleType().getSuffix());
				}
				else {
					samplesRemainingList.add(chkSample);
				}
			}
			
			WebUtils.setSessionAttribute(request, "sampleList", samplesRemainingList);
			
		}

		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message",message.toString());
		myModel.put("containerId",new Integer(containerId));
		String isOrdered = "";
		if(ordered){
			isOrdered = "ordered";
		}else{
			isOrdered = "unOrdered";
		}
		myModel.put("isOrdered",isOrdered);
		return view;
	}
}

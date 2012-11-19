/*
 * Created on Aug 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.model.Container;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.model.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddSample2ContainerController extends ContainerContentsController{
	public AddSample2ContainerController(){
		super();
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
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
		List allSampleTypes = agtcManager.getSampleTypes();
		List numbers = new ArrayList();
		for(int i = 1;i<11;i++){
			numbers.add(new Integer(i));
		}
		models.put("availableSampleTypes",allSampleTypes);
		models.put("numbers",numbers);
		
		List samples = (List)WebUtils.getSessionAttribute(request,"sampleList");
		List containerList = containerManager.getAllContainers();
		models.put("sampleList",samples);
		models.put("containerList",containerList);
		return models;
	}
}

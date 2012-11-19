/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.*;
import agtc.sampletracking.bus.report.SatoLabelPrinter;


import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
 



/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SampleList4selectController extends BasicController {
	private Log log = LogFactory.getLog(SampleList4selectController.class);
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		SampleListHolder sampleListHolder = (SampleListHolder)WebUtils.getOrCreateSessionAttribute(request.getSession(),"sampleListHolder",SampleListHolder.class);
		String action = RequestUtils.getStringParameter(request, "action","");
		if(action.equals("Put into cart")){

			int currentPage = RequestUtils.getIntParameter(request, "pageNo",0);
			int countBase = currentPage*40;
			int total = 0;
			for(int i=0;i<40;i++){
				String name = Integer.toString(i);
				String a = RequestUtils.getStringParameter(request, name,"-1");
				if(!a.equals("-1")){
					int idx = Integer.parseInt(a);
					sampleListHolder.addToSelectList(countBase+idx);
					total++;
				}
				//log.debug("the " + name + " value is" + a);
			}
			String message = "Have put the " + total + " sample into cart";
			ModelAndView view = new ModelAndView(new RedirectView("sampleList4select.htm"));
			Map myModel = view.getModel();
			myModel.put("message",message);
			myModel.put("pageNo",new Integer(currentPage));
			return view;
		}else if(action.equals("Print label")){
			SampleListHolder listHolder = (SampleListHolder)
											WebUtils.
											getOrCreateSessionAttribute
											(request.getSession(),
													"sampleListHolder",
													SampleListHolder.class);
			List selectList = listHolder.getSelectList();
			if(selectList==null || selectList.size()==0){
				
				errors.reject("error.noSampleSelected","No sample selected for printing");
				return showForm(request, response, errors);
			
			}
			//Collections.sort(selectList,new SampleComparator());
			
			SatoLabelPrinter satoP = new SatoLabelPrinter();
			satoP.printSatoLabel(selectList);
			listHolder.reSet();
			return new ModelAndView("pickupLabel");
			
		}else{// the only possible action left is clear cart
			sampleListHolder.clearSelectList();
			ModelAndView view = new ModelAndView(new RedirectView("samples.htm"));
			return view;
		}
		
	}
		
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request)
	   throws java.lang.Exception
	{
		int currentPage = RequestUtils.getIntParameter(request, "pageNo",0);
		String message = RequestUtils.getStringParameter(request, "message","");
		SampleListHolder sampleListHolder = (SampleListHolder)WebUtils.getOrCreateSessionAttribute(request.getSession(),"sampleListHolder",SampleListHolder.class);
		List originalList = sampleListHolder.getOriginalList();
		int totalPage = (originalList.size()/40);
		log.debug("total page is " + totalPage);
		List sample4ThisPage = null;
		try{
			sample4ThisPage = originalList.subList(((currentPage)*40),((currentPage+1)*40));
		}catch(Exception e){
			//the last page
			sample4ThisPage = originalList.subList((currentPage)*40,originalList.size());
		}
		
		Map models = new HashMap();
		if(!message.equals("")){
			models.put("message",message);
		}
		models.put("totalPage",Integer.toString(totalPage));
		models.put("totalSamples",Integer.toString(originalList.size()));
		models.put("currentPage",Integer.toString(currentPage));
		models.put("sample4ThisPage",sample4ThisPage);
	
		return models;
	}
}

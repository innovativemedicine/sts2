/*
 * Created on Dec 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import agtc.sampletracking.bus.SampleListHolder;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PlateList4selectController extends BasicController {
	private Log log = LogFactory.getLog(PlateList4selectController.class);
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws java.lang.Exception{
		SampleListHolder plateListHolder = (SampleListHolder)WebUtils.getOrCreateSessionAttribute(request.getSession(),"plateListHolder",SampleListHolder.class);
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
					plateListHolder.addToSelectList(countBase+idx);
					total++;
				}
				//log.debug("the " + name + " value is" + a);
			}
			String message = "Have put the " + total + " plate into cart";
			ModelAndView view = new ModelAndView(new RedirectView("plateList4select.htm"));
			Map myModel = view.getModel();
			myModel.put("message",message);
			myModel.put("pageNo",new Integer(currentPage));
			return view;
		}else if(action.equals("Print label")){
			ModelAndView view = new ModelAndView(new RedirectView("printLabel.htm"));
			Map myModel = view.getModel();
			myModel.put("labelType","plate");
			return view;
			
		}else{// the only possible action left is clear cart
			plateListHolder.clearSelectList();
			ModelAndView view = new ModelAndView(new RedirectView("plates.htm"));
			return view;
		}
		
	}
		
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request)
	   throws java.lang.Exception
	{
		int currentPage = RequestUtils.getIntParameter(request, "pageNo",0);
		String message = RequestUtils.getStringParameter(request, "message","");
		SampleListHolder plateListHolder = (SampleListHolder)WebUtils.getOrCreateSessionAttribute(request.getSession(),"plateListHolder",SampleListHolder.class);
		List originalList = plateListHolder.getOriginalList();
		int totalPage = (originalList.size()/40);
		List plate4ThisPage = null;
		try{
			plate4ThisPage = originalList.subList(((currentPage)*40),((currentPage+1)*40));
		}catch(Exception e){
			//the last page
			plate4ThisPage = originalList.subList((currentPage)*40,originalList.size());
		}
		
		Map models = new HashMap();
		if(!message.equals("")){
			models.put("message",message);
		}
		models.put("totalPage",Integer.toString(totalPage));
		models.put("totalPlates",Integer.toString(originalList.size()));
		models.put("currentPage",Integer.toString(currentPage));
		models.put("plate4ThisPage",plate4ThisPage);
	
		return models;
	}
}

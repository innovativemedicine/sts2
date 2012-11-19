/*
 * Created on Jan 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.dao.*;
import org.springframework.validation.BindException;
/**
 * @author Jianan Xiao, Jan 19, 2005
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateResultToCGGController extends BasicController{
	private AGTCManager agtcManager;	
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		//No command, So need override this function. Do nothing here
		String nothing = new String("Do nothing.");
		return nothing;
	}
	
	protected ModelAndView onSubmit(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, 
			java.lang.Object command,BindException errors) 
			throws java.lang.Exception{
		
		
		String message = agtcManager.updateResultToCGG();
		message = "Have successfully updated the Genotype Result to CGG <br>" + message;

		//if (al == null) return new ModelAndView(new RedirectView(getSuccessView()));
		return new ModelAndView("successComplete","message",message);
	}

	/**
	 * @return
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	/**
	 * @param manager
	 */
	public void setAgtcManager(AGTCManager manager) {
		agtcManager = manager;
	}

}
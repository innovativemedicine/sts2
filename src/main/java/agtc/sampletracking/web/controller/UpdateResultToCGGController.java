/*
 * Created on Jan 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.web.servlet.*;
import agtc.sampletracking.bus.manager.*;
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
			HttpServletRequest request,
			HttpServletResponse response, 
			Object command,BindException errors) 
			throws java.lang.Exception{
		
		ModelAndView mav = new ModelAndView("updateResultToCGG");
	
		String message = agtcManager.updateResultToCGG();
		message = "Have successfully updated the Genotype Result to CGG <br>" + message;

		mav.addObject("message", message);
		
		return mav;
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
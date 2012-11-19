/*
 * Created on Oct 27, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import agtc.sampletracking.model.*;
import org.springframework.web.util.WebUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.RequestUtils;
import javax.servlet.http.*;

/**
 * @author Gloria Deng
 *
 * This user role managerment is not a good solution. Consider to use Acegi security system for the Spring later
 */
public class CheckUserRoleInterceptor extends HandlerInterceptorAdapter {
	private Log log = LogFactory.getLog(CheckUserRoleInterceptor.class);
	
	public boolean preHandle(javax.servlet.http.HttpServletRequest request,
						   javax.servlet.http.HttpServletResponse response,
						   java.lang.Object handler)
					throws java.lang.Exception
	{
		/**
		User user = (User)WebUtils.getSessionAttribute(request, "user");
		String uri = request.getRequestURI();
		if(user == null ){
			if(uri.equals("/SampleTracking/index.htm")){
				return true;
			}else{
			
				response.sendRedirect("index.htm");
			
				return true;
			}
		}else{
			int userPreviledge = user.getUserRole().getUserPreviledge();
			//log.debug("uri is" + request.getRequestURI());
			
			int actionLevel = StsUtil.getActionLevel(uri);
			if(userPreviledge < actionLevel){
				response.sendRedirect("notAllowed.html");
				//return  false;
			}
		}
		*/
		return true;
	}
}

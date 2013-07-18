/*
 * Created on Sep 28, 2005
 * 
 * Jianan Xiao
 * 
 * The simple controller for login/logout
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.controller;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.context.Context;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.security.SecureContext;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.model.User;

public class LoginFormController implements Controller {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		Context context = ContextHolder.getContext();
		SecureContext sc = (SecureContext) context;
		Authentication auth = sc.getAuthentication();
		User user = (User) auth.getPrincipal();
		String userName = user.getLoginname();
		
		Set userRoles = user.getROLESs();
		
		String userRole = "GUEST";
		
		for (Object roles: userRoles)
		{
			userRole = roles.toString();
		}
		        
        WebUtils.setSessionAttribute(request, "userRole", userRole);
        WebUtils.setSessionAttribute(request, "userName", userName);

        String s = request.getParameter("login");
        if (s!=null) {
        	request.getSession().invalidate();
        	return new ModelAndView("logout");
        }
        
        return new ModelAndView("login");
    }
}
 
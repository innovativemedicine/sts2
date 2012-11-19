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

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginFormController implements Controller {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("SpringappController - returning hello view");
        String s = request.getParameter("login");
        if (s!=null) {
        	request.getSession().invalidate();
        	return new ModelAndView("logout");
        }
        
        return new ModelAndView("login");
    }
}
 
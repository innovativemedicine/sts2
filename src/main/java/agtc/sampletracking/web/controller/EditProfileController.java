package agtc.sampletracking.web.controller;
/**
 * Edit user self's information, change password
 * 
 * @author Jianan Xiao 2005-10-06
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.context.Context;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.context.security.SecureContext;
import net.sf.acegisecurity.context.security.SecureContextImpl;
import net.sf.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import agtc.sampletracking.bus.manager.SystemUserManager;
import agtc.sampletracking.model.User;

public class EditProfileController extends BasicController{
	private SystemUserManager userManager;
	private User user;
	private Log log = LogFactory.getLog(SampleTypeController.class);
	
	public EditProfileController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
	}
	
	protected Object formBackingObject(HttpServletRequest request) 
		throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		Context context = ContextHolder.getContext();
		SecureContext sc = (SecureContext) context;
		Authentication auth = sc.getAuthentication();
		user = 
			(User)auth.getPrincipal();
		
		 return (Object)user;
	}
	
//	protected ModelAndView onSubmit(Object command) throws ServletException {
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
			throws Exception{
			
		User user = (User) command;
		
		//1.check password
		String password = request.getParameter("password");
		if(!user.getPassword().equals(password)){
			errors.reject("error.wrongPassword",
					new String[]{ "Wrong Password"},
					"Wrong password or login name");
			return showForm(request, response, errors);
		}
		
		//2.check if new password is the same
		String newPassword1 = request.getParameter("newpassword1");
		String newPassword2 = request.getParameter("newpassword2");
		if(!newPassword1.equals(newPassword2)){
			errors.reject("error.wrongNewPassword",
					new String[]{ "Wrong New Password"},
					"New Password is not the same.");
			return showForm(request, response, errors);
			
		}
		
		//Get new password
		String newPassword = request.getParameter("newpassword1");
		if(newPassword.length()>0){
			user.setPassword(newPassword);
		}
		// save/update user information
		try{
			userManager.saveUser(user);
				        
			//After saveUser, the Role was miss, so, reload it
			user.setROLESs(userManager.
					getUserDAO().
					findRolesByUserId(user.getUserId()));
			
			//To avoid user login again after change password or login name,
			// we must reset Authentication object which used by ACEGI
			final SecureContextImpl newContext = new SecureContextImpl();
	        newContext.setAuthentication
	        			(new UsernamePasswordAuthenticationToken
	        					(user.getLoginname(), user.getPassword()));
	        ContextHolder.setContext(newContext); 

		}catch(Exception e){
			e.printStackTrace();
			errors.reject("error.notUnique",
					new String[]{ user.getLoginname()},"Not unique");
			return showForm(request, response, errors);
		}
		
		log.debug("success view is " + getSuccessView());
		Map myModel = new HashMap();
		myModel.put("message","You have successfully updated your profile !");

		return new ModelAndView("successComplete",myModel);
	}

	public SystemUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(SystemUserManager userManager) {
		this.userManager = userManager;
	}

}

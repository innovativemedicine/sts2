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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.SystemUserManager;
import agtc.sampletracking.model.User;

public class EditProfileController extends BasicController {
	private SystemUserManager	userManager;
	private User				user;
	private Log					log	= LogFactory.getLog(SampleTypeController.class);

	public EditProfileController() {
		// initialize the form from the formBackingObject
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		Context context = ContextHolder.getContext();
		SecureContext sc = (SecureContext) context;
		Authentication auth = sc.getAuthentication();
		user = (User) auth.getPrincipal();

		return (Object) user;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		User user = (User) command;

		// 1.check password
		String password = request.getParameter("password");
		if (!user.getPassword().equals(password)) {

			String err = "Wrong Password or Login Name";

			ModelAndView mav = new ModelAndView(new RedirectView("editProfile.htm"));
			mav.addObject("err", err);

			return mav;
			
		}

		// 2.check if new password is the same
		String newPassword1 = request.getParameter("newpassword1");
		String newPassword2 = request.getParameter("newpassword2");
		if (!newPassword1.equals(newPassword2)) {
			String err = "New Password Does Not Match";

			ModelAndView mav = new ModelAndView(new RedirectView("editProfile.htm"));
			mav.addObject("err", err);

			return mav;
		}

		// Get new password
		String newPassword = request.getParameter("newpassword1");
		if (newPassword.length() > 0) {
			user.setPassword(newPassword);
		}
		// save/update user information
		try {
			userManager.saveUser(user);

			// After saveUser, the Role was miss, so, reload it
			user.setROLESs(userManager.getUserDAO().findRolesByUserId(user.getUserId()));

			// To avoid user login again after change password or login name,
			// we must reset Authentication object which used by ACEGI
			final SecureContextImpl newContext = new SecureContextImpl();
			newContext.setAuthentication(new UsernamePasswordAuthenticationToken(user.getLoginname(), user
					.getPassword()));
			ContextHolder.setContext(newContext);

		} catch (Exception e) {
			e.printStackTrace();
			String err = "User name is not unique";

			ModelAndView mav = new ModelAndView(new RedirectView("editProfile.htm"));
			mav.addObject("err", err);

			return mav;		}

		ModelAndView mav = new ModelAndView("logout");
		return mav;
	}

	protected Map referenceData(HttpServletRequest request) throws java.lang.Exception {
		Map m = new HashMap();
		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		m.put("err", err);
		return m;
	}

	public SystemUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(SystemUserManager userManager) {
		this.userManager = userManager;
	}

}

package agtc.sampletracking.web.controller.admin;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.SystemUserManager;
import agtc.sampletracking.model.User;
import agtc.sampletracking.web.controller.BasicController;
import agtc.sampletracking.web.controller.SampleTypeController;

public class AddEditUserController extends BasicController {
	private SystemUserManager	userManager;
	private List				LRoles;
	private Log					log	= LogFactory.getLog(SampleTypeController.class);

	public AddEditUserController() {
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		User user = null;
		int i = ServletRequestUtils.getIntParameter(request, "userId", -1);

		if (i == -1) {
			user = new User();
			user.setUserId(new Integer(-1));
		} else {
			user = userManager.getUserDAO().getUser(new Integer(i));
		}

		return (Object) user;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		String submit = ServletRequestUtils.getStringParameter(request, "Submit", null);

		User user = (User) command;

		String labelnu = request.getParameter("activity");
		if ((labelnu != null) && (labelnu.equals("on")))
			user.setActivity("on");
		else
			user.setActivity("off");

		if (submit.compareTo("New") == 0) {
			user.setUserId(new Integer(-1));
		}

		try {
			String[] roles = ServletRequestUtils.getRequiredStringParameters(request, "roles");
			userManager.saveUser(user);

			// handle roles
			userManager.saveUserRoles(user, roles);
		} catch (Exception e) {
			String err = "User name not unique";

			ModelAndView mav = new ModelAndView(new RedirectView("adminAddEditUser.htm"));
			mav.addObject("err", err);

			return mav;
		}

		log.debug("success view is " + getSuccessView());

		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	protected Map referenceData(HttpServletRequest request) throws java.lang.Exception {
		// super.referenceData(request);
		Map m = new HashMap();
		LRoles = userManager.getRoles();
		List LUsers = userManager.getUsers();

		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		m.put("err", err);

		m.put("RolesList", LRoles);
		m.put("LUsers", LUsers);
		return m;
	}

	public List getRoles() {
		return LRoles;
	}

	public void setLRoles(List stock) {
		LRoles = stock;
	}

	public SystemUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(SystemUserManager userManager) {
		this.userManager = userManager;
	}

}

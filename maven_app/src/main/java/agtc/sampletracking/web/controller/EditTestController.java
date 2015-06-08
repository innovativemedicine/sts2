/*
 * Created on Nov 3, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.io.File;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.IdListResolver;
import agtc.sampletracking.bus.manager.AGTCManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.Test;

/**
 * @author Gloria Deng
 */
public class EditTestController extends BasicController {
	private TestManager		testManager;
	private ProjectManager	projectManager;
	private IdListResolver	idListResolver;
	private AGTCManager		agtcManager;
	private Log				log	= LogFactory.getLog(EditTestController.class);

	public EditTestController() {
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws ServletException {

		int i = ServletRequestUtils.getIntParameter(request, "testId", -1);
		if (i == -1) {
			Test test = new Test();
			test.setTestId(new Integer(-1));
			int ip = ServletRequestUtils.getRequiredIntParameter(request, "projectId");
			Project project = projectManager.getProject(new Integer(ip));
			test.setProject(project);
			return test;
		} else {
			Test test = testManager.getTest(new Integer(i));
			return test;
		}
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Test test = (Test) command;
		String[] ids = request.getParameterValues("assayIds");
		String assayIdList = "";
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				assayIdList += "," + ids[i] + ",";
			}
		}
		handleUpload(request, test);

		test.setAssayList(assayIdList);
		// delegate the update to the Business layer

		try {
			testManager.saveTest(test);
		} catch (Exception e) {
			String err = "Test Name not unique";

			ModelAndView mav = new ModelAndView(new RedirectView("editTest.htm"));
			mav.addObject("err", err);

			return mav;
		}
		
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message", "Have successfully saved this test !");
		myModel.put("testId", test.getTestId());
		return view;
	}

	private void handleUpload(HttpServletRequest request, Test test) throws Exception {
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
		String protocols = "";

		MultipartFile aFile1 = mrequest.getFile("file1");
		String fileName1 = aFile1.getOriginalFilename();
		if (!fileName1.equals("")) {
			log.debug("the file name is " + fileName1);
			File file1 = new File(contextPath + PROTOCOLPATH + fileName1);
			aFile1.transferTo(file1);
			protocols = fileName1;
		}

		MultipartFile aFile2 = mrequest.getFile("file2");
		String fileName2 = aFile2.getOriginalFilename();
		if (!fileName2.equals("")) {
			log.debug("the file name is " + fileName2);
			File file2 = new File(contextPath + PROTOCOLPATH + fileName2);
			aFile2.transferTo(file2);
			protocols += "," + fileName2;
		}

		MultipartFile aFile3 = mrequest.getFile("file3");
		String fileName3 = aFile3.getOriginalFilename();
		if (!fileName3.equals("")) {
			log.debug("the file name is " + fileName3);
			File file3 = new File(contextPath + PROTOCOLPATH + fileName3);
			aFile3.transferTo(file3);
			protocols += "," + fileName3;

		}

		MultipartFile aFile4 = mrequest.getFile("file4");
		String fileName4 = aFile4.getOriginalFilename();
		if (!fileName4.equals("")) {
			log.debug("the file name is " + fileName4);
			File file4 = new File(contextPath + PROTOCOLPATH + fileName4);
			aFile4.transferTo(file4);
			protocols += "," + fileName4;
		}

		test.setProtocal(protocols);
	}

	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws java.lang.Exception {
		Map models = new HashMap();
		List allInstruments = agtcManager.getInstruments();
		Test test = (Test) command;

		String message = ServletRequestUtils.getStringParameter(request, "message", "");
		String err = ServletRequestUtils.getStringParameter(request, "err", "");
		
		models.put("err", err);
		models.put("message", message);

		String assayNameList = "";
		String assayIdList = test.getAssayList();

		String protocol = test.getProtocal();
		
		if (protocol != null) {
			String[] protocols = null;
			if (protocol.indexOf(",") > 0) {
				protocols = protocol.split(",");
			} else {
				protocols = new String[1];
				protocols[0] = protocol;
			}

			models.put("protocols", protocols);

		}

		if (assayIdList != null && assayIdList.length() > 1) {
			StringTokenizer st = new StringTokenizer(assayIdList, ",");
			while (st.hasMoreTokens()) {
				String id = st.nextToken();
				log.debug("the assay id is " + id);
				String name = testManager.getAssay(Integer.valueOf(id)).getName();
				assayNameList += name + " , ";
			}

		}

		Project project = test.getProject();
		Set allAssays = project.getAssays();

		models.put("allInstruments", allInstruments);
		models.put("allAssays", allAssays);
		models.put("assayNameList", assayNameList);

		return models;
	}


	public TestManager getTestManager() {
		return testManager;
	}

	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	public IdListResolver getIdListResolver() {
		return idListResolver;
	}

	public void setIdListResolver(IdListResolver resolver) {
		idListResolver = resolver;
	}

	public AGTCManager getAgtcManager() {
		return agtcManager;
	}

	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}

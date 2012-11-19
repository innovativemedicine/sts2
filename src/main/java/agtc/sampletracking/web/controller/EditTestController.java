/*
 * Created on Nov 3, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.RequestUtils;
import org.springframework.validation.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.*;
import agtc.sampletracking.bus.*;
import agtc.sampletracking.web.command.*;
import agtc.sampletracking.bus.manager.*;
import java.util.*;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.*;
import org.springframework.web.multipart.*;
import java.io.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EditTestController extends BasicController {
	private TestManager testManager;
	private ProjectManager projectManager;
	private IdListResolver idListResolver;
	private AGTCManager agtcManager;
	private Log log = LogFactory.getLog(EditTestController.class);
	
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public EditTestController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
			
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getIntParameter(request, "testId",-1);
		if(i==-1){
			Test test = new Test();
			test.setTestId(new Integer(-1));
			int ip = RequestUtils.getRequiredIntParameter(request, "projectId");
			Project project = projectManager.getProject(new Integer(ip));
			test.setProject(project);
			return test;
		}else{
			Test test = testManager.getTest(new Integer(i));
			return test;
		}
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
								HttpServletResponse response,
								Object command,
								BindException errors)
						 throws Exception{
		Test test = (Test) command;
		String[] ids = request.getParameterValues("assayIds");
		String assayIdList = "";
		if (ids != null) { 
			for (int i = 0; i < ids.length; i++) { 
				assayIdList +=","+ids[i]+","; 
			} 
		}
		handleUpload(request,test);
		
		test.setAssayList(assayIdList);
		// delegate the update to the Business layer
		
		try{
			testManager.saveTest(test);
		}catch(Exception e){
			errors.rejectValue( "name","error.notUnique",new String[]{test.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		log.debug("success view is " + getSuccessView());
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this test !");
		myModel.put("testId",test.getTestId());
		return view;
	}
	
	private void handleUpload (HttpServletRequest request,Test test) throws Exception {
		MultipartHttpServletRequest  mrequest = (MultipartHttpServletRequest)request;
		String protocalS = "";
		
		MultipartFile aFile1 = mrequest.getFile("file1");
		String fileName1 = aFile1.getOriginalFilename();
		if(! fileName1.equals("")){
			log.debug("the file name is " + fileName1);
			File file1 = new File("..\\webapps\\SampleTracking\\protocals\\"+fileName1);
			aFile1.transferTo(file1);
			protocalS = fileName1;
		}
		
		MultipartFile aFile2 = mrequest.getFile("file2");
		String fileName2 = aFile2.getOriginalFilename();
		if(! fileName2.equals("")){
			log.debug("the file name is " + fileName2);
			File file2 = new File("..\\webapps\\SampleTracking\\protocals\\"+fileName2);
			aFile2.transferTo(file2);
			protocalS += "," + fileName2;
		}
		
		MultipartFile aFile3 = mrequest.getFile("file3");
		String fileName3 = aFile3.getOriginalFilename();
		if(! fileName3.equals("")){
			log.debug("the file name is " + fileName3);
			File file3 = new File("..\\webapps\\SampleTracking\\protocals\\"+fileName3);
			aFile3.transferTo(file3);
			protocalS += "," + fileName3;
			
			
		}
		
		MultipartFile aFile4 = mrequest.getFile("file4");
		String fileName4 = aFile4.getOriginalFilename();
		if(! fileName4.equals("")){
			log.debug("the file name is " + fileName4);
			File file4 = new File("..\\webapps\\SampleTracking\\protocals\\"+fileName4);
			aFile4.transferTo(file4);
			protocalS += "," + fileName4;
		}
		
		test.setProtocal(protocalS);
	}
		
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
									  java.lang.Object command,Errors errors)
							   throws java.lang.Exception
	{
		Map models = new HashMap();
		List allInstruments = agtcManager.getInstruments();
		Test test = (Test) command;
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		String assayNameList = "";
		String assayIdList = test.getAssayList();
		
		String protocal = test.getProtocal();
		log.debug("the protocal is " + protocal);
		if(protocal != null){
			String[] protocals = null;
			if(protocal.indexOf(",")>0){
				protocals = protocal.split(",");
			}else{
				protocals = new String[1];
				protocals[0] = protocal;
			}
			
			log.debug("the length of protocal is " + protocals.length);
			models.put("protocals",protocals);
			
		}
		
		
		if(assayIdList != null && assayIdList.length() >1 ){
			StringTokenizer st = new StringTokenizer(assayIdList,",");
			 while (st.hasMoreTokens()) {
			 	String id = st.nextToken();
			 	log.debug("the assay id is "+ id);
				 String name = testManager.getAssay(Integer.valueOf(id)).getName();
				 assayNameList += name +" , ";
			 }
		
		}
		
		
		
		Project project = test.getProject();
		Set allAssays = project.getAssays();
		
		models.put("allInstruments",allInstruments);
		models.put("allAssays",allAssays);
		models.put("assayNameList",assayNameList);
		
		
	
		log.debug("referenceData is called");
		return models;
	}
		
	
	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
												 javax.servlet.http.HttpServletResponse response,
												 java.lang.Object command,
												 BindException errors)
										  throws java.lang.Exception
	{
		Test test = (Test) command;
		log.debug(test);
		log.debug(errors);
		return null;
	}
	*/
	/**
	 * @return
	 */
	public TestManager getTestManager() {
		return testManager;
	}

	/**
	 * @param manager
	 */
	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

	/**
	 * @return
	 */
	public ProjectManager getProjectManager() {
		return projectManager;
	}

	/**
	 * @param manager
	 */
	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	/**
	 * @return
	 */
	public IdListResolver getIdListResolver() {
		return idListResolver;
	}

	/**
	 * @param resolver
	 */
	public void setIdListResolver(IdListResolver resolver) {
		idListResolver = resolver;
	}
	
	


	/**
	 * @return Returns the agtcManager.
	 */
	public AGTCManager getAgtcManager() {
		return agtcManager;
	}
	/**
	 * @param agtcManager The agtcManager to set.
	 */
	public void setAgtcManager(AGTCManager agtcManager) {
		this.agtcManager = agtcManager;
	}
}

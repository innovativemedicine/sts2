/*
 * Created on Oct 29, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.sampletracking.model.*;
import agtc.sampletracking.bus.comparator.*;
import agtc.sampletracking.bus.manager.*;
import agtc.sampletracking.bus.*;

import java.util.*;


/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StsController extends MultiActionController implements InitializingBean {
	private ProjectManager projectManager;
//	private DbManager dbManager;
	private TestManager testManager;
	private SampleManager sampleManager;
	private ContainerManager containerManager;
	private IdListResolver idListResolver;
	private Log log = LogFactory.getLog(StsController.class);
	
	public void afterPropertiesSet() throws Exception {
		if (projectManager == null )
			throw new ApplicationContextException("Must set managers bean property on " + getClass());
	}
	
	public ModelAndView projectDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Project project = projectManager.getProject(
				new Integer(RequestUtils.getIntParameter(request, "projectId", -1)));
		if (project == null) {
			return new ModelAndView(new RedirectView("projects.htm"));
		}
		
		Map models = new HashMap();
		models.put("command",project);
		if(project.getTests()!= null){
			Set testSet = project.getTests();
			List testList = Collections.list(Collections.enumeration(testSet));
			Collections.sort(testList,new TestComparator());
			models.put("testList",testList);
		}
		if(project.getContainers() != null){
			Set containerSet = project.getContainers();
			List containerList = Collections.list(Collections.enumeration(containerSet));
			Collections.sort(containerList,new ContainerComparator());
			models.put("containerList",containerList);
		}
		if(project.getProjectId() != null){
			List assayList = projectManager.getAssayByProjectId(project.getProjectId());
			//Collections.sort(assayList,new AssayComparator());
			models.put("assayList",assayList);
		}
		if(project.getRuns() != null){
			Set runSet = project.getRuns();
			List runList = Collections.list(Collections.enumeration(runSet));
			Collections.sort(runList,new RunComparator());
			models.put("runList",runList);
		}
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		return new ModelAndView("projectDetails", models);
	}
	
	public ModelAndView resultDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Result result = testManager.getResult(
				new Integer(RequestUtils.getIntParameter(request, "resultId", -1)));
		Run run = result.getRun();
		run.setTestNameList(idListResolver.resolveTestIdList(run.getTestList()));
		run.setPlateNameList(idListResolver.resolvePlateIdList(run.getPlateList()));
		Map models = new HashMap();
		models.put("result",result);
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		return new ModelAndView("resultDetails", models);
	}
	
	public ModelAndView assayDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Assay assay = testManager.getAssay(
				new Integer(RequestUtils.getIntParameter(request, "assayId", -1)));
		if (assay == null) {
			return new ModelAndView(new RedirectView("projects.htm"));
		}
		Map models = new HashMap();
		models.put("command",assay);
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		return new ModelAndView("assayDetails", models);
	}
	
	public ModelAndView reagentDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Reagent reagent = sampleManager.getReagent(
				new Integer(RequestUtils.getIntParameter(request, "reagentId", -1)));
	
		Map models = new HashMap();
		models.put("command",reagent);
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		return new ModelAndView("reagentDetails", models);
	}
	
	public ModelAndView runDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Run run = testManager.getRun(
				new Integer(RequestUtils.getIntParameter(request, "runId", -1)));
		String linkageFormat = RequestUtils.getStringParameter(request, "linkageFormat","");
		run.setTestNameList(idListResolver.resolveTestIdList(run.getTestList()));
		run.setPlateNameList(idListResolver.resolvePlateIdList(run.getPlateList()));
		List uniAssayList = testManager.getAssayByRunId(run.getRunId());
		Map models = new HashMap();
		models.put("command",run);
		models.put("uniAssayList",uniAssayList);
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		return new ModelAndView("runDetails", models);
	}
	
	public ModelAndView resultListHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int runId = RequestUtils.getIntParameter(request, "runId", -1);
		String linkageFormat = RequestUtils.getStringParameter(request, "linkageFormat","");
		Map models = new HashMap();
		
		List results = testManager.searchResultByRunId(runId);
		if(results!=null && results.size()>0){
			ResultWorker rw = new ResultWorker();
			if(linkageFormat.equals("yes")){
				rw.setLinkageFormat(true);
			}
			Object[][] rs=rw.formatResult(results);
			models.put("resultList",rs);
		}
		return new ModelAndView("resultList", models);
	}
	
	public ModelAndView testDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Test test = testManager.getTest(
				new Integer(RequestUtils.getIntParameter(request, "testId", -1)));
		if (test == null) {
			return new ModelAndView(new RedirectView("projects.htm"));
		}
		
		Map models = new HashMap();
		
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
		
		String assayIdList = test.getAssayList();
		String assayNameList=idListResolver.resolveAssayIdList(assayIdList);
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		

		models.put("command",test);
		models.put("assayNameList",assayNameList);
	
		return new ModelAndView("testDetails", models);
	}
	
	public ModelAndView sampleDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Sample sample = sampleManager.getSample(
				new Integer(RequestUtils.getIntParameter(request, "sampleId", -1)));
		String linkageFormat = RequestUtils.getStringParameter(request, "linkageFormat","");
		if (sample == null) {
			return new ModelAndView(new RedirectView("containers.htm"));
		}
		//Set samplesInContainers = sample.getSamplesInContainers();
		//log.debug("samplesInContainer size is " + samplesInContainers.size());
		Map models = new HashMap();
		models.put("command",sample);
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		return new ModelAndView("sampleDetails", models);
	}
	
	public ModelAndView patientDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Patient patient = sampleManager.getPatient(RequestUtils.getStringParameter(request, "intSampleId", ""));
		
		Map models = new HashMap();
		models.put("command",patient);
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		
		return new ModelAndView("patientDetails", models);
	}
	
	public ModelAndView sampleIdListHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Container container = containerManager.getContainer(
				new Integer(RequestUtils.getIntParameter(request, "containerId", -1)));
		if (container == null) {
			return new ModelAndView(new RedirectView("containers.htm"));
		}
		Set allSamplesInContainer = container.getSamplesInContainers();
		ContainerType currentContainerType = container.getContainerType();
		Sample[][] orderedSamples = null;
		if(allSamplesInContainer!=null){
			//log.debug("the length of List is " + allSamplesInContainer.size());
			PlateWorker plateWorker = new PlateWorker(currentContainerType);
			plateWorker.formatContainer(allSamplesInContainer);
			orderedSamples = plateWorker.getOrderedSamples();
		}

		return new ModelAndView("sampleIdList", "orderedSamples",orderedSamples);
	}
	
	public ModelAndView sampleIdMapHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Container container = containerManager.getContainer(
				new Integer(RequestUtils.getIntParameter(request, "containerId", -1)));
		if (container == null) {
			return new ModelAndView(new RedirectView("containers.htm"));
		}
		Set allSamplesInContainer = container.getSamplesInContainers();
		ContainerType currentContainerType = container.getContainerType();
		Sample[][] orderedSamples = null;
		if(allSamplesInContainer!=null){
			//log.debug("the length of List is " + allSamplesInContainer.size());
			PlateWorker plateWorker = new PlateWorker(currentContainerType);
			plateWorker.formatContainer(allSamplesInContainer);
			orderedSamples = plateWorker.getOrderedSamples();
		}

		return new ModelAndView("sampleIdMap", "orderedSamples",orderedSamples);
	}
	
	public ModelAndView containerDetailsHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		Container container = containerManager.getContainer(
				new Integer(RequestUtils.getIntParameter(request, "containerId", -1)));
		if (container == null) {
			return new ModelAndView(new RedirectView("containers.htm"));
		}
		Set allSamplesInContainer = container.getSamplesInContainers();
		ContainerType currentContainerType = container.getContainerType();
		Map models = new HashMap();
		if(allSamplesInContainer!=null && allSamplesInContainer.size()>0){
			//log.debug("the length of List is " + allSamplesInContainer.size());
			PlateWorker plateWorker = new PlateWorker(currentContainerType);
			plateWorker.formatContainer(allSamplesInContainer);
			Sample[][] orderedSamples = plateWorker.getOrderedSamples();
			List unOrderedSamples = plateWorker.getUnOrderedSamples();
			// it is a rule that we put either ordered sample or unordered sample in container, 
			if(unOrderedSamples.size()==0){
				container.setOrderedSample(true);
				models.put("orderedSamples",orderedSamples);
			}else{
				container.setUnorderedSample(true);
				models.put("unOrderedSamples",unOrderedSamples);
			}
		}else{
			container.setNoneSample(true);
		}
		models.put("command", container);
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		return new ModelAndView("containerDetails", models);
	}
	
	
	public ModelAndView successLoginHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		return new ModelAndView("success", "message", "You have successfully logged in !");
	}
	
	public ModelAndView deleteTestHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int testId = RequestUtils.getRequiredIntParameter(request, "testId");
		testManager.removeTest(new Integer(testId));
		return new ModelAndView("successDelete","entityName","test");
	}
	
	public ModelAndView deleteRunHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int runId = RequestUtils.getRequiredIntParameter(request, "runId");
		testManager.removeRun(new Integer(runId));
		return new ModelAndView("successDelete","entityName","run and its results");
	}
	
	public ModelAndView deleteAssayHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int assayId = RequestUtils.getRequiredIntParameter(request, "assayId");
		Assay assay = testManager.getAssay(new Integer(assayId));
		assay.getProjects().clear();
		testManager.removeAssay(new Integer(assayId));
		return new ModelAndView("successDelete","entityName","assay");
	}

	//Delete all results of select assay(s) from a RUN
	//Jianan Xiao 2006-03-01
	public ModelAndView deleteRunAssayHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int runId = RequestUtils.getRequiredIntParameter(request, "runId");
		String[] assays = request.getParameterValues("assay");
		
		testManager.removeResultByAssay(new Integer(runId),assays);
		return new ModelAndView("successDelete","entityName","assay(s)");
	}

	public ModelAndView deleteResultHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int resultId = RequestUtils.getRequiredIntParameter(request, "resultId");
		
		testManager.removeResult(new Integer(resultId));
		return new ModelAndView("successDelete","entityName","result");
		
	}
	
	public ModelAndView deleteReagentHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int id = RequestUtils.getRequiredIntParameter(request, "reagentId");
		
		sampleManager.removeReagent(new Integer(id));
		return new ModelAndView("successDelete","entityName","reagent");
	}
	
	public ModelAndView deleteSampleHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int id = RequestUtils.getRequiredIntParameter(request, "sampleId");
		Sample sample = sampleManager.getSample(new Integer(id));
		sampleManager.removeSample(new Integer(id));
		return new ModelAndView("successDelete","entityName","sample");
	}
	/* here really delete the sample,  
	public ModelAndView deleteSampleFromContainerHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = RequestUtils.getRequiredIntParameter(request, "sicId");
		int containerId = RequestUtils.getIntParameter(request, "containerId",-1);
		SamplesInContainer sic = sampleManager.getSamplesInContainer(new Integer(id));
		
		Sample sample = sic.getSample();
		Set sics = sample.getSamplesInContainers();
		sics.remove(sic);
		sampleManager.removeSamplesInContainer(new Integer(id));
		sample.setSamplesInContainers(sics);
		sampleManager.saveSample(sample);
		String message = "";
		
		if(containerId == -1){ // came from sampleDetails Page
			Map models = new HashMap();
			models.put("message",message);
			models.put("command", sample);
			return new ModelAndView("sampleDetails", models);
		}else{ //came from containerContent page
			ModelAndView view = new ModelAndView(new RedirectView("containerContents.htm"));
			Map myModel = view.getModel();
			myModel.put("message",message);
			myModel.put("containerId",new Integer(containerId));
			return view;
		}
	}
	*/
	
	
	public ModelAndView deleteAllSamplesInContainerHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int id = RequestUtils.getRequiredIntParameter(request, "containerId");
		Container container = containerManager.getContainer(new Integer(id));
		sampleManager.removeAllSamplesInContainer(container);
	
		return new ModelAndView("successDelete","entityName","all samples in this container");
	}
	
	public ModelAndView deleteProjectHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int id = RequestUtils.getRequiredIntParameter(request, "projectId");
		try{
			projectManager.removeProject(new Integer(id));
		}catch(Exception e){
			log.error(e);
			return new ModelAndView("errorPage","message","Could not remove this project from database !");
		}
		return new ModelAndView("successDelete","entityName","project");
	}
	
	public ModelAndView deleteContainerHandler(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException {
		int id = RequestUtils.getRequiredIntParameter(request, "containerId");
		try{
			containerManager.removeContainer(new Integer(id));
		}catch(Exception e){
			log.error(e.toString());
			return new ModelAndView("errorPage","message","Could not remove this container from database !");
		}
		return new ModelAndView("successDelete","entityName","container");
	}
	
	
	
	public ModelAndView emptyContainerHandler(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		int id = RequestUtils.getRequiredIntParameter(request, "containerId");
		Container container = containerManager.getContainer(new Integer(id));
		Set sics = container.getSamplesInContainers();
		if(sics!=null){
			sics.clear();
		}
		sampleManager.removeSamplesInContainerByContainer(new Integer(id));
		container.setSamplesInContainers(sics);
		containerManager.saveContainer(container);
		Map models = new HashMap();
		
		models.put("command", container);
		models.put("message","You have successfully removed all samples from this container !");
		
		return new ModelAndView("containerDetails", models);
	}
	
	public ModelAndView shuffleSamplesInContainerHandler(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		int id = RequestUtils.getRequiredIntParameter(request, "containerId");
		Container container = containerManager.getContainer(new Integer(id));
		Set sics = container.getSamplesInContainers();
		Iterator i = sics.iterator();
		//String location = "";
		while ( i.hasNext()){
			SamplesInContainer sic = (SamplesInContainer)i.next();
			sic.setWell("");
		}
		container.setSamplesInContainers(sics);
		containerManager.saveContainer(container);
		
		Map myModel = new HashMap();
		
		
		myModel.put("command", container);
		myModel.put("message","You have successfully shuffled samples in this container !");
		
		return new ModelAndView("containerDetails", myModel);
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
	public SampleManager getSampleManager() {
		return sampleManager;
	}

	/**
	 * @param manager
	 */
	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	/**
	 * @return
	 */
	public ContainerManager getContainerManager() {
		return containerManager;
	}

	/**
	 * @param manager
	 */
	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
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

}

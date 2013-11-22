/*
 * Created on Oct 29, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.ConstantInterface;
import agtc.sampletracking.bus.IdListResolver;
import agtc.sampletracking.bus.PlateWorker;
import agtc.sampletracking.bus.ResultWorker;
import agtc.sampletracking.bus.comparator.ContainerComparator;
import agtc.sampletracking.bus.comparator.RunComparator;
import agtc.sampletracking.bus.comparator.TestComparator;
import agtc.sampletracking.bus.manager.ContainerManager;
import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.bus.manager.TestManager;
import agtc.sampletracking.model.Assay;
import agtc.sampletracking.model.Container;
import agtc.sampletracking.model.ContainerType;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.Result;
import agtc.sampletracking.model.Run;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.Test;

/**
 * @author Gloria Deng
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StsController extends MultiActionController implements InitializingBean, ConstantInterface {
	private ProjectManager		projectManager;
	// private DbManager dbManager;
	private TestManager			testManager;
	private SampleManager		sampleManager;
	private ContainerManager	containerManager;
	private IdListResolver		idListResolver;
	private Log					log	= LogFactory.getLog(StsController.class);

	public void afterPropertiesSet() throws Exception {
		if (projectManager == null)
			throw new ApplicationContextException("Must set managers bean property on " + getClass());
	}

	public ModelAndView projectDetailsHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Project project = projectManager.getProject(new Integer(ServletRequestUtils.getIntParameter(request,
				"projectId", -1)));
		if (project == null) {
			return new ModelAndView(new RedirectView("projects.htm"));
		}

		Map models = new HashMap();
		models.put("command", project);
		if (project.getTests() != null) {
			Set testSet = project.getTests();
			List testList = Collections.list(Collections.enumeration(testSet));
			Collections.sort(testList, new TestComparator());
			models.put("testList", testList);
		}
		if (project.getContainers() != null) {
			Set containerSet = project.getContainers();
			List containerList = Collections.list(Collections.enumeration(containerSet));
			Collections.sort(containerList, new ContainerComparator());
			models.put("containerList", containerList);
		}
		if (project.getProjectId() != null) {
			List assayList = projectManager.getAssayByProjectId(project.getProjectId());
			// Collections.sort(assayList,new AssayComparator());
			models.put("assayList", assayList);
		}
		if (project.getRuns() != null) {
			Set runSet = project.getRuns();
			List runList = Collections.list(Collections.enumeration(runSet));
			Collections.sort(runList, new RunComparator());
			models.put("runList", runList);
		}

		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		return new ModelAndView("projectDetails", models);
	}

	public ModelAndView resultDetailsHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Result result = testManager
				.getResult(new Integer(ServletRequestUtils.getIntParameter(request, "resultId", -1)));
		Run run = result.getRun();
		run.setTestNameList(idListResolver.resolveTestIdList(run.getTestList()));
		run.setPlateNameList(idListResolver.resolvePlateIdList(run.getPlateList()));
		Map models = new HashMap();
		models.put("result", result);
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		return new ModelAndView("resultDetails", models);
	}

	public ModelAndView assayDetailsHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Assay assay = testManager.getAssay(new Integer(ServletRequestUtils.getIntParameter(request, "assayId", -1)));
		if (assay == null) {
			return new ModelAndView(new RedirectView("projects.htm"));
		}
		Map models = new HashMap();
		models.put("command", assay);
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		return new ModelAndView("assayDetails", models);
	}

	public ModelAndView runDetailsHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Run run = testManager.getRun(new Integer(ServletRequestUtils.getIntParameter(request, "runId", -1)));
		String linkageFormat = ServletRequestUtils.getStringParameter(request, "linkageFormat", "");
		run.setTestNameList(idListResolver.resolveTestIdList(run.getTestList()));
		run.setPlateNameList(idListResolver.resolvePlateIdList(run.getPlateList()));
		List uniAssayList = testManager.getAssayByRunId(run.getRunId());
		Map models = new HashMap();
		models.put("command", run);
		models.put("uniAssayList", uniAssayList);

		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		return new ModelAndView("runDetails", models);
	}

	public ModelAndView resultListHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int runId = ServletRequestUtils.getIntParameter(request, "runId", -1);
		String linkageFormat = ServletRequestUtils.getStringParameter(request, "linkageFormat", "");
		Map models = new HashMap();

		List results = testManager.searchResultByRunId(runId);
		if (results != null && results.size() > 0) {
			ResultWorker rw = new ResultWorker();
			if (linkageFormat.equals("yes")) {
				rw.setLinkageFormat(true);
			}
			Object[][] rs = rw.formatResult(results);
			models.put("resultList", rs);
		}
		return new ModelAndView("resultList", models);
	}

	public ModelAndView testDetailsHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Test test = testManager.getTest(new Integer(ServletRequestUtils.getIntParameter(request, "testId", -1)));
		if (test == null) {
			return new ModelAndView(new RedirectView("projects.htm"));
		}

		Map models = new HashMap();

		String protocal = test.getProtocal();
		log.debug("the protocal is " + protocal);
		if (protocal != null) {
			String[] protocals = null;
			if (protocal.indexOf(",") > 0) {
				protocals = protocal.split(",");
			} else {
				protocals = new String[1];
				protocals[0] = protocal;
			}

			log.debug("the length of protocal is " + protocals.length);
			models.put("protocals", protocals);

		}

		String assayIdList = test.getAssayList();
		String assayNameList = idListResolver.resolveAssayIdList(assayIdList);
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		models.put("command", test);
		models.put("assayNameList", assayNameList);

		return new ModelAndView("testDetails", models);
	}

	public ModelAndView containerDetailsHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Container container = containerManager.getContainer(new Integer(ServletRequestUtils.getIntParameter(request,
				"containerId", -1)));
		if (container == null) {
			return new ModelAndView(new RedirectView("containers.htm"));
		}
		Set allSamplesInContainer = container.getSamplesInContainers();
		ContainerType currentContainerType = container.getContainerType();
		Map models = new HashMap();
		if (allSamplesInContainer != null && allSamplesInContainer.size() > 0) {

			PlateWorker plateWorker = new PlateWorker(currentContainerType);
			plateWorker.formatContainer(allSamplesInContainer);
			Sample[][] orderedSamples = plateWorker.getOrderedSamples();
			List unOrderedSamples = plateWorker.getUnOrderedSamples();
			// it is a rule that we put either ordered sample or unordered
			// sample in container,
			if (unOrderedSamples.size() == 0) {
				container.setOrderedSample(true);
				models.put("orderedSamples", orderedSamples);
			} else {
				container.setUnorderedSample(true);
				models.put("unOrderedSamples", unOrderedSamples);
			}
		} else {
			container.setNoneSample(true);
		}
		if (container.isHasChildContainer()) {
			models.put("childContainers", container.getChildContainers());
		}
		models.put("command", container);
		String message = ServletRequestUtils.getStringParameter(request, "message", "");

		models.put("message", message);

		return new ModelAndView("containerDetails", models);
	}

	public ModelAndView successLoginHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		return new ModelAndView("success", "message", "You have successfully logged in !");
	}

	public ModelAndView deleteTestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int testId = ServletRequestUtils.getRequiredIntParameter(request, "testId");
		testManager.removeTest(new Integer(testId));
		return new ModelAndView("login", "message", "Test deleted");
	}

	public ModelAndView deleteRunHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int runId = ServletRequestUtils.getRequiredIntParameter(request, "runId");
		testManager.removeRun(new Integer(runId));
		return new ModelAndView("login", "message", "Run and its results deleted");
	}

	public ModelAndView deleteAssayHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int assayId = ServletRequestUtils.getRequiredIntParameter(request, "assayId");
		Assay assay = testManager.getAssay(new Integer(assayId));
		assay.getProjects().clear();
		testManager.removeAssay(new Integer(assayId));
		return new ModelAndView("login", "message", "Assay Deleted");
	}

	// Delete all results of select assay(s) from a RUN
	// Jianan Xiao 2006-03-01
	public ModelAndView deleteRunAssayHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int runId = ServletRequestUtils.getRequiredIntParameter(request, "runId");
		String[] assays = request.getParameterValues("assay");

		testManager.removeResultByAssay(new Integer(runId), assays);
		return new ModelAndView("login", "message", "Assay(s) deleted");
	}

	public ModelAndView deleteResultHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int resultId = ServletRequestUtils.getRequiredIntParameter(request, "resultId");

		testManager.removeResult(new Integer(resultId));
		return new ModelAndView("login", "message", "Result deleted");

	}

	public ModelAndView deleteSampleHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "sampleId");
		Sample sample = sampleManager.getSample(new Integer(id));
		String intSampleId = sample.getPatient().getIntSampleId();
		String sampleType = sample.getSampleType().getSuffix();
		Integer sampleDup = sample.getSampleDupNo();

		sampleManager.removeSample(new Integer(id));

		ModelAndView mav = new ModelAndView(new RedirectView("sampleDetails.htm"));
		mav.addObject("message", "Sample " + intSampleId + ":" + sampleType + "-" + sampleDup + " deleted");
		mav.addObject("intSampleId", intSampleId);

		return mav;

	}

	public ModelAndView deleteAllSamplesInContainerHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "containerId");
		Container container = containerManager.getContainer(new Integer(id));

		sampleManager.removeAllSamplesInContainer(container);

		return new ModelAndView("login", "message", "All samples in this container deleted");
	}

	public ModelAndView deleteProjectHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "projectId");
		try {
			projectManager.removeProject(new Integer(id));
		} catch (Exception e) {
			log.error(e);
			return new ModelAndView("errorPage", "message", "Could not remove this project from database !");
		}
		return new ModelAndView("login", "message", "Project Deleted");
	}

	public ModelAndView deleteContainerHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "containerId");
		Container container = containerManager.getContainer(id);

		try {
			// If it is a plate, remove all container along with it.
			if (container.getName().contains(PLATE_PREFIX)) {
				sampleManager.removeAllSamplesAndPatientsInContainer(container);
			}

			containerManager.removeContainer(id);

		} catch (Exception e) {
			log.error(e.toString());
			return new ModelAndView("errorPage", "message", "Could not remove this container from database !");
		}
		return new ModelAndView("login", "message", "Container Deleted");
	}

	public ModelAndView emptyContainerHandler(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "containerId");
		Container container = containerManager.getContainer(new Integer(id));
		Set sics = container.getSamplesInContainers();
		if (sics != null) {
			sics.clear();
		}
		sampleManager.removeSamplesInContainerByContainer(new Integer(id));
		container.setSamplesInContainers(sics);
		containerManager.saveContainer(container);
		Map models = new HashMap();

		models.put("command", container);
		models.put("message", "You have successfully removed all samples from this container !");

		return new ModelAndView("containerDetails", models);
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	public TestManager getTestManager() {
		return testManager;
	}

	public void setTestManager(TestManager manager) {
		testManager = manager;
	}

	public SampleManager getSampleManager() {
		return sampleManager;
	}

	public void setSampleManager(SampleManager manager) {
		sampleManager = manager;
	}

	public ContainerManager getContainerManager() {
		return containerManager;
	}

	public void setContainerManager(ContainerManager manager) {
		containerManager = manager;
	}

	public IdListResolver getIdListResolver() {
		return idListResolver;
	}

	public void setIdListResolver(IdListResolver resolver) {
		idListResolver = resolver;
	}

}

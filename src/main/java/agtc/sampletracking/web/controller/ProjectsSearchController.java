/*
 * Created on Oct 18, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import agtc.sampletracking.bus.manager.ProjectManager;
import agtc.sampletracking.model.Project;

/**
 * @author Gloria Deng
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ProjectsSearchController extends BasicSearchController {
	private ProjectManager	projectManager;
	private Log				log	= LogFactory.getLog(ProjectsSearchController.class);

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager manager) {
		projectManager = manager;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		
		System.out.println("WEEWEE1");

//		List searchResults = handleSubmit(request, command, "project", errors);

		List searchResults = new ArrayList();
		
		if (	searchResults.size() < 1) {
			System.out.println("WEEWEE2");
			
			ModelAndView view = new ModelAndView(new RedirectView("projects.htm"));
			putMessage(request, view.getModel());
			return view;
		}

		if (searchResults.size() > 1) {
			// multiple projects found
			return new ModelAndView("projectList", "projectList", searchResults);
		}

		System.out.println("ERRRROR");
		
		Project project = (Project) searchResults.iterator().next();

		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("projectId", project.getProjectId());
		return view;
	}

	protected List performSearch(List crtList, List lgcList) {
		return projectManager.searchProject(crtList, lgcList);
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map models = new HashMap();
		referenceData(request, models, "project");
		List projectList = projectManager.getAllProjects();

		models.put("projectList", projectList);
		return models;
	}

}

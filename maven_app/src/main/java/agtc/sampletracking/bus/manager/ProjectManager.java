/*
 * Created on Oct 21, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.bus.manager;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import java.util.*;

/**
 * @author Gloria Deng
 *
 * This is the middle class between web object and DAO object. It is also the target class that spring framework provide 
 * transaction management.
 */
public class ProjectManager {
	//private Log log = LogFactory.getLog(ProjectManager.class);
	private ProjectDAO projectDAO;

	private List allProjects;
	private boolean refresh = false;
	
	
	public Project getProject(String projectName){
		return projectDAO.getProject(projectName);
	}
	
	public Project getProject(Integer projectId){
		return projectDAO.getProject(projectId);
	}
	
	public List getAssayByProjectId(Integer projectId){
		return projectDAO.getAssayByProjectId(projectId);
	}
	
	/**
	 * @return Returns the allProjects.
	 */
	public List getAllProjects() {
		if(allProjects == null || refresh == true){
			allProjects = projectDAO.getProjects();
			refresh = false;
		}
		return allProjects;
	}
	/**
	 * 
	 *see comments of ProjectSearchFields
	 */
	public List searchProject(List crtList,List lgcList){
		return projectDAO.getProjects(crtList,lgcList);
	
	}
	
	public void removeProject(Integer projectId){
		projectDAO.removeProject(projectId);
	}
	
	public void saveProject(Project project) throws Exception {
		projectDAO.saveProject(project);
		refresh = true;
	}

	
	/**
	 * @return
	 */
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}


	/**
	 * @param projectDAO
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	

}

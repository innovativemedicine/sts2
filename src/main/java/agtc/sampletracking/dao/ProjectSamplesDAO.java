/*
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao;

import java.util.*;

import agtc.sampletracking.model.*;
/**
 * @author Bei Jin
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

public interface ProjectSamplesDAO {
	public List getProjects();
	public Project getProject(Integer projectId);
	public Project getProject(String projectName);
	public List getProjects(List crtList,List lgcList);
	public void saveProject(Project project) throws Exception;
	public void removeProject(Integer projectId); 
	public List getAssayByProjectId(Integer projectId);
}

/*
 * Created on Nov 25, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.editor;

import java.beans.PropertyEditorSupport;
import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ProjectEditor extends PropertyEditorSupport {
	private ProjectDAO projectDAO;
	public void setAsText(String projectId) throws IllegalArgumentException { 
		 
		 // text is the string from the form, the projectId is the identifier in 
		 // the database 
    
		 Project project=null; 
		  if(projectId!=null&&!projectId.equals("")){ 
			project=projectDAO.getProject(new Integer(projectId));       
		  }      
		  setValue(project); 
     
	   } 

	   public String getAsText(Object value) { 
	   //return the id value of the object 
    
		   Project project = (Project) value; 
		   return String.valueOf(project.getProjectId()); 
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

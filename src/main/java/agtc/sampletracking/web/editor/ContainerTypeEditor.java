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
public class ContainerTypeEditor extends PropertyEditorSupport {
	private ContainerTypeDAO containerTypeDAO;
	public void setAsText(String containerTypeId) throws IllegalArgumentException { 
	 
		 // text is the string from the form, the containerTypeId is the identifier in 
		 // the database 

		 ContainerType containerType=null; 
		  if(containerTypeId!=null&&!containerTypeId.equals("")){ 
			containerType=containerTypeDAO.getContainerType(new Integer(containerTypeId));       
		  }      
		  setValue(containerType); 
 
	   } 

	   public String getAsText(Object value) { 
	   //return the id value of the object 

		   ContainerType containerType = (ContainerType) value; 
		   return String.valueOf(containerType.getContainerTypeId()); 
	   }
	/**
	 * @return
	 */
	public ContainerTypeDAO getContainerTypeDAO() {
		return containerTypeDAO;
	}

	/**
	 * @param typeDAO
	 */
	public void setContainerTypeDAO(ContainerTypeDAO typeDAO) {
		containerTypeDAO = typeDAO;
	}

}

/*
 * Created on Dec 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.sampletracking.dao.*;
import agtc.sampletracking.model.*;
import agtc.sampletracking.web.controller.StsController;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ContainerEditor extends PropertyEditorSupport {
	private ContainerDAO containerDAO;
	private Log log = LogFactory.getLog(ContainerEditor.class);
	public void setAsText(String containerId) throws IllegalArgumentException { 
		log.debug("the containerId is " + containerId);
		 // text is the string from the form, the containerTypeId is the identifier in 
		 // the database 

		Container container=null; 
		if(containerId!=null&&!containerId.equals("")){ 
				container=containerDAO.getContainer(new Integer(containerId));       
		}      
		setValue(container); 
 
    } 

   public String getAsText(Object value) { 
	   //return the id value of the object 

	   Container container = (Container) value; 
	   return String.valueOf(container.getContainerId()); 
   }
	/**
	 * @return
	 */
	public ContainerDAO getContainerDAO() {
		return containerDAO;
	}

	/**
	 * @param containerDAO
	 */
	public void setContainerDAO(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}

}

/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.editor;

import java.beans.PropertyEditorSupport;
import agtc.sampletracking.model.*;
import agtc.sampletracking.dao.*;


/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssayEditor extends PropertyEditorSupport {
	private AssayDAO assayDAO;
	public void setAsText(String assayId) throws IllegalArgumentException { 
	 
		 // text is the string from the form, the containerTypeId is the identifier in 
		 // the database 

		 Assay assay=null; 
		  if(assayId!=null&&!assayId.equals("")){ 
		  	assay=assayDAO.getAssay(new Integer(assayId));       
		  }      
		  setValue(assay); 
 
	   } 

	   public String getAsText(Object value) { 
	   //return the id value of the object 

		   Assay assay = (Assay) value; 
		   return String.valueOf(assay.getAssayId()); 
	   }
	/**
	 * @return Returns the assayDAO.
	 */
	public AssayDAO getAssayDAO() {
		return assayDAO;
	}
	/**
	 * @param assayDAO The assayDAO to set.
	 */
	public void setAssayDAO(AssayDAO assayDAO) {
		this.assayDAO = assayDAO;
	}
}

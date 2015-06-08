/*
 * Created on Apr 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.editor;

import java.beans.PropertyEditorSupport;

import agtc.sampletracking.dao.SampleTypeDAO;
import agtc.sampletracking.model.SampleType;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SampleTypeEditor  extends PropertyEditorSupport {
	private SampleTypeDAO sampleTypeDAO;
	public void setAsText(String sampleTypeId) throws IllegalArgumentException { 
	 
		 // text is the string from the form, the containerTypeId is the identifier in 
		 // the database 

		 SampleType sampleType=null; 
		  if(sampleTypeId!=null&&!sampleTypeId.equals("")){ 
		  	sampleType=sampleTypeDAO.getSampleType(new Integer(sampleTypeId));       
		  }      
		  setValue(sampleType); 
 
	   } 

	public String getAsText(Object value) { 
	   //return the id value of the object 

	   	SampleType sampleType = (SampleType) value; 
		return String.valueOf(sampleType.getSampleTypeId()); 
	}
	

	/**
	 * @return Returns the sampleTypeDAO.
	 */
	public SampleTypeDAO getSampleTypeDAO() {
		return sampleTypeDAO;
	}
	/**
	 * @param sampleTypeDAO The sampleTypeDAO to set.
	 */
	public void setSampleTypeDAO(SampleTypeDAO sampleTypeDAO) {
		this.sampleTypeDAO = sampleTypeDAO;
	}
}

/*
 * Created on Nov 4, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.editor;

import java.beans.PropertyEditorSupport;
import agtc.sampletracking.dao.InvestigatorDAO;
import agtc.sampletracking.model.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class InvestigatorEditor extends PropertyEditorSupport {
	private InvestigatorDAO investigatorDAO;
	public void setAsText(String investigatorId) throws IllegalArgumentException { 
		 
		 // text is the string from the form, the investigatorId is the identifier in 
		 // the database 
    
		 Investigator investigator=null; 
		  if(investigatorId!=null&&!investigatorId.equals("")){ 
			investigator=investigatorDAO.getInvestigator(new Integer(investigatorId));       
		  }      
		  setValue(investigator); 
     
	   } 

	   public String getAsText(Object value) { 
	   //return the id value of the object 
    
		   Investigator investigator = (Investigator) value; 
		   return String.valueOf(investigator.getInvestigatorId()); 
	   }

	/**
	 * @return
	 */
	public InvestigatorDAO getInvestigatorDAO() {
		return investigatorDAO;
	}

	/**
	 * @param investigatorDAO
	 */
	public void setInvestigatorDAO(InvestigatorDAO investigatorDAO) {
		this.investigatorDAO = investigatorDAO;
	}

}

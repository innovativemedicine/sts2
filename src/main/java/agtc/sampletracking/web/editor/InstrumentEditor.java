/*
 * Created on Nov 30, 2004
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
public class InstrumentEditor extends PropertyEditorSupport {
	private InstrumentDAO instrumentDAO;
	public void setAsText(String instrumentId) throws IllegalArgumentException { 
	 
	 // text is the string from the form, the instrumentId is the identifier in 
	 // the database 

	 Instrument instrument=null; 
	  if(instrumentId!=null&&!instrumentId.equals("")){ 
			instrument=instrumentDAO.getInstrument(new Integer(instrumentId));       
		  }      
		  setValue(instrument); 
 
	   } 

	   public String getAsText(Object value) { 
	   //return the id value of the object 

	   Instrument instrument = (Instrument) value; 
	   return String.valueOf(instrument.getInstrumentId()); 
   }
	/**
	 * @return
	 */
	public InstrumentDAO getInstrumentDAO() {
		return instrumentDAO;
	}

	/**
	 * @param instrumentDAO
	 */
	public void setInstrumentDAO(InstrumentDAO instrumentDAO) {
		this.instrumentDAO = instrumentDAO;
	}

}

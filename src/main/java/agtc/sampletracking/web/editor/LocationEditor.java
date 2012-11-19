/*
 * Created on Nov 25, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.editor;

import java.beans.PropertyEditorSupport;
import agtc.sampletracking.dao.LocationDAO;
import agtc.sampletracking.model.*;

/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class LocationEditor extends PropertyEditorSupport {
	private LocationDAO locationDAO;
	public void setAsText(String locationId) throws IllegalArgumentException { 
	 
	 // text is the string from the form, the locationId is the identifier in 
	 // the database 

		 Location location=null; 
		  if(locationId!=null&&!locationId.equals("")){ 
				location=locationDAO.getLocation(new Integer(locationId));       
		 }      
		setValue(location); 
 
	} 

	public String getAsText(Object value) { 
	   //return the id value of the object 

	   Location location = (Location) value; 
	   return String.valueOf(location.getLocationId()); 
   }

	/**
	 * @return
	 */
	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	/**
	 * @param locationDAO
	 */
	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

}

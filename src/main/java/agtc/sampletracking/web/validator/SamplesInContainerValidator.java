/*
 * Created on Mar 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.SamplesInContainer;
import java.util.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SamplesInContainerValidator implements Validator {

	public boolean supports(Class clazz) {
		return SamplesInContainer.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		SamplesInContainer s = (SamplesInContainer)obj;
		
		String well = s.getWell();
		
		
		if(well!=null && well.length()>0 && !well.matches("[A-J]{1}[0-9]{1,2}")){
			errors.rejectValue( "well","error.wrongFormat","Wrong format");
		}
		
		/*
		Set sics = s.getContainer().getSamplesInContainers();
		Iterator i = sics.iterator();
		while(i.hasNext()){
			SamplesInContainer sic = (SamplesInContainer)i.next();
			if(well!=null && sic!=null && well.compareTo(sic.getWell())== 0){
				errors.rejectValue( "well","error.duplicateWell","This well has already been occupied");
			}
		}
		*/
	}
		
	
}

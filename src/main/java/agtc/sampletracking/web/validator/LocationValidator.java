/*
 * Created on Jan 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.Location;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocationValidator implements Validator {
	public boolean supports(Class clazz) {
		return Location.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "name", "required", "Name required");
	}
}

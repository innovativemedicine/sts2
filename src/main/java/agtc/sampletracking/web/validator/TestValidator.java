/*
 * Created on Mar 15, 2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.Test;

/**
 * @author Hongjing
 */
public class TestValidator implements Validator {

	public boolean supports(Class clazz) {
		return Test.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "name", "required", "required");

	}

}

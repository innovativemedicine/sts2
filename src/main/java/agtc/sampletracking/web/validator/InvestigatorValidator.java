package agtc.sampletracking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.Investigator;
/**
 * <code>Validator</code> for <code>Owner</code> forms.
 *
 * @author Jianan Xiao Dec 22,2004
 * 
 */
public class InvestigatorValidator implements Validator {

	public boolean supports(Class clazz) {
		return Investigator.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name.fname", 
				"required", "First Name required");
		ValidationUtils.rejectIfEmpty(errors, 
				"name.lname", "required", "Last Name required");
	}

}
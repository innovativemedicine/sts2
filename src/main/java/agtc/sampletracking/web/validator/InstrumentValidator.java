package agtc.sampletracking.web.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import agtc.sampletracking.model.*;
/**
 * <code>Validator</code> for <code>Owner</code> forms.
 *
 * @author Jianan Xiao Dec 22,2004
 * 
 */
public class InstrumentValidator implements Validator {

	public boolean supports(Class clazz) {
		return Instrument.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "name", "required", "required");
	}

}
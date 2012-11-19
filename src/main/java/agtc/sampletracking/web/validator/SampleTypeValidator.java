/*
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.validator;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
 
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import agtc.sampletracking.model.*;
/**
 * <code>Validator</code> for <code>Sample Type</code> forms.
 *
 * @author Jianan Xiao Jan 10,2005
 * 
 */
public class SampleTypeValidator implements Validator {

	public boolean supports(Class clazz) {
		return SampleType.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "name", "required", "Name is required");
		ValidationUtils.rejectIfEmpty(errors, "suffix", "required", "Suffix is required");
	}

}
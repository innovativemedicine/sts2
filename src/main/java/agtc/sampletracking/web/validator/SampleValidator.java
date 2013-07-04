/*
 * Created on Mar 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.Sample;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SampleValidator implements Validator {
	private Log log = LogFactory.getLog(SampleValidator.class);
	public boolean supports(Class clazz) {
		return Sample.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		Sample s = (Sample)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patient.intSampleId", "Internal ID required", "Internal ID required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patient.extSampleId", "External ID required", "External ID required");
		
		Pattern pattern = Pattern.compile("[A-Za-z]{1,4}[0-9]{1,6}");
		Matcher matcher = pattern.matcher(s.getPatient().getIntSampleId());
		
		if(!matcher.matches()){
			errors.rejectValue( "patient.intSampleId","error.wrongFormat","Wrong format");
		}
		
	
	}

}

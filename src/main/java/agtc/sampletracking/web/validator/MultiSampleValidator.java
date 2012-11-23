package agtc.sampletracking.web.validator;

import java.util.regex.*;

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
public class MultiSampleValidator implements Validator {
	private Log log = LogFactory.getLog(MultiSampleValidator.class);
	public boolean supports(Class clazz) {
		return Sample.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		Sample s = (Sample)obj;
			
			String intSampleField = "patient.intSampleId";
		
			String extSampleField = "patient.extSampleId";
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, intSampleField,  "Internal ID required", "Internal ID required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, extSampleField,  "External ID required", "External ID required");
			
			Pattern pattern = Pattern.compile("[A-Za-z]{1,4}[0-9]{1,6}");
			Matcher matcher = pattern.matcher(s.getPatient().getIntSampleId());
				
			//log.debug("the matching result is " + matcher.matches());
			
				if(!matcher.matches()){
					errors.rejectValue( intSampleField, "error.wrongFormat","Wrong format");
		}
	}

}
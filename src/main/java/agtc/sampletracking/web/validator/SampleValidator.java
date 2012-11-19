/*
 * Created on Mar 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.validator;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.bus.manager.SampleManager;
import agtc.sampletracking.model.Sample;
import agtc.sampletracking.model.SamplesInContainer;

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
		ValidationUtils.rejectIfEmpty(errors, "patient.intSampleId", "required", "required");
		
		Pattern pattern = Pattern.compile("[A-Za-z]{1,4}[0-9]{1,6}");
		Matcher matcher = pattern.matcher(s.getPatient().getIntSampleId());

		//log.debug("the matching result is " + matcher.matches());
		
		if(!matcher.matches()){
			errors.rejectValue( "patient.intSampleId","error.wrongFormat","Wrong format");
		}
		
	}

}

package agtc.sampletracking.web.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.SamplePrefix;
//import agtc.sampletracking.model.SamplesInContainer;
   
public class SampleSourceTypeValidator implements Validator {
	private Log log = LogFactory.getLog(SampleSourceTypeValidator.class);
	public boolean supports(Class clazz) {
		return SamplePrefix.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		SamplePrefix s = (SamplePrefix)obj;
		ValidationUtils.rejectIfEmpty(errors, "description", "required", "required");	
				
	}

}
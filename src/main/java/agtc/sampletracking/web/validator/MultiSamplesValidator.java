package agtc.sampletracking.web.validator;

import java.util.regex.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.MultiSamples;
import agtc.sampletracking.model.Sample;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MultiSamplesValidator implements Validator {
	private Log log = LogFactory.getLog(MultiSamplesValidator.class);
	
	public boolean supports(Class clazz) {
		return MultiSamples.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		MultiSamples multiSamples = (MultiSamples)obj;
			
			for(int i=0; i<multiSamples.getMultiSamples().size(); i++)
			{
				System.out.println("Entering Multi Validator");
				System.out.println("Size:" + multiSamples.getMultiSamples().size());
				
				Sample s = (Sample)multiSamples.getMultiSamples().get(i);
				
				System.out.println("Patient ID:" + s.getPatient().getIntSampleId());
				
				String intSampleField = "multiSamples[" + i + "].patient.intSampleId";
				String extSampleField = "multiSamples[" + i + "].patient.extSampleId";
				
				System.out.println(intSampleField);
				System.out.println(extSampleField);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, intSampleField,  "error.required", "Required: Internal ID");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, extSampleField,  "error.required", "Required: External ID");
				
				Pattern pattern = Pattern.compile("[A-Za-z]{1,4}[0-9]{1,6}");
				Matcher matcher = pattern.matcher(s.getPatient().getIntSampleId());
					
				//log.debug("the matching result is " + matcher.matches());
				
					if(!matcher.matches()){
						errors.rejectValue( intSampleField, "error.wrongFormat","Wrong format: Internal ID");
			}
		}
	}

}
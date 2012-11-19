/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.Container;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContainerValidator implements Validator {

	public boolean supports(Class clazz) {
		return Container.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		Container container = (Container)obj;
		
		ValidationUtils.rejectIfEmpty(errors, "name", "required", "required");
		if(container.getName().length()>128){
			errors.rejectValue( "name","error.nameTooLong",new String[]{Integer.toString(container.getName().length())},"Name too long");
		}
	}
}

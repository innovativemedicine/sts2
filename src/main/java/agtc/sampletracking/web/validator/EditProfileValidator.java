package agtc.sampletracking.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import agtc.sampletracking.model.User;

public class EditProfileValidator implements Validator{
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "loginname", "required", "Login Name is required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required", "Password is required");
		
	}

}

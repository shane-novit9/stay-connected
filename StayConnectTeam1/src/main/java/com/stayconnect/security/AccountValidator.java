package com.stayconnect.security;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;


@Component
public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WebAccount.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		WebAccount webAcct = (WebAccount) target;
		
		String password = webAcct.getPassword();
		String passConfirm = webAcct.getPassConfirm();
		
		if(!password.equals(passConfirm)) {
			errors.rejectValue("password", "webAcct.password.misMatch");
			errors.rejectValue("passConfirm", "webAcct.password.misMatch");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "webAcct.email.empty");
	}

}

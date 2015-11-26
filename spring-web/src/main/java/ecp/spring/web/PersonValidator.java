package ecp.spring.web;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import ecp.spring.model.ContactInfo;
import ecp.spring.model.Person;

public class PersonValidator implements Validator{
	@Override
	public boolean supports(Class clazz){
		return Person.class.equals(clazz);
	}

	public void validate(Object target, Errors errors){
		Person person = (Person)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.firstName", "error.name.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.lastName", "error.name.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.middleName", "error.name.middleName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "error.birthday");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.houseNo", "error.address.houseNo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.street", "error.address.street");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.subdivision", "error.address.subdivision");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.municipality", "error.address.municipality");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.barangay", "error.address.barangay");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zipcode", "error.address.zipcode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.province", "error.address.province");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employmentStatus", "error.employmentStatus");
	}
}
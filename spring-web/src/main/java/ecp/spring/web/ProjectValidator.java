package ecp.spring.web;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import ecp.spring.model.Project;

public class ProjectValidator implements Validator{
	@Override
	public boolean supports(Class clazz){
		return Project.class.equals(clazz);
	}

	public void validate(Object target, Errors errors){
		Project project = (Project)target;

		if(project.getStartDate().after(project.getEndDate())){
			errors.rejectValue("endDate", "error.project.date");
		}
	}
}
package ecp.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ecp.spring.model.Person;
import ecp.spring.model.PersonModel;
import ecp.spring.model.FileUpload;
import ecp.spring.web.PersonFileParser;
import ecp.spring.service.PersonManagerImpl;
import java.util.List;

public class UploadController extends SimpleFormController {

	PersonFileParser parser;
	PersonManagerImpl personManagerImpl;
	private final Logger logger = LoggerFactory.getLogger(AddPersonController.class);
	public void setParser(PersonFileParser parser) {
		this.parser = parser;
	}

	public void setPersonManagerImpl(PersonManagerImpl personManagerImpl) {
		this.personManagerImpl = personManagerImpl;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, 
									HttpServletResponse response, 
									Object command, BindException errors) {
		logger.info("UploadController onSubmit() method");
	    FileUpload f = (FileUpload)command;
        MultipartFile file = f.getFile();
		Person person;
		if(file != null){
			person = parser.extractPersonFromFile(file);
			personManagerImpl.addPerson(person);
		}
		
		ModelAndView model = new ModelAndView();
        model.setView(new RedirectView("/"));
		List<PersonModel> list = personManagerImpl.listPerson(0,1,"id");
		model.addObject("personList",list);
		return model;
	}
}
	

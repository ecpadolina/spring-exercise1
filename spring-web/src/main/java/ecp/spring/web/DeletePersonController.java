package ecp.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;	
import ecp.spring.service.PersonManagerImpl;
import ecp.spring.service.RoleManagerImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecp.spring.model.Person;
import ecp.spring.model.PersonModel;
import java.util.List;

public class DeletePersonController extends SimpleFormController{
	PersonManagerImpl personManagerImpl;
	private final Logger logger = LoggerFactory.getLogger(DeletePersonController.class);
	public void setPersonManagerImpl(PersonManagerImpl personManagerImpl){
		this.personManagerImpl = personManagerImpl;
	}

	public DeletePersonController() {
        setCommandClass(Person.class);
        setCommandName("person");
    }

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
    	logger.info("DeletePersonController showForm() method");
		System.out.println(request.getParameter("id"));
		Person person = personManagerImpl.getPerson(Integer.parseInt(request.getParameter("id")));
		person.setRoles(null);
		personManagerImpl.deletePerson(person);
		ModelAndView model = new ModelAndView("redirect:/");
		List<PersonModel> list = personManagerImpl.listPerson(0,1,"id");
		model.addObject("personList",list);
		return model;
    }
}
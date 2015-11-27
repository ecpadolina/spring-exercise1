package ecp.spring.web;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import ecp.spring.model.PersonModel;
import ecp.spring.model.Person;
import ecp.spring.model.Role;
import ecp.spring.service.PersonManagerImpl;
import ecp.spring.service.RoleManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@SuppressWarnings("deprecation")
public class IndexController extends SimpleFormController{
	PersonManagerImpl personManagerImpl;
	RoleManagerImpl roleManagerImpl;

	public void setPersonManagerImpl(PersonManagerImpl personManagerImpl){
		this.personManagerImpl = personManagerImpl;
	}

	public void setRoleManagerImpl(RoleManagerImpl roleManagerImpl){
		this.roleManagerImpl = roleManagerImpl;
	}


	public IndexController() {
        setCommandClass(PersonModel.class);
    }

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
    	ModelAndView model = new ModelAndView("index");
		List<PersonModel> list = personManagerImpl.listPerson(0, 1, "id");
		model.addObject("personList",list);
		model.addObject("roleList", roleManagerImpl.listRolesWithPerson());
        return model;
    }

  	protected ModelAndView onSubmit(HttpServletRequest request, 
									HttpServletResponse response, 
									Object command, BindException errors) {
		ModelAndView model = new ModelAndView("index");
		String column = request.getParameter("column");
		int id = Integer.parseInt(request.getParameter("role"));
		int order = Integer.valueOf(request.getParameter("order"));
		List<PersonModel> list = personManagerImpl.listPerson(id, order, column);
		model.addObject("personList",list);
		model.addObject("roleList", roleManagerImpl.listRolesWithPerson());
        return model;
	}

}
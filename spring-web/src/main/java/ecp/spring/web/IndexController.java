package ecp.spring.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import ecp.spring.model.PersonModel;
import ecp.spring.model.Person;
import ecp.spring.model.Role;
import ecp.spring.service.PersonManagerImpl;
import ecp.spring.service.RoleManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@SuppressWarnings("deprecation")
public class IndexController extends AbstractController{
	PersonManagerImpl personManagerImpl;
	RoleManagerImpl roleManagerImpl;

	public void setPersonManagerImpl(PersonManagerImpl personManagerImpl){
		this.personManagerImpl = personManagerImpl;
	}

	public void setRoleManagerImpl(RoleManagerImpl roleManagerImpl){
		this.roleManagerImpl = roleManagerImpl;
	}

	@Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView model = new ModelAndView("index");
    	String column = "id";
    	int order = 1;
    	int id = 0;
    	if(request.getParameter("column") != null){
    		column = request.getParameter("column");
    		order = Integer.parseInt(request.getParameter("order"));
    		id = Integer.parseInt(request.getParameter("role"));
    	}
    	if(request.getParameter("id") != null){
    		Person person = personManagerImpl.getPerson(Integer.parseInt(request.getParameter("id")));
    		person.setRoles(null);
			personManagerImpl.deletePerson(person);
    	}
		List<PersonModel> list = personManagerImpl.listPerson(id, order, column);
		model.addObject("personList",list);
		model.addObject("roleList", roleManagerImpl.listRolesWithPerson());
        return model;
    }
}
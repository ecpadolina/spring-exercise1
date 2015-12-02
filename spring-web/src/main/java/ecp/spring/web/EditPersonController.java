package ecp.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import ecp.spring.model.ContactInfo;
import ecp.spring.model.Person;
import ecp.spring.model.PersonModel;
import ecp.spring.model.Role;
import ecp.spring.service.PersonManagerImpl;
import ecp.spring.service.RoleManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.text.SimpleDateFormat;

public class EditPersonController extends SimpleFormController{
	PersonManagerImpl personManagerImpl;
	RoleManagerImpl roleManagerImpl;
	private final Logger logger = LoggerFactory.getLogger(EditPersonController.class);
	public void setPersonManagerImpl(PersonManagerImpl personManagerImpl){
		this.personManagerImpl = personManagerImpl;
	}

	public void setRoleManagerImpl(RoleManagerImpl roleManagerImpl){
		this.roleManagerImpl = roleManagerImpl;
	}

	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" );
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		logger.info("formBackingObject()");
		return personManagerImpl.getPerson(Integer.parseInt(request.getParameter("id")));
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request,
                                HttpServletResponse response,
                                BindException errors)
                         throws Exception {
        logger.info("EditPersonController showForm() method");
		ModelAndView model = new ModelAndView(getFormView());
        model.addAllObjects(errors.getModel());
		Map map = errors.getModel();
        Person person = (Person)map.values().iterator().next();
        model.addObject("contacts", person.getContacts());
        model.addObject("roles", roleManagerImpl.getRoles(1, "roleId"));
        return model;
	}

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request,
    											 HttpServletResponse response, 
												 Object command, 
												 BindException errors) throws Exception { 
		logger.info("EditPersonController processFormSubmission() method");
		Person person = (Person) command;
		if(errors.hasErrors()) {
		    ModelAndView mav = new ModelAndView(getFormView());
		    mav.addAllObjects(errors.getModel());
		    mav.addObject("roles", roleManagerImpl.getRoles(1, "roleId"));
		    mav.addObject("contacts", person.getContacts());
		    return mav;

		} else {
			return onSubmit(request,response,command,errors);		
		}

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception{
		logger.info("EditPersonController onSubmit() method");
		HashSet<Role> roles = new HashSet<Role>();
        Person person = (Person) command;
		Set<ContactInfo> contacts = new HashSet<ContactInfo>();
		Set<ContactInfo> oldContacts;
		String[] tempRoles = request.getParameterValues("personRoles");
		String[] contactInfo = request.getParameterValues("contactInfo");
		String[] contactType = request.getParameterValues("contactType");

		if(tempRoles != null) { 
			for(String roleId : tempRoles) {
				int id = Integer.parseInt(roleId);
				Role role = roleManagerImpl.getRole(id);
				roles.add(role);
			}
		    person.setRoles(roles);
		}

		if(contactInfo != null){
			for(int i = 0; i < contactInfo.length; i++){
				ContactInfo contact = new ContactInfo();
				contact.setContactInfo(contactInfo[i]);
				contact.setContactType(contactType[i]);
				contacts.add(contact);
			}
			person.setContacts(contacts);
		}

        personManagerImpl.updatePerson(person);
		ModelAndView model = new ModelAndView("redirect:/");
		List<PersonModel> list = personManagerImpl.listPerson(0,1,"id");
		model.addObject("personList",list);
        return model;
	}
}
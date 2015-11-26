package ecp.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;	
import ecp.spring.service.RoleManagerImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ecp.spring.model.Role;
import java.util.List;

public class DeleteRoleController extends SimpleFormController{
	RoleManagerImpl roleManagerImpl;
	private final Logger logger = LoggerFactory.getLogger(DeleteRoleController.class);
	public void setRoleManagerImpl(RoleManagerImpl roleManagerImpl){
		this.roleManagerImpl = roleManagerImpl;
	}

	public DeleteRoleController() {
        setCommandClass(Role.class);
        setCommandName("role");
    }

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
    	logger.info("DeleteRoleController showForm() method");
    	logger.error("Error DeleteRoleController showForm() method ");
		ModelAndView model = new ModelAndView();
		System.out.println(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("id"));
		Role role = roleManagerImpl.getRole(id);
		role.setPersons(null);
		roleManagerImpl.deleteRole(id);
        model.setView(new RedirectView("/roleIndex"));
		List<Role> list = roleManagerImpl.getRoles(1,"roleId");
		model.addObject("roleList",list);
		return model;
    }
}
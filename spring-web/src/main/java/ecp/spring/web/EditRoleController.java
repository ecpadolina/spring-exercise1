package ecp.spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.bind.ServletRequestDataBinder;
import ecp.spring.model.Role;
import ecp.spring.service.RoleManagerImpl;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRoleController extends SimpleFormController{
	RoleManagerImpl roleManagerImpl;
	private final Logger logger = LoggerFactory.getLogger(EditRoleController.class);
	public void setRoleManagerImpl(RoleManagerImpl roleManagerImpl){
		this.roleManagerImpl = roleManagerImpl;
	}

	public EditRoleController(){
		setCommandClass(Role.class);
		setCommandName("role");
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return roleManagerImpl.getRole(Integer.parseInt(request.getParameter("id")));
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request,
                                HttpServletResponse response,
                                BindException errors)
                         throws Exception {

		ModelAndView model = new ModelAndView(getFormView());
		Map map = errors.getModel();
		Role role = (Role)map.values().iterator().next();
        model.addAllObjects(errors.getModel());
		return model;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, 
									HttpServletResponse response, 
									Object command, BindException errors){
		logger.info("EditRoleController onSubmit() method");
		Role role = (Role)command;
		roleManagerImpl.updateRole(role);
		ModelAndView model = new ModelAndView("redirect:/roleIndex");
		List<Role> list = roleManagerImpl.getRoles(1,"roleId");
		model.addObject("roleList",list);
        return model;
	}
}
package ecp.spring.web;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import ecp.spring.model.Role;
import ecp.spring.service.RoleManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@SuppressWarnings("deprecation")
public class RoleIndexController extends SimpleFormController{
	RoleManagerImpl roleManagerImpl;

	public void setRoleManagerImpl(RoleManagerImpl roleManagerImpl){
		this.roleManagerImpl = roleManagerImpl;
	}

	public RoleIndexController() {
        setCommandClass(Role.class);
    }

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
    	ModelAndView model = new ModelAndView("roleIndex");
		List<Role> list = roleManagerImpl.getRoles(1, "roleId");
		model.addObject("roleList",list);
        return model;
    }

  	protected ModelAndView onSubmit(HttpServletRequest request, 
									HttpServletResponse response, 
									Object command, BindException errors) {
		ModelAndView model = new ModelAndView("roleIndex");
		String column = request.getParameter("column");
		int order = Integer.valueOf(request.getParameter("order"));
		List<Role> list = roleManagerImpl.getRoles(order,column);
		model.addObject("roleList",list);
        return model;
	}

}
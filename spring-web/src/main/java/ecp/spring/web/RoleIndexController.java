package ecp.spring.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import ecp.spring.model.Role;
import ecp.spring.service.RoleManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@SuppressWarnings("deprecation")
public class RoleIndexController extends AbstractController{
	RoleManagerImpl roleManagerImpl;

	public void setRoleManagerImpl(RoleManagerImpl roleManagerImpl){
		this.roleManagerImpl = roleManagerImpl;
	}

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView model = new ModelAndView("roleIndex");
    	String column = "roleId";
    	int order = 1;

    	if(request.getParameter("column") != null){
    		column = request.getParameter("column");
    		order = Integer.parseInt(request.getParameter("order"));
    	}
        if(request.getParameter("roleId") != null){
            int id = Integer.parseInt(request.getParameter("roleId"));
            Role role = roleManagerImpl.getRole(id);
            role.setPersons(null);
            roleManagerImpl.deleteRole(id);
        }

		List<Role> list = roleManagerImpl.getRoles(order, column);
		model.addObject("roleList",list);
        return model;
    }

}
package ecp.spring.web;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import ecp.spring.model.Project;
import ecp.spring.service.ProjectManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@SuppressWarnings("deprecation")
public class ProjectIndexController extends SimpleFormController{
	ProjectManagerImpl projectManagerImpl;

	public void setProjectManagerImpl(ProjectManagerImpl projectManagerImpl){
		this.projectManagerImpl = projectManagerImpl;
	}

	public ProjectIndexController() {
        setCommandClass(Project.class);
    }

    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) {
    	ModelAndView model = new ModelAndView("projectIndex");
		List<Project> list = projectManagerImpl.listProjects();
		model.addObject("projectList",list);
        return model;
    }

  	protected ModelAndView onSubmit(HttpServletRequest request, 
									HttpServletResponse response, 
									Object command, BindException errors) {
		Integer id = Integer.parseInt(request.getParameter("projectId"));
		if(id != null){
			projectManagerImpl.deleteProject(id);
		}
		ModelAndView model = new ModelAndView("redirect:/projectIndex");
		List<Project> list = projectManagerImpl.listProjects();
		model.addObject("projectList",list);
        return model;
	}

}
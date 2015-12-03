package ecp.spring.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import ecp.spring.model.Project;
import ecp.spring.service.ProjectManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@SuppressWarnings("deprecation")
public class ProjectIndexController extends AbstractController{
	ProjectManagerImpl projectManagerImpl;

	public void setProjectManagerImpl(ProjectManagerImpl projectManagerImpl){
		this.projectManagerImpl = projectManagerImpl;
	}

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView model = new ModelAndView("projectIndex");

		if(request.getParameter("projectId") != null){
			projectManagerImpl.deleteProject(Integer.parseInt(request.getParameter("projectId")));
		}

		List<Project> list = projectManagerImpl.listProjects();
		model.addObject("projectList",list);
        return model;
    }

}
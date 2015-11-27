package ecp.spring.web;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;	
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;

import ecp.spring.model.Project;
import ecp.spring.model.Person;
import ecp.spring.model.PersonModel;
import ecp.spring.service.ProjectManagerImpl;
import ecp.spring.service.PersonManagerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.text.SimpleDateFormat;

public class EditProjectController extends SimpleFormController{
	PersonManagerImpl personManagerImpl;
	ProjectManagerImpl projectManagerImpl;

	public void setPersonManagerImpl(PersonManagerImpl personManagerImpl){
		this.personManagerImpl = personManagerImpl;
	}

	public void setProjectManagerImpl(ProjectManagerImpl projectManagerImpl){
		this.projectManagerImpl = projectManagerImpl;
	}

	public EditProjectController(){
		setCommandName("project");
		setCommandClass(Project.class);
	}

	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return projectManagerImpl.getProject(Integer.parseInt(request.getParameter("id")));
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request,
                                HttpServletResponse response,
                                BindException errors)
                         throws Exception {
		ModelAndView model = new ModelAndView("projectForm");
        model.addAllObjects(errors.getModel());
        Map map = errors.getModel();
		Project project = (Project)map.values().iterator().next();
        List<PersonModel> list = personManagerImpl.listPerson(0,1,"id");
        model.addObject("persons", list);
		return model;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, 
									HttpServletResponse response, 
									Object command, BindException errors){
		Project project = (Project)command;
		Set<Person> members = new HashSet<Person>();
		String[] ids = request.getParameterValues("members");
		for(String id : ids){
			members.add(personManagerImpl.getPerson(Integer.parseInt(id)));
		}
		project.setPersons(members);
		projectManagerImpl.updateProject(project);
		ModelAndView model = new ModelAndView();
		model.setView(new RedirectView("/projectIndex"));
		List<Project> list = projectManagerImpl.listProjects();
		model.addObject("projectList",list);
        return model;
	}
}
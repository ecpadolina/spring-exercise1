package ecp.spring.service;

import java.util.List;
import ecp.spring.model.Project;
import ecp.spring.dao.ProjectDaoImpl;

public class ProjectManagerImpl implements ProjectManager{
	ProjectDaoImpl projectDao;

	public void setProjectDao(ProjectDaoImpl projectDao){
    	this.projectDao = projectDao;
  	}

  	public List listProjects(){
  		return projectDao.listProjects();
  	}

  	public Project getProject(int id){
  		return projectDao.getProject(id);
  	}

  	public void addProject(Project project){
  		projectDao.addProject(project);
  	}

  	public void deleteProject(int id){
  		projectDao.deleteProject(id);
  	}

  	public void updateProject(Project project){
  		projectDao.updateProject(project);
  	}
}
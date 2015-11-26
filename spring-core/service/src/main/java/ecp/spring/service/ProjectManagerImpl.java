package ecp.spring.service;

import java.util.List;
import ecp.spring.model.Project;
import ecp.spring.dao.ProjectDaoImpl;

public class ProjectManagerImpl{
	ProjectDaoImpl projectDaoImpl;

	public void setProjectDaoImpl(ProjectDaoImpl projectDaoImpl){
    	this.projectDaoImpl = projectDaoImpl;
  	}

  	public List listProjects(){
  		return projectDaoImpl.listProjects();
  	}

  	public Project getProject(int id){
  		return projectDaoImpl.getProject(id);
  	}

  	public void addProject(Project project){
  		projectDaoImpl.addProject(project);
  	}

  	public void deleteProject(int id){
  		projectDaoImpl.deleteProject(id);
  	}

  	public void updateProject(Project project){
  		projectDaoImpl.updateProject(project);
  	}
}
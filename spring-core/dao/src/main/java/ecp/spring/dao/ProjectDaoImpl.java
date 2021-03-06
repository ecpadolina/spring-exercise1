package ecp.spring.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import ecp.spring.model.Project;

public class ProjectDaoImpl implements ProjectDao {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    
	public List listProjects() {
		List list = sessionFactory.getCurrentSession().createCriteria(Project.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	public Project getProject(int id) {
		return (Project)sessionFactory.getCurrentSession().get(Project.class, id);
	}

	public void addProject(Project project) {
		sessionFactory.getCurrentSession().save(project);
	}

	public void deleteProject(int id) {
		sessionFactory.getCurrentSession().delete(getProject(id));
	}

    public void updateProject(Project project) {
		sessionFactory.getCurrentSession().update(project);
    }

}

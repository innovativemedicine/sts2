/*
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import agtc.sampletracking.dao.ProjectSamplesDAO;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.model.ProjectSamples;
import agtc.sampletracking.web.command.SearchCommand;

/**
 * @author Bei Jin
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ProjectSamplesDAOHbImpl
	extends STSBasicDAO
	implements ProjectSamplesDAO {
	private Log log = LogFactory.getLog(ProjectSamplesDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ProjectSamplesDAO#getProjects()
	 */
	public List getProjects() {
		return  getHibernateTemplate().find("select p from Project p left join fetch p.investigator order by p.name");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ProjectDAO#getProject(java.lang.Integer)
	 */
	public Project getProject(Integer projectId) {
		return (Project)(getHibernateTemplate().get(Project.class,projectId));
	}
	
	public Project getProject(String projectName) {
		return (Project)(getHibernateTemplate().find("from Project p where p.name=?",projectName).get(0));
	}
	
	
	public void saveProject(Project project) throws Exception {
		getHibernateTemplate().saveOrUpdate(project);
	}

	public void removeProject(Integer projectId) {
		Object project = getHibernateTemplate().load(Project.class,projectId);
		getHibernateTemplate().delete(project);

	}

	public List getAssayByProjectId(Integer projectId) {
		String sql = "from Assay a where a.assayId in "
						+" (select distinct r.assay.assayId from Result r "
						+" where r.run.runId in( select ru.runId from Run ru where ru.project.projectId=?))";
		return getHibernateTemplate().find(sql,projectId);
	}

}

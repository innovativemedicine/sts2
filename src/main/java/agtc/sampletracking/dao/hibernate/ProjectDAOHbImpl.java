/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;

import agtc.sampletracking.dao.ProjectDAO;
import agtc.sampletracking.model.Project;
import agtc.sampletracking.web.command.SearchCommand;
import agtc.sampletracking.web.searchFields.ProjectSearchFields;

/**
 * @author Hongjing
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ProjectDAOHbImpl
	extends STSBasicDAO
	implements ProjectDAO {
	private Log log = LogFactory.getLog(ProjectDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ProjectDAO#getProjects()
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
	
	public List getProjects(List crtList,List lgcList){
		Session session = getSession();
		Criteria crt = session.createCriteria(Project.class);
		
		
		// the size of lgcList is always one less than the size of crtList
		if(crtList.size()>1){
			for (int i=0;i<crtList.size()-1;i++){
				SearchCommand searchCommand = (SearchCommand)crtList.get(i);
				ProjectSearchFields.getExpression(crt,searchCommand);
				if(lgcList.size()>i){
					String logicalOperator = (String)lgcList.get(i);
					getExpression(crt,logicalOperator);
				}
			}
		}
		SearchCommand searchCommand = (SearchCommand)crtList.get(crtList.size()-1);
		ProjectSearchFields.getExpression(crt,searchCommand);
		log.debug(" the criteria is " + crt.toString());
		return crt.list();
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ProjectDAO#saveProject(agtc.sampletracking.model.Project)
	 */
	public void saveProject(Project project) throws Exception {
		getHibernateTemplate().saveOrUpdate(project);
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.ProjectDAO#removeProject(java.lang.Integer)
	 */
	public void removeProject(Integer projectId) {
		Object project = getHibernateTemplate().load(Project.class,projectId);
		getHibernateTemplate().delete(project);

	}
	
	/*	 Get Assay List by projectId
	 * *		Jianan Xiao 2006-02-15
	 * * select distinct a.assay_id, a.name, a.note, a.location from result r,assay a 
where r.assay_id=a.assay_id and run_id in( select run_id from run where project_id=3);
	 */
	public List getAssayByProjectId(Integer projectId) {
		String sql = "from Assay a where a.assayId in "
						+" (select distinct r.assay.assayId from Result r "
						+" where r.run.runId in( select ru.runId from Run ru where ru.project.projectId=?))";
		return getHibernateTemplate().find(sql,projectId);
	}
}

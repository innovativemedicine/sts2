/*
 * Created on Jan 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import agtc.sampletracking.dao.SamplePrefixDAO;
import agtc.sampletracking.model.Assay;
import agtc.sampletracking.model.SamplePrefix;
import agtc.sampletracking.web.controller.SamplesSearchController;

/**
 * @author Gloria
 *
 */
public class SamplePrefixDAOHbImpl extends HibernateDaoSupport implements SamplePrefixDAO {
	private Log log = LogFactory.getLog(SamplePrefixDAOHbImpl.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplePrefixDAO#getSamplePrefixes()
	 */
	public List getSamplePrefixes() {
		return  getHibernateTemplate().find("select s from SamplePrefix s order by s.prefix");
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplePrefixDAO#getSamplePrefix(java.lang.Integer)
	 */
	public SamplePrefix getSamplePrefix(Integer samplePrefixId) {
		return (SamplePrefix)(getHibernateTemplate().get(SamplePrefix.class,samplePrefixId));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplePrefixDAO#getSamplePrefixByPrefix(java.lang.String)
	 */
	public SamplePrefix getSamplePrefixByDescription(String des) {
		log.debug("the des is " + des);
		return  (SamplePrefix)(getHibernateTemplate().find("from SamplePrefix s where s.description='"+des+"'").get(0));
	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplePrefixDAO#saveSamplePrefix(agtc.sampletracking.model.SamplePrefix)
	 */
	public void saveSamplePrefix(SamplePrefix o) throws Exception {
		getHibernateTemplate().saveOrUpdate(o);

	}

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.SamplePrefixDAO#removeSamplePrefix(java.lang.Integer)
	 */
	public void removeSamplePrefix(Integer samplePrefixId) {
		Object o = getHibernateTemplate().load(SamplePrefix.class,samplePrefixId);
		getHibernateTemplate().delete(o);

	}

}

/*
 * Created on Jan 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import agtc.sampletracking.dao.OtherDAO;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OtherDAOJdbcImpl extends JdbcDaoSupport implements
OtherDAO{
	public void removeSamplesInContainer(Integer sicId){
		getJdbcTemplate().execute("delete from SAMPLES_IN_CONTAINER where SICID = " + sicId.toString());
	}
}

/*
 * Created on Jan 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.*;
import java.sql.*;

import agtc.sampletracking.dao.CGGStudyGroupDAO;
import agtc.sampletracking.bus.*;


/**
 * @author Gloria Deng
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CGGStudyGroupDAOJdbcImpl extends JdbcDaoSupport implements
		CGGStudyGroupDAO {

	/* (non-Javadoc)
	 * @see agtc.sampletracking.dao.CGGStudyGroupDAO#getAllStudyGroups()
	 */
	public List getAllStudyGroups() {
		return getJdbcTemplate().query("select STUDY_GROUP_ID,STUDY_GROUP_NAME from cgg.STUDY_GROUP",new QueryStudyGroupHandler());
	}
	
	/**
	 * 
	 * @author Gloria Deng
	 * inner Class to handle resultset of the getAllStudyGroup query
	 */
	
	public class QueryStudyGroupHandler implements RowMapper{
		public Object mapRow(ResultSet rs,int rowNum) throws SQLException{
			int id = rs.getInt("STUDY_GROUP_ID");
			String name = rs.getString("STUDY_GROUP_NAME");
			
			return new CGGStudyGroup(new Integer(id),name);
		}
	}


}

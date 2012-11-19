
/*
 * Created on Jan 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import agtc.sampletracking.dao.*;
import org.springframework.dao.*;
/**
 * @author Jianan Xiao 2005-01-19
 *
 * Update the CGG system's result data with SampleTracking system's data 
 * 1.Clear the data in CGG's "result" table;
 * 2.Select all the test result in SampleTracking system;
 * 3.Insert into CGG's  "result" table one by one;
 * 3.1 If get exception, record it
 */
public class UpdateResultToCGGDAOJdbcImpl extends JdbcDaoSupport implements
	UpdateResultToCGGDAO{
	
	public String updateResult(){
		String sql;
		sql = "delete from cgg.result";
		getJdbcTemplate().execute(sql);
		
		sql ="select r.result_id,s.INT_SAMPLE_ID,a.name, r.result,r.study_group_id"
			+" from result r, assay a,sample s"
			+" where r.assay_id = a.assay_id and r.SAMPLE_ID = s.SAMPLE_ID"
			+" and r.study_group_id is not null";
		
		StringBuffer bf = new StringBuffer();
		
		getJdbcTemplate().query(sql,new UpdateResultHandler(bf));
		

		return bf.toString();
	}
	
	public class UpdateResultHandler implements RowCallbackHandler{
		private StringBuffer sf;
		UpdateResultHandler(StringBuffer sf){
			this.sf = sf;
			
		}
		public void processRow(ResultSet rs) throws SQLException{
			String id = ((Integer) new Integer(rs.getInt(0))).toString();
			String intSampleId = rs.getString(1);
			String assayName = rs.getString(2);
			String result = rs.getString(3);
			int studyGroupId = rs.getInt(4);
			
		
			String sql ="insert  into CGG.result(accession_id,sample_id,test,test_result,study_group_id)"
				+" values("
				+"'"+id+"','"+intSampleId+"','"+assayName+"','"+result
				+"',"+studyGroupId+")";
			try{
			getJdbcTemplate().execute(sql);
			} catch (DataAccessException se){
				se.printStackTrace();
				sf.append(intSampleId + "<br>");

			}
			if(sf.length()>0){
				sf.insert(0,"The following samples are not in CGG <br>");
			}
			return;			
		}
	}
}

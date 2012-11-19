/*
 * Created on Oct 5, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.dao;

import java.util.*;
import agtc.sampletracking.model.*;
/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface ResultDAO {
	public List getResults(List crList,List lgcList);
	public List getResults(String sql);
	public List getResultsByRunId(int runId);
	public Result getResult(Integer resultId);
	public void saveResult(Result result);
	public void removeResult(Integer resultId); 
	public void removeResultByAssay(Integer runId, String[] assays);
	public void refreshResult(Result result);
}

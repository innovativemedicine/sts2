/*
 * Created on Jan 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao;
import agtc.sampletracking.model.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface CGGResultDAO {
	public void saveResultToCGG(Result result, int studyGroupId);
	public void removeResultFromCGG(Integer resultId); 
}

/*
 * Created on Jan 7, 2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.sampletracking.dao;
import agtc.sampletracking.model.*;

/**
 * @author Hongjing
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface CGGResultDAO {
	public void saveResultToCGG(Result result, int studyGroupId);
	public void removeResultFromCGG(Integer resultId); 
}

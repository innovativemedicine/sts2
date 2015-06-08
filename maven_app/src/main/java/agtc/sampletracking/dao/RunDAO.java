/*
 * Created on Dec 3, 2004
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
public interface RunDAO {
	public List getRuns();
	public List getRuns(List crtList,List lgcList);
	public Run getRun(Integer runId);
	public void saveRun(Run run);
	public void removeRun(Integer runId); 
}

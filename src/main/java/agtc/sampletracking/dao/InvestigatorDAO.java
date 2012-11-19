/*
 * Created on Sep 30, 2004
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

public interface InvestigatorDAO {
	public List getInvestigators();
	public Investigator getInvestigator(Integer investigatorId);
	public void saveInvestigator(Investigator investigator) throws Exception;
	public void removeInvestigator(Integer investigatorId); 
}

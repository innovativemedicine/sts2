/*
 * Created on Oct 1, 2004
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
public interface ReagentDAO {
	public List getReagents();
	public Reagent getReagent(Integer reagentId);
	public void saveReagent(Reagent reagent) throws Exception;
	public void removeReagent(Integer reagentId); 
	public List getReagents(List crtList,List lgcList);
}

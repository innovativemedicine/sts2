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
public interface AssayDAO {
	public List getAssays();
	public List getAssays(Integer testId);
	public List getAssays(List crtList,List lgcList);
	public Assay getAssay(Integer assayId);
	public Assay getAssayByName(String name);
	public List getAssayByRunId(Integer id);
	public void saveAssay(Assay assay) throws Exception;
	public void removeAssay(Integer assayId); 
}


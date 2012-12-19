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
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */

public interface ContainerDAO {
	public List getContainers();

	public Container getContainer(Integer stockId);

	public Container getContainer(String name);

	public String getLargestPlateId(String platePrefix);

	public void saveContainer(Container stockContainer) throws Exception;

	public void removeContainer(Integer stockId);

	public List getContainers(List crtList, List lgcList);

	public List getAllPlates();

	public List getAllBoxes();

	public List<Container> simpleSearchContainers(String containerIdFrom,
			String containerIdTo, String extIdFrom, String extIdTo,
			List containerTypeIds, List projectIds);

	public List<Container> simpleSearchContainers(List plateIds,
			List plateTypeIds, List projectIds);

}

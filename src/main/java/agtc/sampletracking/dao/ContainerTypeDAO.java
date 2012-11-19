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

public interface ContainerTypeDAO {
	public List getContainerTypes();
	public ContainerType getContainerType(Integer containerTypeId);
	public ContainerType getContainerType(String name);
	public void saveContainerType(ContainerType containerType) throws Exception;
	public void removeContainerType(Integer containerTypeId); 
}

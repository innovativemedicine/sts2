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

public interface LocationDAO {
	public List Locations();
	public Location getLocation(Integer locationId);
	public Location getLocation(String name);
	public void saveLocation(Location location) throws Exception;
	public void removeLocation(Integer locationId); 
}

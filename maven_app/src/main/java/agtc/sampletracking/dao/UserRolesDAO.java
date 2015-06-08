package agtc.sampletracking.dao;
/*
 * Created on Oct 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
 

import java.util.*;
import agtc.sampletracking.model.*;
/**
 * @author Gloria Deng
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface UserRolesDAO {
	public List getUserRoles();
	public UserRoles getUserRoles(Integer userRoleId);
	public void saveUserRoles(UserRoles userRoles);
	public void removeUserRoles(UserRoles userRoles); 
	public void deleteUserRoles(Integer userid);
	public List findUserRoles(User user);
}

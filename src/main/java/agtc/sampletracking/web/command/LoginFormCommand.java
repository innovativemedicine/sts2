/*
 * Created on Oct 12, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package agtc.sampletracking.web.command;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginFormCommand {
	private Log log = LogFactory.getLog(LoginFormCommand.class);
	private String userId;
	private String providedPwd;
	
	
	public String getProvidedPwd() {
		return providedPwd;
	}

	public String getUserId() {
		return userId;
	}

	public void setProvidedPwd(String string) {
		providedPwd = string;
	}

	public void setUserId(String string) {
		userId = string;
	}

}

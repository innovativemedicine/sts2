package agtc.sampletracking.web;

import java.util.List;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.ConfigAttribute;
import net.sf.acegisecurity.ConfigAttributeDefinition;
import net.sf.acegisecurity.GrantedAuthority;

public class STSRoleVote implements net.sf.acegisecurity.vote.AccessDecisionVoter {
	public boolean supports(ConfigAttribute attribute){
		return true;
	}
	public boolean supports(Class clazz){
		return true;
	}
	public int vote(Authentication authentication, Object object,
	ConfigAttributeDefinition config){
		GrantedAuthority[] roles = authentication.getAuthorities();
		System.out.println(roles[0].getAuthority());
		return ACCESS_GRANTED;
	}

}

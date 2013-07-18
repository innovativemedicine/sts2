package agtc.sampletracking.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.GrantedAuthorityImpl;
import net.sf.acegisecurity.UserDetails;


/** 
 *        @hibernate.class
 *         table="USERS"
 *     
*/
public class User implements Serializable,UserDetails  {

    /** identifier field */
    private Integer userId;

    private String loginname;
	private String password;
	private String activity;
	private String firstName;
	private String lastName;

	// collections
	private Set userRoles;
	private Set ROLESs;

    /** default constructor */
    public User() {
    }

    //methods for UserDetails interface
    //Jianan Xiao 2005-09-22
    public GrantedAuthority[] getAuthorities(){
    	int role_legnth = ROLESs.size();
    	GrantedAuthority[] ag = 
    		new GrantedAuthority[role_legnth];
    	int i =0;
    	Iterator it = ROLESs.iterator();
    	while(it.hasNext()){
    		Roles r = (Roles) it.next();
    		GrantedAuthorityImpl ga = 
    			new GrantedAuthorityImpl("ROLE_"+r.getName());
    		ag[i]= ga;
    		i++;
    	}
    	return ag;
    }
    
    public String getUsername(){
    	return getLoginname();
    }
    
    public boolean isAccountNonExpired(){
    	boolean b=(activity.equals("on"));
    	return b;
    }
    public boolean isAccountNonLocked(){
    	boolean b=(activity.equals("on"));
    	return b;    	
    }
    public boolean isCredentialsNonExpired(){
    	boolean b=(activity.equals("on"));
    	return b;    	
    }
    public boolean isEnabled(){
    	boolean b=(activity.equals("on"));
    	return b;    	
    }
    
    //  methods for UserDetails interface END
    
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public Set getROLESs() {
		return ROLESs;
	}

	public void setROLESs(Set ss) {
		ROLESs = ss;
	}

	
	public Set getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}
 
}

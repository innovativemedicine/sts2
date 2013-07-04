package agtc.sampletracking.model;

import java.io.Serializable;
import java.util.Iterator;

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

    private java.lang.String loginname;
	private java.lang.String password;
	private java.lang.String activity;
	private java.lang.String firstName;
	private java.lang.String lastName;

	// collections
	private java.util.Set userRoles;
	private java.util.Set ROLESs;

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
    
    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="long"
     *             column="USER_ID"
     *         
     */
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    /** 
     *            @hibernate.property
     *             column="PASSWORD"
     *             length="16"
     *             not-null="true"
     *         
     */
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public java.lang.String getActivity() {
		return activity;
	}

	public void setActivity(java.lang.String activity) {
		this.activity = activity;
	}

	public java.lang.String getFirstName() {
		return firstName;
	}

	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	public java.lang.String getLastName() {
		return lastName;
	}

	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	public java.lang.String getLoginname() {
		return loginname;
	}

	public void setLoginname(java.lang.String loginname) {
		this.loginname = loginname;
	}


	public java.util.Set getROLESs() {
		return ROLESs;
	}

	public void setROLESs(java.util.Set ss) {
		ROLESs = ss;
	}

	public java.util.Set getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(java.util.Set userRoles) {
		this.userRoles = userRoles;
	}
 
}

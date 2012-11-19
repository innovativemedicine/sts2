package agtc.sampletracking.model;

import java.io.Serializable;
import java.util.Iterator;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.GrantedAuthorityImpl;
import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.jaas.AuthorityGranter;


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
	private java.lang.Integer reference;
	private java.lang.String activity;
	private java.lang.String firstName;
	private java.lang.String lastName;
	private java.lang.String address;
	private java.lang.String homePhone;
	private java.lang.String officePhone;
	private java.lang.String emailAddress;

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

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(java.lang.String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public java.lang.String getFirstName() {
		return firstName;
	}

	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	public java.lang.String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(java.lang.String homePhone) {
		this.homePhone = homePhone;
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

	public java.lang.String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(java.lang.String officePhone) {
		this.officePhone = officePhone;
	}

	public java.lang.Integer getReference() {
		return reference;
	}

	public void setReference(java.lang.Integer reference) {
		this.reference = reference;
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

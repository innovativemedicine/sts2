package agtc.sampletracking.model;

import java.io.Serializable;

public class UserRoles implements Serializable{
	//primary key
	private Integer id;
	
	//many to one
	private Roles role;
	private User  user;
	
	//default construct
	public UserRoles(){
		this.id=new Integer(-1);
	}
	
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
}

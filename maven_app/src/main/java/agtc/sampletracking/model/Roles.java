package agtc.sampletracking.model;

import java.io.Serializable;
import java.util.Set;

public class Roles implements Serializable{
//	 primary key
	private Integer id;

	// fields
	private String name;
	private String description;
	private String note;

	// collections
	private Set userRoles;;
	
	public String toString(){
		return name;
	}

	public Set getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	 
}

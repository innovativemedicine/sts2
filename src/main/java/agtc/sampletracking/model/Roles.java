package agtc.sampletracking.model;

import java.io.Serializable;

public class Roles implements Serializable{
//	 primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String note;

	// collections
	private java.util.Set userRoles;;

	public java.util.Set getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(java.util.Set userRoles) {
		this.userRoles = userRoles;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	 
}

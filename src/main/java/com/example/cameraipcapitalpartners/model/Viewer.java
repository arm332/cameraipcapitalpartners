package com.example.cameraipcapitalpartners.model;

import com.example.cameraipcapitalpartners.Constant;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Viewer {
	@Parent public Key<Project> project;
	@Id public String sessionid;
	public String email;
	
	public Viewer() {
		this.project = Key.create(Project.class, Constant.PROJECT_NAME);
	}

	public Viewer(String sessionid, String email) {
		this();
		this.sessionid = sessionid;
		this.email = email;
	}

	public Key<Project> getProject() {
		return project;
	}

	public void setProject(Key<Project> project) {
		this.project = project;
	}

	public String getSessionid() {
		return sessionid;
	}
	
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}


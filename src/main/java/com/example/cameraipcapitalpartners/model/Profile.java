package com.example.cameraipcapitalpartners.model;

import com.example.cameraipcapitalpartners.Constant;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindex;

@Entity
@Unindex
public class Profile {
	@Parent public Key<Project> project;
	@Id public String email;
	public String name;
	public Integer status;
	
	public Profile() {
		this.project = Key.create(Project.class, Constant.PROJECT_NAME);
	}

	public Profile(String email, String name, Integer status) {
		this();
		this.email = email;
		this.name = name;
		this.status = status;
	}

	public Key<Project> getProject() {
		return project;
	}

	public void setProject(Key<Project> project) {
		this.project = project;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

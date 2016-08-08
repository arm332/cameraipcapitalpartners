package com.invprof.cameras.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindex;
import com.invprof.cameras.Constant;

@Entity
@Unindex
public class Acme {
	@Parent public Key<Project> project;
	@Id public Long id;
	public String domain;
	public String token;
	public String auth;
	
	public Acme() {
		this.project = Key.create(Project.class, Constant.PROJECT_NAME);
	}
	
	public Acme(Long id) {
		this();
		this.id = id;
	}
	
	public Acme(Long id, String domain, String token, String auth) {
		this();
		this.id = id;
		this.domain = domain;
		this.token = token;
		this.auth = auth;
	}

	public Key<Project> getProject() {
		return project;
	}

	public void setProject(Key<Project> project) {
		this.project = project;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acme other = (Acme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
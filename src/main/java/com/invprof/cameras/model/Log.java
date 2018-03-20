package com.invprof.cameras.model;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindex;
import com.invprof.cameras.Constant;

@Entity
@Unindex
public class Log {
	@Parent public Key<Project> project;
	@Id public Long id;
	@Index public Date date;
	public String email;
	public String notes;
	
	public Log() {
		this.project = Key.create(Project.class, Constant.PROJECT_NAME);
	}
	
	public Log(Long id) {
		this();
		this.id = id;
	}
	
	public Log(Long id, Date date, String email, String notes) {
		this();
		this.id = id;
		this.date = date;
		this.email = email;
		this.notes = notes;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		Log other = (Log) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

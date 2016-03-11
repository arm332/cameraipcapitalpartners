package com.example.cameraipcapitalpartners.model;

import com.example.cameraipcapitalpartners.Constant;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindex;

@Entity
@Unindex
public class Camera {
	@Parent public Key<Project> project;
	@Id public Long id;
	public String title;
	public String description;
	public String url;
	public Integer interval;
	@Index public Integer position;
	
	public Camera() {
		this.project = Key.create(Project.class, Constant.PROJECT_NAME);
	}
	
	public Camera(Long id) {
		this();
		this.id = id;
	}
	
	public Camera(Long id, String title, String description, String url, Integer interval, Integer position) {
		this();
		this.id = id;
		this.title = title;
		this.description = description;
		this.url = url;
		this.interval = interval;
		this.position = position;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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
		Camera other = (Camera) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
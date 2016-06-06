package com.invprof.cameras.repository;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.invprof.cameras.Constant;
import com.invprof.cameras.model.Camera;
import com.invprof.cameras.model.Project;

public class CameraRepositoryImpl implements CameraRepository {
	@Override
	public List<Camera> list() {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Camera.class).ancestor(parent).order("position").list();
	}

	@Override
	public Camera load(Long id) {
		if(id == null) return new Camera();
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Camera.class).parent(parent).id(id).now();
	}

	@Override
	public Long save(Camera item) {
		Key<Camera> key = ObjectifyService.ofy().save().entity(item).now();
		return key.getId();
	}

	@Override
	public void delete(Long id) {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		ObjectifyService.ofy().delete().type(Camera.class).parent(parent).id(id).now();
	}
}

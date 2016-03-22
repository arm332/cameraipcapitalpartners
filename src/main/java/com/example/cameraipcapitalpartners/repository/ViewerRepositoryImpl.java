package com.example.cameraipcapitalpartners.repository;

import java.util.List;

import com.example.cameraipcapitalpartners.Constant;
import com.example.cameraipcapitalpartners.model.Project;
import com.example.cameraipcapitalpartners.model.Viewer;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class ViewerRepositoryImpl implements ViewerRepository {

	@Override
	public List<Viewer> list() {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Viewer.class).ancestor(parent).order("__key__").list();
	}

	@Override
	public Viewer load(String id) {
		if(id == null) return new Viewer();
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Viewer.class).parent(parent).id(id).now();
	}

	@Override
	public Long save(Viewer item) {
		Key<Viewer> key = ObjectifyService.ofy().save().entity(item).now();
		return key.getId();
	}

	@Override
	public void delete(String id) {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		ObjectifyService.ofy().delete().type(Viewer.class).parent(parent).id(id).now();
	}

}

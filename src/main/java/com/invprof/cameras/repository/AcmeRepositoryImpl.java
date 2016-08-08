package com.invprof.cameras.repository;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.invprof.cameras.Constant;
import com.invprof.cameras.model.Acme;
import com.invprof.cameras.model.Project;

public class AcmeRepositoryImpl implements AcmeRepository {
	@Override
	public List<Acme> list() {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Acme.class).ancestor(parent).list();
	}

	@Override
	public Acme load(Long id) {
		if(id == null) return new Acme();
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Acme.class).parent(parent).id(id).now();
	}

	@Override
	public Long save(Acme item) {
		Key<Acme> key = ObjectifyService.ofy().save().entity(item).now();
		return key.getId();
	}

	@Override
	public void delete(Long id) {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		ObjectifyService.ofy().delete().type(Acme.class).parent(parent).id(id).now();
	}
}

package com.invprof.cameras.repository;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.invprof.cameras.Constant;
import com.invprof.cameras.model.Log;
import com.invprof.cameras.model.Project;

public class LogRepositoryImpl implements LogRepository {

	@Override
	public List<Log> list() {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Log.class).ancestor(parent).list();
	}

	@Override
	public Log load(Long id) {
		if(id == null) return new Log();
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Log.class).parent(parent).id(id).now();
	}

	@Override
	public Long save(Log item) {
		Key<Log> key = ObjectifyService.ofy().save().entity(item).now();
		return key.getId();
	}

	@Override
	public void delete(Long id) {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		ObjectifyService.ofy().delete().type(Log.class).parent(parent).id(id).now();
	}

}

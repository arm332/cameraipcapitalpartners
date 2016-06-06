package com.invprof.cameras.repository;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.invprof.cameras.Constant;
import com.invprof.cameras.model.Profile;
import com.invprof.cameras.model.Project;

public class ProfileRepositoryImpl implements ProfileRepository {
	@Override
	public List<Profile> list() {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Profile.class).ancestor(parent).order("__key__").list();
	}

	@Override
	public Profile load(String email) {
		if(email == null) return new Profile();
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		//Key<Profile> key = Key.create(parent, Profile.class, email);
		//return ObjectifyService.ofy().load().key(key).now();
		return ObjectifyService.ofy().load().type(Profile.class).parent(parent).id(email).now();
	}

	@Override
	public Long save(Profile item) {
		Key<Profile> key = ObjectifyService.ofy().save().entity(item).now();
		return key.getId();
	}

	@Override
	public void delete(String email) {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		//Key<Profile> key = Key.create(parent, Profile.class, email);
		//ObjectifyService.ofy().delete().key(key).now();
		ObjectifyService.ofy().delete().type(Profile.class).parent(parent).id(email).now();
	}

	//@Override
	//public Profile find(String name) {
	//	Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
	//	return ObjectifyService.ofy().load().type(Profile.class).ancestor(parent).filter("name", name).first().now();
	//}
}

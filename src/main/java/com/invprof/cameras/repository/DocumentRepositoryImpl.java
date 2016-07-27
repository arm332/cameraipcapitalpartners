package com.invprof.cameras.repository;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.invprof.cameras.Constant;
import com.invprof.cameras.model.Document;
import com.invprof.cameras.model.Project;

public class DocumentRepositoryImpl implements DocumentRepository {
	@Override
	public List<Document> list() {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Document.class).ancestor(parent).order("position").list();
	}

	@Override
	public Document load(Long id) {
		if(id == null) return new Document();
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		return ObjectifyService.ofy().load().type(Document.class).parent(parent).id(id).now();
	}

	@Override
	public Long save(Document item) {
		Key<Document> key = ObjectifyService.ofy().save().entity(item).now();
		return key.getId();
	}

	@Override
	public void delete(Long id) {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
		ObjectifyService.ofy().delete().type(Document.class).parent(parent).id(id).now();
	}
}

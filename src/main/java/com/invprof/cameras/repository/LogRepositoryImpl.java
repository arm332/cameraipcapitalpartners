package com.invprof.cameras.repository;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.invprof.cameras.Constant;
import com.invprof.cameras.model.Log;
import com.invprof.cameras.model.Project;

public class LogRepositoryImpl implements LogRepository {

	@Override
	public List<Log> list(Date start, Date end, Integer limit, Integer offset, String order) {
		Key<Project> parent = Key.create(Project.class, Constant.PROJECT_NAME);
//		return ObjectifyService.ofy().load().type(Log.class).ancestor(parent)
//				.filter("date >=", start).filter("date <=", end)
//				.limit(limit).offset(offset).order("-date").list();
		Query<Log> qry = ObjectifyService.ofy().load().type(Log.class).ancestor(parent);
		if (start != null) qry = qry.filter("date >=", start);
		if (end != null) qry = qry.filter("date <=", end);
		if (limit != null) qry = qry.limit(limit);
		if (offset != null) qry = qry.offset(offset);
		if (order != null) qry = qry.order(order);
		return qry.list();
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

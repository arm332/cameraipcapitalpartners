package com.invprof.cameras.service;

import java.util.Date;
import java.util.List;

import com.invprof.cameras.model.Log;

public interface LogService {
	List<Log> list(Date start, Date end, Integer limit, Integer offset, String order);
	Log load(Long id);
	Long save(Log item);
	void delete(Long id);
}

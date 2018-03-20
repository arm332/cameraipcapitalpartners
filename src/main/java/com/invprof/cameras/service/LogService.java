package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Log;

public interface LogService {
	List<Log> list();
	Log load(Long id);
	Long save(Log item);
	void delete(Long id);
}

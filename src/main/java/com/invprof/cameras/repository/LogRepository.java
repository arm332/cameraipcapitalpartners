package com.invprof.cameras.repository;

import java.util.List;

import com.invprof.cameras.model.Log;

public interface LogRepository {
	List<Log> list(Integer limit, Integer offset);
	Log load(Long id);
	Long save(Log item);
	void delete(Long id);
}

package com.invprof.cameras.service;

import java.util.Date;
import java.util.List;

import com.invprof.cameras.model.Log;
import com.invprof.cameras.repository.LogRepository;
import com.invprof.cameras.repository.LogRepositoryImpl;

public class LogServiceImpl implements LogService {
	private LogRepository repository = new LogRepositoryImpl();
	
	@Override
	public List<Log> list(Date start, Date end, Integer limit, Integer offset, String order) {
		return repository.list(start, end, limit, offset, order);
	}

	@Override
	public Log load(Long id) {
		return repository.load(id);
	}

	@Override
	public Long save(Log item) {
		return repository.save(item);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}

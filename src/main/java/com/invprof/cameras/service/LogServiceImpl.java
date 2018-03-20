package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Log;
import com.invprof.cameras.repository.LogRepository;
import com.invprof.cameras.repository.LogRepositoryImpl;

public class LogServiceImpl implements LogService {
	private LogRepository repository = new LogRepositoryImpl();
	
	@Override
	public List<Log> list() {
		return repository.list();
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

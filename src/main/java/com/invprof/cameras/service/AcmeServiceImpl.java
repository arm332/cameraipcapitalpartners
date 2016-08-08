package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Acme;
import com.invprof.cameras.repository.AcmeRepository;
import com.invprof.cameras.repository.AcmeRepositoryImpl;

public class AcmeServiceImpl implements AcmeService {
	private AcmeRepository repository = new AcmeRepositoryImpl();
	
	@Override
	public List<Acme> list() {
		return repository.list();
	}

	@Override
	public Acme load(Long id) {
		return repository.load(id);
	}

	@Override
	public Long save(Acme item) {
		return repository.save(item);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}

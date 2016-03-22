package com.example.cameraipcapitalpartners.service;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Viewer;
import com.example.cameraipcapitalpartners.repository.ViewerRepository;
import com.example.cameraipcapitalpartners.repository.ViewerRepositoryImpl;

public class ViewerServiceImpl implements ViewerService {
	private ViewerRepository repository = new ViewerRepositoryImpl();
	
	@Override
	public List<Viewer> list() {
		return repository.list();
	}

	@Override
	public Viewer load(String id) {
		return repository.load(id);
	}

	@Override
	public Long save(Viewer item) {
		return repository.save(item);
	}

	@Override
	public void delete(String id) {
		repository.delete(id);
	}

}

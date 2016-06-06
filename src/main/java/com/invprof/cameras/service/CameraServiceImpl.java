package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Camera;
import com.invprof.cameras.repository.CameraRepository;
import com.invprof.cameras.repository.CameraRepositoryImpl;

public class CameraServiceImpl implements CameraService {
	private CameraRepository repository = new CameraRepositoryImpl();
	
	@Override
	public List<Camera> list() {
		return repository.list();
	}

	@Override
	public Camera load(Long id) {
		return repository.load(id);
	}

	@Override
	public Long save(Camera item) {
		return repository.save(item);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}

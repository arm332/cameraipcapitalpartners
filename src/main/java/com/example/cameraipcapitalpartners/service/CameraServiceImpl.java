package com.example.cameraipcapitalpartners.service;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Camera;
import com.example.cameraipcapitalpartners.repository.CameraRepository;
import com.example.cameraipcapitalpartners.repository.CameraRepositoryImpl;

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

package com.example.cameraipcapitalpartners.repository;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Camera;

public interface CameraRepository {
	List<Camera> list();
	Camera load(Long id);
	Long save(Camera item);
	void delete(Long id);
}

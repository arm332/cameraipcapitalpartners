package com.example.cameraipcapitalpartners.service;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Camera;

public interface CameraService {
	List<Camera> list();
	Camera load(Long id);
	Long save(Camera item);
	void delete(Long id);
}

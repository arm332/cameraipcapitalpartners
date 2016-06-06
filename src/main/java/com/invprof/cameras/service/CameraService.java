package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Camera;

public interface CameraService {
	List<Camera> list();
	Camera load(Long id);
	Long save(Camera item);
	void delete(Long id);
}

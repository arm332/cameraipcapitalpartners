package com.invprof.cameras.repository;

import java.util.List;

import com.invprof.cameras.model.Camera;

public interface CameraRepository {
	List<Camera> list();
	Camera load(Long id);
	Long save(Camera item);
	void delete(Long id);
}

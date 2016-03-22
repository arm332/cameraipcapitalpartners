package com.example.cameraipcapitalpartners.service;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Viewer;

public interface ViewerService {
	List<Viewer> list();
	Viewer load(String id);
	Long save(Viewer item);
	void delete(String id);
}

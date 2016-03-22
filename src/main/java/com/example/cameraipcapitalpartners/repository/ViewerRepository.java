package com.example.cameraipcapitalpartners.repository;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Viewer;

public interface ViewerRepository {
	List<Viewer> list();
	Viewer load(String id);
	Long save(Viewer item);
	void delete(String id);
}

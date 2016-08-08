package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Acme;

public interface AcmeService {
	List<Acme> list();
	Acme load(Long id);
	Long save(Acme item);
	void delete(Long id);
}

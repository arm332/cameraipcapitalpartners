package com.invprof.cameras.repository;

import java.util.List;

import com.invprof.cameras.model.Acme;

public interface AcmeRepository {
	List<Acme> list();
	Acme load(Long id);
	Long save(Acme item);
	void delete(Long id);
}

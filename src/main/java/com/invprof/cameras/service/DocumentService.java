package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Document;

public interface DocumentService {
	List<Document> list();
	Document load(Long id);
	Long save(Document item);
	void delete(Long id);
}

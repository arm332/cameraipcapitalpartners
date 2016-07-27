package com.invprof.cameras.repository;

import java.util.List;

import com.invprof.cameras.model.Document;

public interface DocumentRepository {
	List<Document> list();
	Document load(Long id);
	Long save(Document item);
	void delete(Long id);
}

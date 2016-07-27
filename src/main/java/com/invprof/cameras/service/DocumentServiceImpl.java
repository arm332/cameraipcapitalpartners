package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Document;
import com.invprof.cameras.repository.DocumentRepository;
import com.invprof.cameras.repository.DocumentRepositoryImpl;

public class DocumentServiceImpl implements DocumentService {
	private DocumentRepository repository = new DocumentRepositoryImpl();
	
	@Override
	public List<Document> list() {
		return repository.list();
	}

	@Override
	public Document load(Long id) {
		return repository.load(id);
	}

	@Override
	public Long save(Document item) {
		return repository.save(item);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}

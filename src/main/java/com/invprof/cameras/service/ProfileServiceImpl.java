package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Profile;
import com.invprof.cameras.repository.ProfileRepository;
import com.invprof.cameras.repository.ProfileRepositoryImpl;

public class ProfileServiceImpl implements ProfileService {
	private ProfileRepository repository = new ProfileRepositoryImpl();

	@Override
	public List<Profile> list() {
		return repository.list();
	}

	@Override
	public Profile load(String email) {
		return repository.load(email);
	}

	@Override
	public Long save(Profile item) {
		return repository.save(item);
	}

	@Override
	public void delete(String email) {
		repository.delete(email);
	}
}

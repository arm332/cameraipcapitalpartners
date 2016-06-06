package com.invprof.cameras.service;

import java.util.List;

import com.invprof.cameras.model.Profile;

public interface ProfileService {
	List<Profile> list();
	Profile load(String email);
	Long save(Profile item);
	void delete(String email);
}

package com.invprof.cameras.repository;

import java.util.List;

import com.invprof.cameras.model.Profile;

public interface ProfileRepository {
	List<Profile> list();
	Profile load(String email);
	Long save(Profile item);
	void delete(String email);
}

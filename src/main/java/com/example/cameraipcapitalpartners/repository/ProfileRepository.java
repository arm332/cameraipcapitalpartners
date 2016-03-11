package com.example.cameraipcapitalpartners.repository;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Profile;

public interface ProfileRepository {
	List<Profile> list();
	Profile load(String email);
	Long save(Profile item);
	void delete(String email);
}

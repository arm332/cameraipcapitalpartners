package com.example.cameraipcapitalpartners.service;

import java.util.List;

import com.example.cameraipcapitalpartners.model.Profile;

public interface ProfileService {
	List<Profile> list();
	Profile load(String email);
	Long save(Profile item);
	void delete(String email);
}

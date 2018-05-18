package com.qa.service;

import javax.inject.Inject;

import com.qa.repository.IUserRepository;

public class UserService implements IUserService {
	
	@Inject
    private IUserRepository userRepository;

	@Override
	public String addAdmin(String admin) {
		return userRepository.addAdmin(admin);
	}
}

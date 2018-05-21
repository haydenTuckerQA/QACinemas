package com.qa.service;

import javax.inject.Inject;

import com.qa.repository.IUserRepository;

public class UserService implements IUserService {
	
	@Inject
    private IUserRepository userRepository;

	public String addAdmin(String admin) {
		return userRepository.addAdmin(admin);
	}

	public String deleteAdmin(String username) {
		return userRepository.deleteAdmin(username);
	}

	public String getAdmin(String username) {
		return userRepository.getAdmin(username);
	}

	public String getAllAdmins() {
		return userRepository.getAllAdmins();
	}
}

package com.qa.service;

public interface IUserService {
	String addAdmin(String admin);
	String deleteAdmin(String username);
	String getAdmin(String username);
	String getAllAdmins();
}

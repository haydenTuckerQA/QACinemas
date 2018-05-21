package com.qa.repository;

public interface IUserRepository {
	String addAdmin(String admin);
	String deleteAdmin(String username);
	String getAdmin(String username);
	String getAllAdmins();
}

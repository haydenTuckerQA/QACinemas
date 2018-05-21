package com.qa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Role;
import com.qa.domain.User;
import com.qa.repository.UserRepository;
import com.qa.utility.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private TypedQuery<User> query;
	private JSONUtil util;
	
	private User user;
	private List<User> users;
	
	private static final String MOCK_DATA_ARRAY = "[{\"username\":\"User\",\"password\":\"password\",\"role\":\"USER\"}]";
	private static final String MOCK_OBJECT = "{\"username\":\"User\",\"password\":\"password\",\"role\":\"USER\"}";

	@Before
	public void setup() {
		user = new User("User", "password", Role.USER);
		users = new ArrayList<User>();
		users.add(user);
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testCreateAdmin() {
		String reply = repo.addAdmin(MOCK_OBJECT);
		Assert.assertEquals("{\"message\": \"admin sucessfully added\"}", reply);
	}

	@Test
	public void testDeleteAdmin() {
		Mockito.when(manager.createQuery(Mockito.anyString(), Mockito.eq(User.class))).thenReturn(query);
		String expectedValue = "null";
		String actualValue = repo.deleteAdmin("User");
		Assert.assertEquals(expectedValue, actualValue);
		
		Mockito.when(query.getSingleResult()).thenReturn(user);
		
		expectedValue = MOCK_OBJECT;
		actualValue = repo.deleteAdmin("User");
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testGetAllAdmins() {
		Mockito.when(manager.createQuery(Mockito.anyString(), Mockito.eq(User.class))).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(users);
		
		String expectedValue = MOCK_DATA_ARRAY;
		String actualValue =  repo.getAllAdmins();
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testGetAdmin() {
		Mockito.when(manager.createQuery(Mockito.anyString(), Mockito.eq(User.class))).thenReturn(query);
		String expectedValue = "null";
		String actualValue = repo.getAdmin("User");
		Assert.assertEquals(expectedValue, actualValue);
		
		Mockito.when(query.getSingleResult()).thenReturn(user);
		expectedValue = MOCK_OBJECT;
		actualValue = repo.getAdmin("User");
		Assert.assertEquals(expectedValue, actualValue);
	}
}

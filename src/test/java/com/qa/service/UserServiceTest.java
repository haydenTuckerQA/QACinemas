package com.qa.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.repository.UserRepository;
import com.qa.utility.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;
	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"id\":1,\"username\":\"User\",\"password\":\"password\",\"role\":\"User\"}]";

	private static final String MOCK_OBJECT = "{\"id\":1,\"username\":\"User\",\"password\":\"password\",\"role\":\"User\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testCreateAdmin() {
		String reply = repo.addAdmin(MOCK_OBJECT);
		Assert.assertEquals("{\"message\": \"admin sucessfully added\"}", reply);
	}
}

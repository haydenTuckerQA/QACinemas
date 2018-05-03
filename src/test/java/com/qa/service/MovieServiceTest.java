package com.qa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Movie;
import com.qa.utility.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

	@InjectMocks
	private MovieService repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"id\":1,\"firstName\":\"Johny\",\"lastName\":\"Bloggs\",\"accountNumber\":\"1234\"}]";

	private static final String MOCK_OBJECT = "{\"title\":\"John\",\"genre\":\"action\",\"ratingr\":\"18\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	@Test
	public void testCreateAccount() {
		String reply = repo.addMovie(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"movie sucessfully added\"}");
	}
	
	

}
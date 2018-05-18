package com.qa.service;

import com.qa.domain.Tickets;
import com.qa.repository.TicketsRepository;
import com.qa.utility.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TicketsServiceTest {

	@InjectMocks
	private TicketsRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"id\":1,\"movieID\": 1 ,\"dayShowing\":\"27/05\",\"hourShowing\":\"17:30\",\"typeShowing\":\"iMax\",\"screening\":1, \"seats\":100,\"disabledSeats\":5}\"]";

	private static final String MOCK_OBJECT = "{\"movieID\": 1 ,\"dayShowing\":\"27/05\",\"hourShowing\":\"17:30\",\"typeShowing\":\"iMax\",\"screening\":1, \"seats\":100,\"disabledSeats\":5}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testCreateShowing() {
		String reply = repo.addShowing(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"Showing sucessfully added\"}");
	}
	
	@Test
	public void testDeleteShowing()
	{
		Mockito.when(repo.findShowing((long) 1)).thenReturn(util.getObjectForJSON("{\"movieID\": 1 ,\"dayShowing\":\"27/05\",\"hourShowing\":\"17:30\",\"typeShowing\":\"iMax\",\"screening\":1, \"seats\":100,\"disabledSeats\":5}", Tickets.class));
		String reply = repo.removeShowing(1);
		Assert.assertEquals(reply, "{\"message\": \"Showing sucessfully deleted\"}");
	
		Mockito.when(repo.findShowing((long) 1)).thenReturn(null);
		reply = repo.removeShowing(1);
		Assert.assertEquals(reply, "{\"message\": \"Showing couldn't be deleted\"}");
	}
	
	@Test
	public void testGetAllShowings()
	{
		
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Tickets> Tickets = new ArrayList<Tickets>();
		Tickets.add(util.getObjectForJSON("{\"id\":1,\"movieID\": 1 ,\"dayShowing\":\"27/05\",\"hourShowing\":\"17:30\",\"typeShowing\":\"iMax\",\"screening\":1, \"seats\":100,\"disabledSeats\":5}", Tickets.class));
		Mockito.when(query.getResultList()).thenReturn(Tickets);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllShowings((long)1));
	}

	

}
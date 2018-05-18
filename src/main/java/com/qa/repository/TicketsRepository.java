package com.qa.repository;

import com.qa.domain.Movie;
import com.qa.domain.Tickets;
import com.qa.utility.JSONUtil;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Model
@Default
public class TicketsRepository {

	@Inject
	private JSONUtil util;
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	@Transactional(REQUIRED)
	public String addShowing(String showing) {
		Tickets aShowing = util.getObjectForJSON(showing, Tickets.class);
		manager.persist(aShowing);      
		return "{\"message\": \"Showing sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String removeShowing(long id) {
		Tickets aShowing = findShowing(id);
		if(aShowing!=null)
		{
			manager.remove(aShowing);
			return "{\"message\": \"Showing sucessfully deleted\"}";
		}
		else
			return "{\"message\": \"Showing couldn't be deleted\"}";
	}
	
	public String getAllShowings(Long movieID) {
		Query query = manager.createQuery("Select m FROM Tickets m WHERE m.movieID = " + movieID);
		Collection<Tickets> Tickets = (Collection<Tickets>) query.getResultList();
		return util.getJSONForObject(Tickets);
	}
	
	public String buyTicket(long id, String ticket)
	{
		Tickets aShowing = findShowing(id);
		if(aShowing!=null)
		{
			if(ticket.substring(0,1).equals("1"))
			{
				if(aShowing.getSeats()>=Integer.parseInt(ticket.substring(2)))
				{
					aShowing.setSeats(aShowing.getSeats()-Integer.parseInt(ticket.substring(2)));
					return "{\"message\": \"Ticket sucessfully bought\"}";
				}
				else
					return "{\"message\": \"Not Enough Tickets\"}";
			}
			else
			{
				if(aShowing.getDisabledSeats()>=Integer.parseInt(ticket.substring(2)))
				{
					aShowing.setDisabledSeats(aShowing.getDisabledSeats()-Integer.parseInt(ticket.substring(2)));
					return "{\"message\": \"Ticket sucessfully bought\"}";
				}
				else
					return "{\"message\": \"Not Enough Tickets\"}";
			}
		}
		return "{\"message\": \"Showing not in the system\"}";
	}
	
	public Tickets findShowing(long id) {
		return manager.find(Tickets.class, id);
	}

}

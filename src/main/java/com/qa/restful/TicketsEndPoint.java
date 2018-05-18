package com.qa.restful;
import com.qa.service.ITicketsService;

import javax.inject.Inject;
import javax.ws.rs.*;


@Path("/ticket")
public class TicketsEndPoint {

	@Inject
	private ITicketsService ticketsService;
	
	
	@GET
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String getAllShowings(@PathParam("id") Long movieID)
	{
		return ticketsService.getAllShowings(movieID);
	}
	
	
	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String addShowing(String showing)
	{
		return ticketsService.addShowing(showing);
	}
	
	@PUT
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String buyTickets(@PathParam("id") Long id, String ticket) {
		return ticketsService.buyTicket(id, ticket);
	}
	
	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String removeShowing(@PathParam("id") Long id)
	{
		return ticketsService.removeShowing(id);
	}
}

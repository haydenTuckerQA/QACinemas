package com.qa.integration;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.IMovie;


@Path("/movie")
public class MovieServiceEndPoint {

	@Inject
	private IMovie repo;
	
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllMovies()
	{
		return repo.getAllMovies();
	}
	
	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String addMovie(String account)
	{
		return repo.addMovie(account);
	}
	
	@PUT
	@Path("/json")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateMovie(String account) {
		return repo.updateMovie(account);
	}
	
	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String removeMovie(@PathParam("id") Long id)
	{
		return repo.removeMovie(id);
	}
}

package com.qa.restful;
import com.qa.service.IMovieService;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;


@Path("/movie")
@DeclareRoles({"ADMIN","USER"})
public class MovieEndPoint {

	@Inject
	private IMovieService movieService;
	
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllMovies()
	{
		return movieService.getAllMovies();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String getMovie(@PathParam("id") Long id)
	{
		return movieService.getMovie(id);
	}
	
	@POST
	@Path("/json")
	@Produces({ "application/json" })
	@RolesAllowed("ADMIN")
	public String addMovie(String movie)
	{
		return movieService.addMovie(movie);
	}
	
	@PUT
	@Path("/json")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@RolesAllowed("ADMIN")
	public String updateMovie(String movie) {
		return movieService.updateMovie(movie);
	}
	
	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	@RolesAllowed("ADMIN")
	public String removeMovie(@PathParam("id") Long id)
	{
		return movieService.removeMovie(id);
	}
}

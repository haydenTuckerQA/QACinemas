package com.qa.restful;
import com.qa.service.IMovieService;

import javax.inject.Inject;
import javax.ws.rs.*;


@Path("/movie")
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
	
	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String addMovie(String movie)
	{
		return movieService.addMovie(movie);
	}
	
	@PUT
	@Path("/json")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateMovie(String movie) {
		return movieService.updateMovie(movie);
	}
	
	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String removeMovie(@PathParam("id") Long id)
	{
		return movieService.removeMovie(id);
	}
}

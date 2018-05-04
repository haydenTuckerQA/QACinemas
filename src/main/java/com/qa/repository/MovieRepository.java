package com.qa.repository;

import com.qa.domain.Movie;
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
public class MovieRepository implements IMovieRepository {

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
	public String addMovie(String movie) {
		Movie aMovie = util.getObjectForJSON(movie, Movie.class);
		manager.persist(aMovie);      
		return "{\"message\": \"movie sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String removeMovie(Long id)
	{
		Movie aMovie = findMovie(id);
		if(aMovie!=null)
		{
			manager.remove(aMovie);
			return "{\"message\": \"movie sucessfully removed\"}";
		}
		else
			return "{\"message\": \"movie couldn't be removed\"}";
		
	}

	@Override
	public String getMovie(Long id) {
		Movie moveFound = findMovie(id);
		if(moveFound!=null)
		{
			return util.getJSONForObject(moveFound);
		}
		else
			return "{\"message\": \"movie be found!\"}";
	}

	public Movie findMovie(long id) {
		return manager.find(Movie.class, id);
	}

	@Transactional(REQUIRED)
	public String updateMovie(String movie)
	{
		Movie aMovie = util.getObjectForJSON(movie, Movie.class);
		Movie idMovie = findMovie(aMovie.getId());
		if(idMovie!=null)
		{
			idMovie = aMovie;
			manager.merge(idMovie);
			return "{\"message\": \"movie sucessfully updated\"}";
		}
		else
			return "{\"message\": \"movie couldn't be updated\"}";
		
	}
	public String getAllMovies() {
		Query query = manager.createQuery("Select m FROM Movie m");
		Collection<Movie> Movies = (Collection<Movie>) query.getResultList();
		return util.getJSONForObject(Movies);
	}
}

package com.qa.service;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import com.qa.domain.Movie;
import com.qa.utility.JSONUtil;

@Model
@Default
public class MovieService {

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

	public Movie findMovie(long id) {
		// TODO Auto-generated method stub
		return manager.find(Movie.class, id);
	}

	public String getAllMovies() {
		Query query = manager.createQuery("Select m FROM Movie m");
		Collection<Movie> accounts = (Collection<Movie>) query.getResultList();
		return util.getJSONForObject(accounts);
	}
}

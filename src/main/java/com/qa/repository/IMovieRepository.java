package com.qa.repository;
public interface IMovieRepository {
	String getAllMovies();
	String addMovie(String movie);
	String updateMovie(String movie);
	String removeMovie(Long id);
	String getMovie(Long id);
}

package com.qa.service;

public interface IMovieService {

    String getAllMovies();
    String addMovie(String movie);
    String updateMovie(String movie);
    String removeMovie(Long id);
    String getMovie(Long id);
}
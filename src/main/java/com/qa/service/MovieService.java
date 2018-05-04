package com.qa.service;

import com.qa.repository.IMovieRepository;
import com.qa.service.IMovieService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class MovieService implements IMovieService {

    @Inject
    private IMovieRepository movieRepository;

    @Override
    public String getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public String addMovie(String movie) {
        return movieRepository.addMovie(movie);
    }

    @Override
    public String updateMovie(String movie) {
        return movieRepository.updateMovie(movie);
    }

    @Override
    public String removeMovie(Long id) {
        return movieRepository.removeMovie(id);
    }

	@Override
	public String getMovie(Long id) {
		return movieRepository.getMovie(id);
	}
}
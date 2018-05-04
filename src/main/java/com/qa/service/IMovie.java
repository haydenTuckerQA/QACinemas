package com.qa.service;
import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.transaction.Transactional;
public interface IMovie {
	String getAllMovies();
	String addMovie(String movie);
	String updateMovie(String movie);
	String removeMovie(Long id);
	String getMovie(Long id);
}

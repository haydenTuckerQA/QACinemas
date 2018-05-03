package com.qa.service;
import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.transaction.Transactional;
public interface IMovie {
	String getAllMovies();
	String addMovie(String account);
	String updateMovie(String account);
	String removeMovie(Long id);
}

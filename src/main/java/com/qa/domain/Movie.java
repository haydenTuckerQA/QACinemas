package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Movie {
	
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String genre;
	private String rating;
	
	public Movie() {
		
	}
	
	public Movie(String title,String genre,String rating) {
		this.title=title;
		this.genre=genre;
		this.rating=rating;
	}

	public Long getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	
}
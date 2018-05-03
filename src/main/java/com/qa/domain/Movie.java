package com.qa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity 
public class Movie {
	
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String genre;
	@Enumerated(EnumType.STRING)
	private Rating rating;
	private String actors;
	private String description;
	private String imageURL;
	private String IMDBURL;
	private int runtime;
	
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getIMDBURL() {
		return IMDBURL;
	}

	public void setIMDBURL(String iMDBURL) {
		IMDBURL = iMDBURL;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}


	public Movie() {
		
	}
	
	public Movie(String title,String genre,String rating) {
		this.title=title;
		this.genre=genre;
		this.rating = Rating.valueOf(rating);
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
		return rating.toString();
	}

	public void setRating(String rating) {
		this.rating = Rating.valueOf(rating);
	}

	
}
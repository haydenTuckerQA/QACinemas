package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity 
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String genre;
	private String IMDBID;
	private String imgURL;

	public Movie() {
	}
	
	public Movie(String title,String genre,String IMDBID) {
		this.title=title;
		this.genre=genre;
		this.IMDBID = IMDBID;
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

	public String getIMDBID() {
		return IMDBID;
	}

	public void setIMDBID(String iMDBID) {
		IMDBID = iMDBID;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
}
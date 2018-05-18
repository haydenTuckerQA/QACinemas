package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tickets {

	@Id
	@GeneratedValue
	private Long id;
	private Long movieID;
	private String dayShowing;
	private String hourShowing;
	private String typeShowing;
	private int screening;
	private int seats;
	private int disabledSeats;
	
	public Tickets() {}
	
	public Tickets(Long movieID,String dayShowing,String hourShowing, String typeShowing,int screening, int seats, int disabledSeats)
	{
		this.movieID=movieID;
		this.dayShowing=dayShowing;
		this.hourShowing=hourShowing;
		this.typeShowing=typeShowing;
		this.screening=screening;
		this.seats=seats;
		this.disabledSeats=disabledSeats;
		
	}
	
	public Tickets(Long movieID,int screening,int seats, int disabledSeats)
	{
		this.movieID=movieID;
		this.screening=screening;
		this.seats=seats;
		this.disabledSeats=disabledSeats;
	}

	public Long getId() {
		return id;
	}

	public Long getMovieID() {
		return movieID;
	}

	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}

	public String getDayShowing() {
		return dayShowing;
	}

	public void setDayShowing(String dayShowing) {
		this.dayShowing = dayShowing;
	}

	public String getHourShowing() {
		return hourShowing;
	}

	public void setHourShowing(String hourShowing) {
		this.hourShowing = hourShowing;
	}

	public String getTypeShowing() {
		return typeShowing;
	}

	public void setTypeShowing(String typeShowing) {
		this.typeShowing = typeShowing;
	}

	public int getScreening() {
		return screening;
	}

	public void setScreening(int screening) {
		this.screening = screening;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getDisabledSeats() {
		return disabledSeats;
	}

	public void setDisabledSeats(int disabledSeats) {
		this.disabledSeats = disabledSeats;
	}
	
}

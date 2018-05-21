package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private String date;
    private Long movie_id;
    private int seats;
    private double price;

    public Booking() {

    }

    public Booking(String time, String date, Long movie_id, int seats, double price) {
        this.time = time;
        this.date = date;
        this.movie_id = movie_id;
        this.seats = seats;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public int getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

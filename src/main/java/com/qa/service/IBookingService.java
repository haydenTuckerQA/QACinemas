package com.qa.service;

public interface IBookingService {

    String getAllBookings();
    String addBooking(String Booking);
    String updateBooking(String Booking);
    String removeBooking(Long id);
    String getBooking(Long id);
}

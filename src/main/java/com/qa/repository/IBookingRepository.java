package com.qa.repository;

public interface IBookingRepository {

    String getAllBookings();
    String addBooking(String Booking);
    String updateBooking(String Booking);
    String removeBooking(Long id);
    String getBooking(Long id);
}

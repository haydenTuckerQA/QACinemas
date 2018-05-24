package com.qa.repository;

import com.qa.domain.Booking;

import javax.transaction.Transactional;

public interface IBookingRepository {

    String getAllBookings();
    String addBooking(String Booking);

    @Transactional(Transactional.TxType.REQUIRED)
    String addBooking(Booking booking);

    String updateBooking(String Booking);
    String removeBooking(Long id);
    String getBooking(Long id);
}

package com.qa.service;

import com.qa.repository.BookingRepository;

import javax.inject.Inject;

public class BookingService implements IBookingService {

    @Inject
    private BookingRepository repository;

    @Override
    public String getAllBookings() {
        return repository.getAllBookings();
    }

    @Override
    public String addBooking(String Booking) {
        return repository.addBooking(Booking);
    }

    @Override
    public String updateBooking(String Booking) {
        return repository.updateBooking(Booking);
    }

    @Override
    public String removeBooking(Long id) {
        return repository.removeBooking(id);
    }

    @Override
    public String getBooking(Long id) {
        return repository.getBooking(id);
    }
}

package com.qa.repository;

import com.qa.domain.Booking;
import com.qa.utility.JSONUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Transactional(Transactional.TxType.SUPPORTS)
public class BookingRepository implements IBookingRepository {

    @Inject
    private JSONUtil util;

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;


    @Override
    public String getAllBookings() {
        Query query = manager.createQuery("Select booking FROM Booking booking");
        Collection<Booking> bookings = (Collection<Booking>) query.getResultList();
        return util.getJSONForObject(bookings);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public String addBooking(String booking) {
        Booking newBooking = util.getObjectForJSON(booking, Booking.class);
        manager.persist(newBooking);
        return "{\"message\": \"Booking sucessfully added\"}";
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public String addBooking(Booking booking) {
        manager.persist(booking);
        return "{\"message\": \"Booking sucessfully added\"}";
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public String updateBooking(String booking) {
        Booking updatedBooking = util.getObjectForJSON(booking, Booking.class);
        Booking bookingFound = findBooking(updatedBooking.getId());
        if(bookingFound!=null)
        {
            manager.merge(updatedBooking);
            return "{\"message\": \"Booking sucessfully updated\"}";
        }
        else
            return "{\"message\": \"Booking couldn't be updated\"}";
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public String removeBooking(Long id) {
        Booking booking = findBooking(id);
        if(booking!=null)
        {
            manager.remove(booking);
            return "{\"message\": \"booking sucessfully removed\"}";
        }
        else
            return "{\"message\": \"booking couldn't be removed\"}";
    }

    @Override
    public String getBooking(Long id) {
        Booking bookingFound = findBooking(id);
        if(bookingFound!=null)
        {
            return util.getJSONForObject(bookingFound);
        }
        else
            return "{\"message\": \"movie cannot't be found!\"}";
    }

    public Booking findBooking(long id) {
        return manager.find(Booking.class, id);
    }

}

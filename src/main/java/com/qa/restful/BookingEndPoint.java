package com.qa.restful;

import com.qa.service.IBookingService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/booking")
public class BookingEndPoint {

    @Inject
    private IBookingService bookingService;


    @GET
    @Produces({ "application/json" })
    public String getAllBookings()
    {
        return bookingService.getAllBookings();
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    public String getBooking(@PathParam("id") Long id)
    {
        return bookingService.getBooking(id);
    }

    @POST
    @Produces({ "application/json" })
    @Consumes({"application/json"})
    public String addBooking(String booking)
    {
        return bookingService.addBooking(booking);
    }

    @PUT
    @Produces({ "application/json" })
    @Consumes({ "application/json" })
    public String updateBooking(String booking) {
        return bookingService.updateBooking(booking);
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    public String removeBooking(@PathParam("id") Long id)
    {
        return bookingService.removeBooking(id);
    }
}

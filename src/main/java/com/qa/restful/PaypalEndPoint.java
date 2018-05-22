package com.qa.restful;

import com.qa.domain.Booking;
import com.qa.service.PaypalService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/pay")
public class PaypalEndPoint {

    @Inject
    PaypalService payService;

    @POST
    @Produces({"application/json"})
    public String createPayment(Booking booking){
        return payService.createPayment(booking).toJSON();
    }

    @GET
    @Path("/success")
    @Produces("application/json")
    @Consumes("application/json")
    public String paymentSuccess(@QueryParam("PayerID") String payerID, @QueryParam("paymentId") String paymentId){
        return payService.executePayment(payerID, paymentId).toJSON();
    }
}

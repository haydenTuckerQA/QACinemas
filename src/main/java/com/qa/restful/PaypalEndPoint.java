package com.qa.restful;

import com.qa.service.PaypalService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/pay")
public class PaypalEndPoint {

    @Inject
    PaypalService payService;

    @GET
    @Produces({"application/json"})
    public String getPayment(){
        return payService.getPayment().toJSON();
    }

    @GET
    @Path("/success")
    @Produces("application/json")
    @Consumes("application/json")
    public String paymentSuccess(@QueryParam("PayerID") String payerID, @QueryParam("paymentId") String paymentId){
        return payService.executePayment(payerID, paymentId).toJSON();
    }
}

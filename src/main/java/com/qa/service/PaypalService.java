package com.qa.service;
import com.qa.repository.BookingRepository;
import com.qa.utility.Details;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.qa.domain.Booking;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PaypalService {

    @Inject
    private BookingRepository bookingRepository;

    private String clientId = "AceevttjXwlQN9jUYXWxTqbUSsFRuRlCD8A4-V6_NzxGDPxIca6pcQOw5o6A9M1G4hWxda9A4VjQTfXD";
    private String clientSecret = "EB3sMkBOAaNDxLXNtDwXVZ33wv5GYrNX9AcEdCzSRWSrJtI70To0ZDTDKtjMKWOr10OKAseeT1gEds2P";

    public Payment createPayment(Booking booking){

        double totalPrice = Math.round(booking.getPrice());
        double tax = Math.round(booking.getPrice()*0.2);

        Details details = new Details();
        details.setSubtotal(totalPrice+"");
        details.setTax(tax+"");
        //details.setBooking_id(booking.getId());
        //details.setDate(booking.getDate());

        Amount amount = new Amount();
        amount.setCurrency("GBP");
        amount.setTotal((totalPrice+tax)+"");
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Cinema ticket");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        Item ticket = new Item("Cinema Booking", "1", booking.getPrice()+"", "GBP");
        ticket.setDescription("This is a Cinema booking for "+booking.getSeats() +" adults.");
        items.add(ticket);
        itemList.setItems(items);
        transaction.setItemList(itemList);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setNoteToPayer("Please arrive 15 min before start of the film");

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/QACinemas/web/index.html");
        redirectUrls.setReturnUrl("http://localhost:8080/QACinemas/web/paymentSuccess.html");
        payment.setRedirectUrls(redirectUrls);
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
            payment = payment.create(apiContext);
            bookingRepository.addBooking(booking);
            // For debug purposes only: System.out.println(createdPayment.toString());
        } catch (PayPalRESTException e) {
            // Handle errors
        } catch (Exception ex) {
            // Handle errors
        }

        return payment;
    }

    public Payment executePayment(String payerID, String paymentId) {
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerID);
        Payment executedPayment = null;
        try {
            //payment.execute(apiContext, paymentExecution);
            executedPayment = Payment.get(apiContext, paymentId);
            executedPayment = executedPayment.execute(apiContext, paymentExecution);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return executedPayment;
    }

}

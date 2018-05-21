package com.qa.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;

public class PaypalService {

    private String clientId = "AceevttjXwlQN9jUYXWxTqbUSsFRuRlCD8A4-V6_NzxGDPxIca6pcQOw5o6A9M1G4hWxda9A4VjQTfXD";
    private String clientSecret = "EB3sMkBOAaNDxLXNtDwXVZ33wv5GYrNX9AcEdCzSRWSrJtI70To0ZDTDKtjMKWOr10OKAseeT1gEds2P";
    public Payment getPayment(){

        Amount amount = new Amount();
        amount.setCurrency("GBP");
        amount.setTotal("24.00");

        Details details = new Details();
        details.setSubtotal("20.00");
        details.setTax("4.00");

        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Cinema ticket");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        Item ticket = new Item("Cinema Ticket", "1", "20.00", "GBP");
        ticket.setDescription("An adult Cinema ticket for Dunkkirk film at 1200");
        items.add(ticket);
        itemList.setItems(items);

        ShippingAddress shippingAddress = new ShippingAddress("Saber");
        shippingAddress.setLine1("23 Lover Walk");
        shippingAddress.setCity("Southampton");
        shippingAddress.setCountryCode("GB");
        shippingAddress.setPostalCode("SO17 2HU");
        itemList.setShippingAddress(shippingAddress);


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
        redirectUrls.setCancelUrl("http://localhost:8080/QACinemas/api/pay/cancel");
        redirectUrls.setReturnUrl("http://localhost:8080/QACinemas/api/pay/success");
        payment.setRedirectUrls(redirectUrls);
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
            payment = payment.create(apiContext);
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

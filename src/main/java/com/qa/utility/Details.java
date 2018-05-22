package com.qa.utility;

public class Details extends com.paypal.api.payments.Details {

    private Long booking_id;
    private String date;

    public Long getBooking_id() {
        return booking_id;
    }

    public String getDate() {
        return date;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

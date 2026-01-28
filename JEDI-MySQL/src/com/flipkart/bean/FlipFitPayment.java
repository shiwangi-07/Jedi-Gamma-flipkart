package com.flipkart.bean;

/**
 * Represents a payment in the FlipFit system.
 * Each payment is associated with a booking and contains details such as payment ID and amount paid.
 */
public class FlipFitPayment {

    private String paymentId;
    private String amountPaid;
    private String bookingId;

    /**
     * Retrieves the payment ID.
     * @return Payment ID
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the payment ID.
     * @param paymentId Payment ID to set
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Retrieves the amount paid for the payment.
     * @return Amount paid
     */
    public String getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the amount paid for the payment.
     * @param amountPaid Amount paid to set
     */
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Retrieves the booking ID associated with the payment.
     * @return Booking ID
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID associated with the payment.
     * @param bookingId Booking ID to set
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}

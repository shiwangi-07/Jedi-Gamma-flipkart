package com.flipfit.business;

import java.util.Date;
import com.flipfit.bean.Slot;
import com.flipfit.bean.Payment;

public class CustomerBusinessImpl implements CustomerBusiness {
    
    // We delegate the actual booking logic to the Booking Service
    private BookingBusinessService bookingService = new BookingBusinessService();
    private WaitingListBusiness waitingListService = new WaitingListBusinessImpl();

    @Override
    public void viewCentres(String city) {
        // Simulating fetching data
        System.out.println("Fetching gyms in " + city + "...");
        System.out.println("1. FlipFit " + city + " Centre");
    }

    @Override
    public boolean viewAvailability(Date date, int centerId) {
        System.out.println("Checking availability for Centre " + centerId + " on " + date);
        // In a real app, we would query the database here
        return true; 
    }

    @Override
    public boolean bookSlot(int slotId, Date date, int userId) {
        // In a real app, we would fetch the actual Slot object by ID from a database here.
        // create a dummy slot object to proceed so the code runs.
        Slot dummySlot = new Slot(slotId, 10, 5); // ID, Time (10AM), Capacity (5)
        
        System.out.println("Requesting booking for Slot " + slotId + " by User " + userId);
        
        // Delegate to Booking Service to handle the logic (decrement seats, etc)
        // We assume 1 seat for simplicity and a fixed price
        bookingService.createBooking(userId, 1, dummySlot, 1, 500); 
        
        return true;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        // In real app we would pass the masterSlotList here
        System.out.println("Requesting cancellation for Booking " + bookingId);
        return true;
    }

    @Override
    public boolean makePayment(int userId, double amount) {
        // Using Payment Bean
        Payment payment = new Payment();
        payment.setPaymentId("PAY-" + System.currentTimeMillis());
        payment.setAmount(amount);
        
        System.out.println("Processing Payment ID: " + payment.getPaymentId());
        System.out.println("Amount: ₹" + payment.getAmount() + " received from User " + userId);
        return true;
    }
    @Override
    public boolean joinWaitList(int slotId, int userId) {
        // REAL LOGIC: Delegating to WaitingListBusiness
        // We assume CentreID is 1 for now (Hardcoded)
        waitingListService.addCustomerToWaitlist(userId, slotId, 1);
        return true;
    }

}
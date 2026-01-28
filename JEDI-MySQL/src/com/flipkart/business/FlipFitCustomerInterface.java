package com.flipkart.business;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.util.List;

/**
 * Interface defining operations related to customers in the FlipFit system.
 */
public interface FlipFitCustomerInterface {

    /**
     * Retrieves details of all gym centers in a specific city.
     * 
     * @param city City name
     * @return List of gym center details
     */
    List<FlipFitGymCenter> getAllGymCenterDetailsByCity(String city);

    /**
     * Retrieves available slots for booking at a specific gym center on a given
     * date.
     * 
     * @param centreID ID of the gym center
     * @param date     Date for which slots are to be retrieved
     * @return List of available slots
     */
    List<FlipFitSlot> getAvailableSlots(String centreID, Date date);

    /**
     * Retrieves bookings made by a customer identified by their customer ID.
     * 
     * @param customerId ID of the customer
     * @return List of customer bookings
     */
    List<FlipFitBooking> getCustomerBookings(String customerId);

    /**
     * Books a slot for a user at a specific gym center on a given date and slot ID.
     * 
     * @param userID   ID of the user making the booking
     * @param date     Date of the booking
     * @param slotId   ID of the slot (time slot) to book
     * @param centreId ID of the gym center
     * @return True if booking is successful, false otherwise
     */
    boolean bookSlot(String userID, Date date, String slotId, String centreId);

    /**
     * Cancels a booking identified by its booking ID.
     * 
     * @param bookingID ID of the booking to cancel
     */
    void cancelBookingByID(String bookingID);

    /**
     * Registers a new customer with the provided details.
     * 
     * @param userName    Username of the customer
     * @param password    Password of the customer
     * @param email       Email of the customer
     * @param phoneNumber Phone number of the customer
     * @param cardNumber  Card number of the customer
     */
    void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber);

    /**
     * Retrieves the profile details of a customer identified by their username.
     * 
     * @param userName Username of the customer
     * @return Customer profile information
     */
    FlipFitCustomer viewMyProfile(String userName);

    /**
     * Checks if a user with the provided username and password exists and is valid.
     * 
     * @param userName Username of the user
     * @param password Password of the user
     * @return True if the user is valid, false otherwise
     */
    boolean isUserValid(String userName, String password);

    /**
     * Retrieves the plan(s) subscribed by a customer identified by their username.
     * 
     * @param userName Username of the customer
     * @return List of plans subscribed by the customer
     */
    List<UserPlan> getCustomerPlan(String userName);

    /**
     * Retrieves the plan(s) subscribed by a customer identified by their username
     * for a specific date.
     * 
     * @param userName Username of the customer
     * @param date     Date of the plan
     * @return List of plans subscribed by the customer for the given date
     */
    List<UserPlan> getCustomerPlan(String userName, Date date);

    /**
     * Cancels a booking identified by its booking ID.
     * 
     * @param bookingId ID of the booking to cancel
     */
    void cancelBookingbyID(String bookingId);
}

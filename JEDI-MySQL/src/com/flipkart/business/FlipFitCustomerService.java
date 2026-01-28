package com.flipkart.business;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.dao.FlipFitCustomerDAO;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.util.List;
import java.time.ZoneId;
import java.util.ArrayList;

public class FlipFitCustomerService implements FlipFitCustomerInterface {

    private FlipFitGymCenterInterface gymCentreService = new FlipFitGymCenterService();
    private FlipFitBookingInterface bookingService = new FlipFitBookingService();
    private FlipFitScheduleInterface scheduleService = new FlipFitScheduleService();
    private FlipFitSlotInterface slotService = new FlipFitSlotService();
    private FlipFitCustomerDAO customerDAO = new FlipFitCustomerDAO();

    public List<FlipFitGymCenter> getAllGymCenterDetailsByCity(String city) {
        // takes City (Location) as input and returns List<GymCenter>
        return gymCentreService.getCentresByCity(city);
    }

    @Override
    public List<FlipFitSlot> getAvailableSlots(String centreID, Date date) {
        return gymCentreService.getAvailableSlotsByCentreAndDate(centreID, date);
    }

    public List<FlipFitBooking> getCustomerBookings(String customerId) {
        // takes userId and returns List<Bookings>
        return bookingService.getBookingByCustomerId(customerId);
    }

    @Override
    public void cancelBookingByID(String bookingID) {
        bookingService.cancelBooking(bookingID);
    }

    public List<UserPlan> getCustomerPlan(String customerId) {
        return bookingService.getCustomerPlan(customerId);
    }

    public List<UserPlan> getCustomerPlan(String customerId, Date date) {
        List<UserPlan> allPlan = bookingService.getCustomerPlan(customerId);
        List<UserPlan> selectedPlan = new ArrayList<>();
        for (UserPlan userPlan : allPlan) {
            if (userPlan.getDate().equals(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                selectedPlan.add(userPlan);
            }
        }
        return selectedPlan;
    }

    public boolean bookSlot(String userName, Date date, String slotId, String centreId) {
        if (!slotService.isSlotValid(slotId, centreId)) {
            System.out.println("INVALID SLOT");
            return false;
        }
        String scheduleId = scheduleService.getOrCreateSchedule(slotId, date).getScheduleID();
        // create booking
        boolean isOverlap = bookingService.checkBookingOverlap(userName, date, slotId);
        if (isOverlap) {
            System.out.println("There exists a conflicting booking, First cancel it!!!!");
            return false;
        }
        bookingService.addBooking(userName, scheduleId);
        return true;
    }

    public void cancelBookingbyID(String bookingID) {
        // cancel a booking
        bookingService.cancelBooking(bookingID);
    }

    public void registerCustomer(String userName, String password, String email, String phoneNumber,
            String cardNumber) {
        try {
            customerDAO.registerCustomer(userName, password, email, phoneNumber, cardNumber);
        } catch (RegistrationFailedException e) {
            e.getMessage();
        }
    }

    @Override
    public FlipFitCustomer viewMyProfile(String userName) {
        return customerDAO.getCustomerById(userName);
    }

    public boolean isUserValid(String userName, String password) {
        try {
            return customerDAO.isUserValid(userName, password);
        } catch (UserInvalidException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

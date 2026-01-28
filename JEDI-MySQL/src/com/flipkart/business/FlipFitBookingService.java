package com.flipkart.business;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.dao.FlipFitBookingDAO;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.util.List;

public class FlipFitBookingService implements FlipFitBookingInterface {

    private final FlipFitBookingDAO bookingDAO = new FlipFitBookingDAO();
    private final FlipFitScheduleService scheduleService  = new FlipFitScheduleService();
    private final FlipFitSlotService slotService = new FlipFitSlotService();

    public boolean checkBookingOverlap(String customerId, Date date, String slotId){
        FlipFitSlot slot = slotService.getSlotByID(slotId);
        return bookingDAO.checkBookingOverlap(customerId,date,slot.getTime());
    }

    public void addBooking(String userName, String scheduleID) {
        try {
            boolean isAvailable = scheduleService.modifySchedule(scheduleID,-1);
            if(!isAvailable){
                System.out.println("No seats available for the booking");
                return;
            }
            bookingDAO.addBooking(userName, scheduleID);
        } catch (BookingFailedException e) {
            System.out.println("Failed to add booking");
        }
    }

    public List<FlipFitBooking> getBookingByCustomerId(String customerId){
        try {
            return bookingDAO.getBookingByCustomerId(customerId);
        } catch (BookingFailedException e) {
            System.out.println("Failed to get booking");
        }
        return null;
    }

    public List<UserPlan> getCustomerPlan(String customerId){
        return bookingDAO.getCustomerPlan(customerId);
    }

    public void cancelBooking(String bookingID) {
        try {
            FlipFitBooking booking  = bookingDAO.getBookingByBookingId(bookingID);
            bookingDAO.cancelBookingById(bookingID);
            scheduleService.modifySchedule(booking.getScheduleID(),1);
        } catch (BookingFailedException e) {
            System.out.println("Failed to cancel booking");
        }
    }

}

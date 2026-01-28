package com.flipkart.dao;

import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitSchedule;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.utils.UserPlan;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class FlipFitBookingDAO implements FlipFitBookingDAOInterface {

    private List<FlipFitBooking> bookingList = new ArrayList<>();
    private FlipFitScheduleDAO flipFitScheduleDAO = new FlipFitScheduleDAO();
    private FlipFitSlotDAO flipFitSlotDAO = new FlipFitSlotDAO();

    public void addBooking(String userName, String scheduleID) {
        try {
            String bookingId = userName + scheduleID;

            FlipFitBooking booking = new FlipFitBooking(bookingId, userName, scheduleID);
            bookingList.add(booking);
            System.out.println("Booking added successfully");
        } catch (Exception e) {
            System.out.println("Booking failed for current slot. Try again later.");
        }
    }

    public List<FlipFitBooking> getBookingByCustomerId(String customerId) {
        List<FlipFitBooking> customerBookings = new ArrayList<>();
        for (FlipFitBooking booking : bookingList) {
            if (booking.getUserID().equals(customerId)) {
                customerBookings.add(booking);
            }
        }
        return customerBookings;
    }

    public List<UserPlan> getCustomerPlan(String customerId) {
        List<UserPlan> allUserPlan = new ArrayList<>();
        try {
            List<FlipFitBooking> customerBookings = getBookingByCustomerId(customerId);
            for (FlipFitBooking booking : customerBookings) {
                FlipFitSchedule schedule = flipFitScheduleDAO.getSchedule(booking.getScheduleID());
                FlipFitSlot slot = flipFitSlotDAO.getSlotById(schedule.getSlotId());
                UserPlan userPlan = new UserPlan(
                        slot.getSlotId(),
                        slot.getCenterID(),
                        slot.getTime(),
                        schedule.getScheduleID(),
                        schedule.getDate());
                allUserPlan.add(userPlan);
            }
        } catch (Exception e) {
            System.out.println("Failed to get customer plan");
        }
        return allUserPlan;
    }

    public boolean checkBookingOverlap(String customerId, Date date, LocalTime localTime) {
        LocalTime endTime = localTime.plusHours(1);
        List<UserPlan> allUserPlan = getCustomerPlan(customerId);

        // Use stream to iterate over allUserPlan and check for overlap
        return allUserPlan.stream()
                .filter(userPlan -> userPlan.getDate()
                        .equals(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                .anyMatch(userPlan -> {
                    LocalTime bookedStartTime = userPlan.getTime();
                    LocalTime bookedEndTime = bookedStartTime.plusHours(1);
                    return localTime.isBefore(bookedEndTime) && endTime.isAfter(bookedStartTime);
                });
    }

    public void cancelBookingById(String bookingID) {
        Optional<FlipFitBooking> bookingToRemove = bookingList.stream()
                .filter(booking -> booking.getBookingID().equals(bookingID))
                .findFirst();

        if (bookingToRemove.isPresent()) {
            bookingList.remove(bookingToRemove.get());
            System.out.println("Booking canceled successfully");
        } else {
            System.out.println("Could not cancel booking for BookingId: " + bookingID);
        }
    }

    public FlipFitBooking getBookingByBookingId(String bookingId) {
        Optional<FlipFitBooking> optionalBooking = bookingList.stream()
                .filter(booking -> booking.getBookingID().equals(bookingId))
                .findFirst();

        if (optionalBooking.isPresent()) {
            return optionalBooking.get();
        } else {
            System.out.println("Could not fetch booking by bookingId: " + bookingId);
            return null;
        }
    }

}

package com.flipkart.bean;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents Schedule of any GymCentre Slot
 */

public class FlipFitSchedule {
    private String scheduleID;
    private String slotId;
    private int availability;
    private LocalDate date;

    /**
     * Constructor for FlipFitSchedule.
     * @param date the date of the schedule (LocalDate object)
     * @param slotId the ID of the slot associated with the schedule
     * @param availability the availability status of the slot (integer)
     */
    public FlipFitSchedule(LocalDate date, String slotId, int availability) {
        this.scheduleID = UUID.randomUUID().toString(); // Generate a unique schedule ID
        this.date = date;
        this.slotId = slotId;
        this.availability = availability;
    }

    /**
     * Setter for the schedule ID.
     * @param scheduleID the schedule ID to set
     */
    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    /**
     * Getter for the schedule ID.
     * @return the schedule ID
     */
    public String getScheduleID() {
        return scheduleID;
    }

    /**
     * Setter for the date of the schedule.
     * @param date the date to set for the schedule (LocalDate object)
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for the date of the schedule.
     * @return the date of the schedule (LocalDate object)
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter for the slot ID associated with the schedule.
     * @param slotId the slot ID to set
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    /**
     * Getter for the slot ID associated with the schedule.
     * @return the slot ID
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Getter for the availability status of the slot.
     * @return the availability status (integer)
     */
    public int getAvailability() {
        return availability;
    }

    /**
     * Setter for the availability status of the slot.
     * @param availability the availability status to set
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }
}

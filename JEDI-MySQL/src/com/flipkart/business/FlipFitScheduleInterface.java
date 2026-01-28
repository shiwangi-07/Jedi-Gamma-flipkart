package com.flipkart.business;

import com.flipkart.bean.FlipFitSchedule;
import com.flipkart.bean.FlipFitSlot;

import java.util.Date;
import java.util.List;

/**
 * Interface defining operations related to schedules in the FlipFit system.
 */
public interface FlipFitScheduleInterface {

    /**
     * Creates a new schedule for a given date and slot ID.
     * @param date Date of the schedule
     * @param slotId ID of the slot (time slot)
     * @return The created FlipFitSchedule object
     */
    FlipFitSchedule createSchedule(Date date, String slotId);

    /**
     * Retrieves a schedule based on slot ID and date.
     * @param slotId ID of the slot
     * @param date Date of the schedule
     * @return The FlipFitSchedule object
     */
    FlipFitSchedule getScheduleByDateAndSlotId(String slotId, Date date);

    /**
     * Modifies a schedule based on its ID and action.
     * @param scheduleId ID of the schedule to modify
     * @param action Action to perform on the schedule (e.g., update, delete)
     * @return True if modification is successful, false otherwise
     */
    boolean modifySchedule(String scheduleId, int action);

    /**
     * Checks availability of schedules for a given gym center and date.
     * @param centreId ID of the gym center
     * @param date Date to check for schedule availability
     * @return List of available schedules
     */
    List<FlipFitSchedule> checkAvailability(String centreId, Date date);

    /**
     * Retrieves all available slots for a given gym center and date.
     * @param centreId ID of the gym center
     * @param date Date to retrieve available slots
     * @return List of available slots
     */
    List<FlipFitSlot> getAllAvailableSlotsByDate(String centreId, Date date);

    /**
     * Retrieves or creates a schedule for a given slot ID and date.
     * @param slotId ID of the slot
     * @param date Date of the schedule
     * @return The retrieved or created FlipFitSchedule object
     */
    FlipFitSchedule getOrCreateSchedule(String slotId, Date date);
}

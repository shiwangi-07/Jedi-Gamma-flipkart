package com.flipkart.dao;

import com.flipkart.bean.FlipFitSchedule;

import java.sql.Date;
import java.util.List;

/**
 * Data Access Object (DAO) interface for handling schedule-related operations in FlipFit system.
 * This interface defines methods for adding, retrieving, modifying schedules,
 * and fetching schedules by specific criteria.
 */
public interface FlipFitScheduleDAOInterface {

    /**
     * Adds a new schedule to the system.
     * @param schedule The FlipFitSchedule object representing the schedule to add
     */
    void addSchedule(FlipFitSchedule schedule);

    /**
     * Retrieves a schedule based on its ID.
     * @param scheduleId ID of the schedule to retrieve
     * @return The FlipFitSchedule object
     */
    FlipFitSchedule getSchedule(String scheduleId);

    /**
     * Retrieves all schedules scheduled for a specific date.
     * @param date Date for which schedules are to be retrieved
     * @return List of schedules scheduled for the specified date
     */
    List<FlipFitSchedule> getAllScheduleByDate(Date date);

    /**
     * Modifies a schedule based on its ID and action.
     * @param scheduleId ID of the schedule to modify
     * @param action Action to perform on the schedule (e.g., update, delete)
     * @return True if the modification was successful, false otherwise
     */
    boolean modifySchedule(String scheduleId, int action);
}

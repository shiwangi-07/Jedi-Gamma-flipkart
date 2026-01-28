package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;

import java.util.Date;
import java.util.List;

/**
 * Interface defining operations related to gym centers in the FlipFit system.
 */
public interface FlipFitGymCenterInterface {

    /**
     * Retrieves details of all gym centers.
     * @return List of all gym centers
     */
    List<FlipFitGymCenter> viewAllGymCenters();

    /**
     * Retrieves details of all gym centers owned by a specific gym owner.
     * @param gymOwnerId ID of the gym owner
     * @return List of gym centers owned by the gym owner
     */
    List<FlipFitGymCenter> getAllCentresByOwmerId(String gymOwnerId);

    /**
     * Retrieves details of gym centers located in a specific city.
     * @param city City name
     * @return List of gym centers in the specified city
     */
    List<FlipFitGymCenter> getCentresByCity(String city);

    /**
     * Retrieves available slots for booking at a specific gym center on a given date.
     * @param centreID ID of the gym center
     * @param date Date for which slots are to be retrieved
     * @return List of available slots
     */
    List<FlipFitSlot> getAvailableSlotsByCentreAndDate(String centreID, Date date);

    /**
     * Adds a new gym center to the system.
     * @param gymCentre Gym center object to add
     */
    void addCenter(FlipFitGymCenter gymCentre);

    /**
     * Requests approval for a gym center identified by its ID.
     * @param gymCentreId ID of the gym center to request approval for
     */
    void requestGymCentreApproval(String gymCentreId);

    /**
     * Retrieves details of a gym center identified by its ID.
     * @param centreID ID of the gym center
     * @return Gym center object
     */
    FlipFitGymCenter getGymCentreById(String centreID);
}

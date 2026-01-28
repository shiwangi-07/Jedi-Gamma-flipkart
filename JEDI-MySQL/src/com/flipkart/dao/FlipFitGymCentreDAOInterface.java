package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCenter;

import java.util.List;

/**
 * Data Access Object (DAO) interface for handling gym center-related operations in FlipFit system.
 * This interface defines methods for viewing, adding, retrieving gym centers,
 * managing approval requests, and fetching gym centers by specific criteria.
 */
public interface FlipFitGymCentreDAOInterface {

    /**
     * Retrieves details of all gym centers.
     * @return List of all gym centers
     */
    List<FlipFitGymCenter> viewAllGymCenters();

    /**
     * Retrieves details of all gym centers owned by a specific owner.
     * @param gymOwnerId ID of the gym owner
     * @return List of gym centers owned by the specified owner
     */
    List<FlipFitGymCenter> getAllCentresByOwnerId(String gymOwnerId);

    /**
     * Retrieves details of a gym center based on its ID.
     * @param gymCentreId ID of the gym center
     * @return The FlipFitGymCenter object
     */
    FlipFitGymCenter getGymCentreByCentreId(String gymCentreId);

    /**
     * Adds a new gym center to the system.
     * @param centre The FlipFitGymCenter object representing the gym center to add
     */
    void addGymCentre(FlipFitGymCenter centre);

    /**
     * Retrieves a list of pending gym centers awaiting approval.
     * @return List of pending gym centers
     */
    List<FlipFitGymCenter> getPendingGymCentreList();

    /**
     * Validates (approves or rejects) a gym center based on its ID.
     * @param gymCentreId ID of the gym center to validate
     * @param isApproved True to approve, false to reject
     */
    void validateGymCentre(String gymCentreId, boolean isApproved);

    /**
     * Sends a request for approval of a gym center based on its ID.
     * @param gymCentreId ID of the gym center to request approval for
     */
    void sendCentreApprovalRequest(String gymCentreId);

    /**
     * Retrieves a list of gym centers located in a specific city.
     * @param city City name
     * @return List of gym centers in the specified city
     */
    List<FlipFitGymCenter> getGymCentreListByCity(String city);
}

package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

/**
 * Data Access Object (DAO) interface for handling gym owner-related operations in FlipFit system.
 * This interface defines methods for retrieving gym owner details, managing approval requests,
 * and registering gym owners.
 */
public interface FlipFitGymOwnerDAOInterface {

    /**
     * Retrieves a list of all gym owners.
     * @return List of all gym owners
     */
    List<FlipFitGymOwner> getGymOwnerList();

    /**
     * Sets the list of gym owners.
     * @param gymOwnerList List of gym owners to set
     */
    void setGymOwnerList(List<FlipFitGymOwner> gymOwnerList);

    /**
     * Registers a new gym owner.
     * @param gymOwner The FlipFitGymOwner object representing the gym owner to register
     */
    void registerGymOwner(FlipFitGymOwner gymOwner);

    /**
     * Retrieves a list of pending gym owners awaiting approval.
     * @return List of pending gym owners
     */
    List<FlipFitGymOwner> getPendingGymOwnerList();

    /**
     * Sends a request for approval of a gym owner based on its ID.
     * @param gymOwnerId ID of the gym owner to request approval for
     */
    void sendOwnerApprovalRequest(String gymOwnerId);

    /**
     * Sets the list of pending gym owners.
     * Note: Implementation may vary based on system requirements.
     */
    void setPendingGymOwnerList();

    /**
     * Validates (approves or rejects) a gym owner based on its ID.
     * @param gymOwnerId ID of the gym owner to validate
     * @param isApproved True to approve, false to reject
     */
    void validateGymOwner(String gymOwnerId, boolean isApproved);
}

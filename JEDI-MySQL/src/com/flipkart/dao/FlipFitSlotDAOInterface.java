package com.flipkart.dao;

import com.flipkart.bean.FlipFitSlot;

import java.util.List;

/**
 * Data Access Object (DAO) interface for handling slot-related operations in FlipFit system.
 * This interface defines methods for retrieving slots, adding slots, and fetching slots by specific criteria.
 */
public interface FlipFitSlotDAOInterface {

    /**
     * Retrieves a list of all slots.
     * @return List of all slots
     */
    List<FlipFitSlot> getSlotList();

    /**
     * Retrieves a list of slots associated with a specific gym center.
     * @param gymCentreId ID of the gym center
     * @return List of slots associated with the specified gym center
     */
    List<FlipFitSlot> getSlotByCentreId(String gymCentreId);

    /**
     * Adds a new slot to the system.
     * @param slot The FlipFitSlot object representing the slot to add
     */
    void addSlot(FlipFitSlot slot);

    /**
     * Retrieves a slot based on its ID.
     * @param slotID ID of the slot to retrieve
     * @return The FlipFitSlot object
     */
    FlipFitSlot getSlotById(String slotID);

    /**
     * Retrieves a slot based on its ID and associated gym center ID.
     * @param slotID ID of the slot to retrieve
     * @param gymCentreId ID of the gym center
     * @return The FlipFitSlot object
     */
    FlipFitSlot getSlotByIdandCentreId(String slotID, String gymCentreId);
}

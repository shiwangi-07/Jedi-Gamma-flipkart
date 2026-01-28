package com.flipkart.business;

import com.flipkart.bean.FlipFitSlot;

import java.util.List;

/**
 * Interface defining operations related to slots in the FlipFit system.
 */
public interface FlipFitSlotInterface {

    /**
     * Retrieves all slots associated with a specific gym center.
     * @param centreID ID of the gym center
     * @return List of slots for the gym center
     */
    List<FlipFitSlot> getAllSlotsByCentre(String centreID);

    /**
     * Retrieves a slot based on its ID.
     * @param slotID ID of the slot
     * @return The FlipFitSlot object
     */
    FlipFitSlot getSlotByID(String slotID);

    /**
     * Retrieves a list of all slots.
     * @return List of all slots
     */
    List<FlipFitSlot> getSlotList();

    /**
     * Adds slots to a gym center identified by its ID.
     * @param gymCentreId ID of the gym center
     * @param slotList List of slots to add
     */
    void addSlotsForGym(String gymCentreId, List<FlipFitSlot> slotList);

    /**
     * Checks if a slot is valid for a specific gym center.
     * @param slotID ID of the slot
     * @param centreId ID of the gym center
     * @return True if the slot is valid, false otherwise
     */
    boolean isSlotValid(String slotID, String centreId);

    /**
     * Retrieves a slot based on its ID and gym center ID.
     * @param slotID ID of the slot
     * @param centreId ID of the gym center
     * @return The FlipFitSlot object
     */
    FlipFitSlot getSlotByIDCentreId(String slotID, String centreId);
}

package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.dao.FlipFitGymCenterDAO;
import java.util.List;

public class FlipFitGymCenterService implements FlipFitGymCenterInterface {
    private static FlipFitGymCenterDAO flipFitGymCenterDAO = new FlipFitGymCenterDAO();
    private static FlipFitScheduleInterface scheduleService = new FlipFitScheduleService();

    public List<FlipFitGymCenter> viewAllGymCenters() {
        return flipFitGymCenterDAO.viewAllGymCenters();
    }

    public List<FlipFitGymCenter> getAllCentresByOwmerId(String gymOwnerId) {
        return flipFitGymCenterDAO.getAllCentresByOwmerId(gymOwnerId);
    }

    public List<FlipFitGymCenter> getCentresByCity(String city) {
        return flipFitGymCenterDAO.getGymCentreListByCity(city);
    }

    public List<FlipFitSlot> getAvailableSlotsByCentreAndDate(String centreID, java.util.Date date) {
        return scheduleService.getAllAvailableSlotsByDate(centreID, date);
    }

    public void addCenter(FlipFitGymCenter gymCentre) {
        // takes gymCenter details
        flipFitGymCenterDAO.addGymCentre(gymCentre);

        // Auto-generate slots
        java.util.List<FlipFitSlot> slots = new java.util.ArrayList<>();
        int openTime = gymCentre.getOpenTime();
        int noOfSlots = gymCentre.getNoOfSlots();

        for (int i = 0; i < noOfSlots; i++) {
            // Generate UUID for slot
            String slotId = java.util.UUID.randomUUID().toString();
            // Calculate time: openTime + i hours
            java.time.LocalTime time = java.time.LocalTime.of(openTime + i, 0); // Assuming 1 hour slots

            FlipFitSlot slot = new FlipFitSlot(slotId, gymCentre.getGymCenterId(), time);
            slots.add(slot);
        }

        FlipFitSlotInterface slotService = new FlipFitSlotService();
        slotService.addSlotsForGym(gymCentre.getGymCenterId(), slots);
    }

    public void requestGymCentreApproval(String gymCentreId) {
        flipFitGymCenterDAO.sendCentreApprovalRequest(gymCentreId);
    }

    public FlipFitGymCenter getGymCentreById(String centreID) {
        FlipFitGymCenter gymCenter = flipFitGymCenterDAO.getGymCentreByCentreId(centreID);
        return gymCenter;
    }
}

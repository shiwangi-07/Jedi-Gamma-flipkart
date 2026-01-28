package com.flipkart.business;

import com.flipkart.dao.FlipFitSlotDAO;
import com.flipkart.bean.FlipFitSlot;

import java.util.List;

import static java.nio.file.Paths.get;

public class FlipFitSlotService implements FlipFitSlotInterface {
    private static final FlipFitSlotDAO FLIP_FIT_SLOT_DAO = new FlipFitSlotDAO();
    public List<FlipFitSlot> getAllSlotsByCentre(String centreID) {
        return FLIP_FIT_SLOT_DAO.getSlotByCentreId(centreID);
    }

    public FlipFitSlot getSlotByID(String slotID){
        return FLIP_FIT_SLOT_DAO.getSlotById(slotID);
    }

    public FlipFitSlot getSlotByIDCentreId(String slotID, String centreId){
        return FLIP_FIT_SLOT_DAO.getSlotByIdandCentreId(slotID,centreId);
    }

    public List<FlipFitSlot> getSlotList(){
        return FLIP_FIT_SLOT_DAO.getSlotList();
    }

    public void addSlotsForGym(String gymCentreId, List<FlipFitSlot> slotList){
        System.out.println("Adding all slots to gym: " + gymCentreId);
        for(FlipFitSlot slot : slotList) {
            slot.setCenterID(gymCentreId);
            FLIP_FIT_SLOT_DAO.addSlot(slot);
        }
    }

    public boolean isSlotValid(String slotID,String centreId){
        get(slotID, centreId);
        return true;
    }
}
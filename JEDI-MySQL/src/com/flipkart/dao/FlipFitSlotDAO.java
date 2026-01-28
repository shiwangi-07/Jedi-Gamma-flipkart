package com.flipkart.dao;

import com.flipkart.bean.FlipFitSlot;

import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class FlipFitSlotDAO implements FlipFitSlotDAOInterface {

    public FlipFitSlotDAO(){
    }

    public List<FlipFitSlot> getSlotList() {
        List<FlipFitSlot> slotList = new ArrayList<>();
        try{
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_ALL_SLOTS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String slotId = rs.getString("slotId");
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();

                slotList.add(new FlipFitSlot(slotId, centreId, time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
    }

    public List<FlipFitSlot> getSlotByCentreId(String gymCentreId){
        List<FlipFitSlot> slotList = new ArrayList<>();
        try{
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_CENTRE);
            ps.setString(1,gymCentreId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String slotId = rs.getString("slotId");
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();

                slotList.add(new FlipFitSlot(slotId, centreId, time));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slotList;
    }

    public void addSlot(FlipFitSlot slot){
        try{
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_SLOT);
            ps.setString(1, slot.getSlotId());
            ps.setString(2, slot.getCenterID());
            ps.setTime(3, java.sql.Time.valueOf(slot.getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FlipFitSlot getSlotById(String slotID) {
        FlipFitSlot slot = null;
        try{
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_ID);
            ps.setString(1,slotID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String centreId = rs.getString("centreId");
                LocalTime time = rs.getTime("time").toLocalTime();
                slot = new FlipFitSlot(slotID, centreId, time);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slot;
    }

    public FlipFitSlot getSlotByIdandCentreId(String slotID, String centreID) {
        FlipFitSlot slot = null;
        try{
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.FETCH_SLOT_BY_ID_AND_CENTRE_ID);
            ps.setString(1,slotID);
            ps.setString(2,centreID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalTime time = rs.getTime("time").toLocalTime();
                slot = new FlipFitSlot(slotID, centreID, time);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return slot;
    }
//    private Map<String, Slot> slotMap = new HashMap<>();
//
//    public SlotDAO() {
//    }
//
//    public List<Slot> getSlotList() {
//        return new ArrayList<>(slotMap.values());
//    }
//
//    public List<Slot> getSlotByCentreId(String gymCentreId) {
//        List<Slot> slotList = new ArrayList<>();
//        for (Slot slot : slotMap.values()) {
//            if (slot.getCenterID().equals(gymCentreId)) {
//                slotList.add(slot);
//            }
//        }
//        return slotList;
//    }
//
//    public void addSlot(Slot slot) {
//        if (slotMap.containsKey(slot.getSlotId())) {
//            throw new RuntimeException("Slot ID already exists");
//        }
//        slotMap.put(slot.getSlotId(), slot);
//        System.out.println("Slot added successfully");
//    }
//
//    public Slot getSlotById(String slotID) {
//        return slotMap.get(slotID);
//    }
//
//    public Slot getSlotByIdandCentreId(String slotID, String centreID) {
//        Slot slot = slotMap.get(slotID);
//        if (slot != null && slot.getCenterID().equals(centreID)) {
//            return slot;
//        }
//        return null;
//    }
}

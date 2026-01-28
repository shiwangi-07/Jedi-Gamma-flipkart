package com.flipkart.dao;

import com.flipkart.bean.FlipFitSchedule;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class FlipFitScheduleDAO implements FlipFitScheduleDAOInterface {

    public void addSchedule(FlipFitSchedule schedule) {
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.ADD_SCHEDULE);
            ps.setString(1, schedule.getScheduleID());
            ps.setDate(2, new java.sql.Date(
                    schedule.getDate().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()));
            ps.setString(3, schedule.getSlotId());
            ps.setInt(4, schedule.getAvailability());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public FlipFitSchedule getSchedule(String scheduleId) {
        FlipFitSchedule schedule = null;
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_SCHEDULE_BY_ID);
            ps.setString(1, scheduleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String slotId = rs.getString("slotId");
                int availability = rs.getInt("availability");
                String date = rs.getString("date");
                LocalDate localDate = LocalDate.parse(date);
                schedule = new FlipFitSchedule(localDate, slotId, availability);
                schedule.setScheduleID(scheduleId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return schedule;
    }

    public List<FlipFitSchedule> getAllScheduleByDate(java.sql.Date date) {
        return List.of();
    }

    public List<FlipFitSchedule> getAllScheduleByDate(Date date) {
        ArrayList<FlipFitSchedule> response = new ArrayList<>();
        try {
            Connection conn = DatabaseConnector.connect();
            PreparedStatement ps = conn.prepareStatement(SQLConstants.GET_SCHEDULES_BY_DATE);
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String scheduleId = rs.getString("scheduleId");
                String slotId = rs.getString("slotId");
                int availability = rs.getInt("availability");
                LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                FlipFitSchedule schedule = new FlipFitSchedule(localDate, slotId, availability);
                schedule.setScheduleID(scheduleId);
                response.add(schedule);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public boolean modifySchedule(String scheduleId, int action) {
        // 1 for increasing, -1 for decreasing
        try {
            Connection conn = DatabaseConnector.connect();
            int availability = getSchedule(scheduleId).getAvailability();
            if (availability < 1 && action == -1) {
                return false;
            }
            PreparedStatement ps = conn.prepareStatement(SQLConstants.MODIFY_SCHEDULE_AVAILABILITY);
            ps.setInt(1, availability + action);
            ps.setString(2, scheduleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // private Map<String, Schedule> scheduleMap = new HashMap<>();
    //
    // public void addSchedule(Schedule schedule) {
    // if (scheduleMap.containsKey(schedule.getScheduleID())) {
    // throw new RuntimeException("Schedule ID already exists");
    // }
    // scheduleMap.put(schedule.getScheduleID(), schedule);
    // System.out.println("Schedule added successfully");
    // }
    //
    // public Schedule getSchedule(String scheduleId) {
    // return scheduleMap.get(scheduleId);
    // }
    //
    // public List<Schedule> getAllScheduleByDate(Date date) {
    // List<Schedule> response = new ArrayList<>();
    // for (Schedule schedule : scheduleMap.values()) {
    // if (schedule.getDate().equals(date)) {
    // response.add(schedule);
    // }
    // }
    // return response;
    // }
    //
    // public boolean modifySchedule(String scheduleId, int action) {
    // Schedule schedule = scheduleMap.get(scheduleId);
    // if (schedule == null) {
    // return false;
    // }
    //
    // int availability = schedule.getAvailability();
    // if (availability < 1 && action == -1) {
    // return false;
    // }
    //
    // schedule.setAvailability(availability + action);
    // return true;
    // }
}

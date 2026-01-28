package com.flipkart.business;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flipkart.bean.FlipFitSlot;
import com.flipkart.bean.FlipFitSchedule;
import com.flipkart.dao.FlipFitScheduleDAO;
public class FlipFitScheduleService implements FlipFitScheduleInterface {

    private final FlipFitGymCenterService gymCentreService = new FlipFitGymCenterService();
    private final FlipFitSlotService slotService = new FlipFitSlotService();
    private final FlipFitScheduleDAO flipFitScheduleDAO = new FlipFitScheduleDAO();

    public FlipFitSchedule createSchedule(Date date, String slotId){
        String centreID = slotService.getSlotByID(slotId).getCenterID();
        int availability = gymCentreService.getGymCentreById(centreID).getCapacity();
        FlipFitSchedule schedule = new FlipFitSchedule(date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), slotId, availability);
        flipFitScheduleDAO.addSchedule(schedule);

        return schedule;
    }

    public FlipFitSchedule getScheduleByDateAndSlotId(String SlotId, Date date){
        List<FlipFitSchedule> scheduleList = flipFitScheduleDAO.getAllScheduleByDate(date);
        for(FlipFitSchedule schedule: scheduleList){
            if(schedule.getSlotId().equals(SlotId))
                return schedule;
        }

        return null;
    }

    public boolean modifySchedule(String scheduleId,int action){
        return flipFitScheduleDAO.modifySchedule(scheduleId, action);
    }

    public List<FlipFitSchedule> checkAvailability(String centreID, Date date){
        List<FlipFitSlot> allSlotsForGym = slotService.getAllSlotsByCentre(centreID);
        List<FlipFitSchedule> allAvailableSchedules = new ArrayList<>();
        for(FlipFitSlot slot : allSlotsForGym){
            String slotId = slot.getSlotId();
            FlipFitSchedule schedule = getOrCreateSchedule(slotId, date);
            if(schedule.getAvailability() > 0)
                allAvailableSchedules.add(schedule);
        }

        return allAvailableSchedules;
    }

    public List<FlipFitSlot> getAllAvailableSlotsByDate(String centreID, Date date) {
        List<FlipFitSlot> allSlotsOfThatCentre = slotService.getAllSlotsByCentre(centreID);
        List<FlipFitSlot> response = slotService.getAllSlotsByCentre(centreID);
        for(FlipFitSlot slot: allSlotsOfThatCentre){
            for(FlipFitSchedule schedule: flipFitScheduleDAO.getAllScheduleByDate(date)){
                if (slotService.getSlotByID(schedule.getSlotId()).getCenterID().equals(centreID)){
                    if(schedule.getAvailability() <= 0){
                        response.remove(slot);
                    }
                }
            }
        }
        return response;
    }

    public FlipFitSchedule getSchedule(String scheduleID){
        return flipFitScheduleDAO.getSchedule(scheduleID);
    }

    public FlipFitSchedule getOrCreateSchedule(String slotId, Date date) {
        FlipFitSchedule schedule = getScheduleByDateAndSlotId(slotId, date);
        if( schedule == null ){
            return createSchedule(date,slotId);
        }
        return schedule;

    }


}

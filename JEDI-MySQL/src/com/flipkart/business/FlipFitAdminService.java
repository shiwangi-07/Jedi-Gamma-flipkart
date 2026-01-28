package com.flipkart.business;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.dao.FlipFitAdminDAO;

import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminService implements FlipFitAdminInterface {

    FlipFitAdminDAO flipFitAdminDAO = new FlipFitAdminDAO();

    private List<FlipFitGymOwner> pendinGymOwnerList = new ArrayList<>();
    private List<FlipFitGymCenter> pendinGymCentreList = new ArrayList<>();

    public void approveGymCenter(String gymCentreId, boolean isApproved) {
        flipFitAdminDAO.validateGymCentre(gymCentreId, isApproved);
    }

    public void approveGymOwner(String gymOwnerId, boolean isApproved) {
        flipFitAdminDAO.validateGymOwner(gymOwnerId, isApproved);
    }

    public List<FlipFitGymCenter> viewPendingGymCentres() {
        pendinGymCentreList = flipFitAdminDAO.getPendingGymCentres();
        return pendinGymCentreList;
    }

    public List<FlipFitGymOwner> viewPendingGymOwners() {
        System.out.println("Viewing pending Gym Owner Approvals: ");
        pendinGymOwnerList = flipFitAdminDAO.getPendingGymOwners();
        return pendinGymOwnerList;
    }

    public boolean isUserValid(String userName, String password) {
        return flipFitAdminDAO.isUserValid(userName, password);
    }

    public void registerAdmin(String userName, String password, String email) {
        flipFitAdminDAO.registerAdmin(userName, password, email);
    }

}

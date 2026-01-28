package com.flipkart.dao;

import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

public class FlipFitAdminDAO implements FlipFitAdminDAOInterface {

    private static FlipFitGymOwnerDAO flipFitGymOwnerDAO = new FlipFitGymOwnerDAO();
    private static FlipFitGymCenterDAO gymCentreDAO = new FlipFitGymCenterDAO();


    public List<FlipFitGymOwner> getPendingGymOwners() {
        return flipFitGymOwnerDAO.getPendingGymOwnerList();
    }

    public void validateGymOwner(String gymOwnerId, boolean isApproved) {
        flipFitGymOwnerDAO.validateGymOwner(gymOwnerId, isApproved);
    }

    public void validateGymCentre(String gymCentreId, boolean isApproved) {
        gymCentreDAO.validateGymCentre(gymCentreId, isApproved);
    }

    public List<FlipFitGymCenter> getPendingGymCentres() {
        return gymCentreDAO.getPendingGymCentreList();
    }

    public void registerAdmin(String userName, String password, String email) {
        try {
            java.sql.Connection conn = com.flipkart.utils.DatabaseConnector.connect();
            java.sql.PreparedStatement stmt = conn.prepareStatement(com.flipkart.constants.SQLConstants.ADD_NEW_ADMIN);
            stmt.setString(1, java.util.UUID.randomUUID().toString());
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, email);

            stmt.executeUpdate();
            stmt.close();
        } catch (java.sql.SQLException exp) {
            exp.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUserValid(String userName, String password) {
        try {
            java.sql.Connection conn = com.flipkart.utils.DatabaseConnector.connect();
            java.sql.PreparedStatement stmt = conn.prepareStatement(com.flipkart.constants.SQLConstants.ADMIN_LOGIN_QUERY);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (java.sql.SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }

    public com.flipkart.bean.FlipFitAdmin getAdminById(String userName) {
        com.flipkart.bean.FlipFitAdmin admin = new com.flipkart.bean.FlipFitAdmin();
        try {
            java.sql.Connection conn = com.flipkart.utils.DatabaseConnector.connect();
            java.sql.PreparedStatement stmt = conn.prepareStatement(com.flipkart.constants.SQLConstants.GET_ADMIN_BY_ID);
            stmt.setString(1, userName);
            java.sql.ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                admin.setUserID(rs.getString("Id"));
                admin.setUserName(rs.getString("name"));
                admin.setPassword(rs.getString("password"));
                admin.setEmail(rs.getString("email"));
                admin.setRole(com.flipkart.bean.FlipFitRole.ADMIN);
            }
            stmt.close();
        } catch (java.sql.SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        return admin;
    }

}

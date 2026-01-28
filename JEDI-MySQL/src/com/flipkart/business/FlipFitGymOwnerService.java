package com.flipkart.business;

import com.flipkart.dao.FlipFitGymOwnerDAO;
import com.flipkart.bean.FlipFitGymOwner;

import java.util.List;

public class FlipFitGymOwnerService implements FlipFitGymOwnerInterface {
    private static final FlipFitGymOwnerDAO FLIP_FIT_GYM_OWNER_DAO = new FlipFitGymOwnerDAO();
    public void requestGymOwnerApproval(String gymOwnerId) {
        FLIP_FIT_GYM_OWNER_DAO.sendOwnerApprovalRequest(gymOwnerId);
    }

    public List<FlipFitGymOwner> viewAllGymOwners() {
        return FLIP_FIT_GYM_OWNER_DAO.getGymOwnerList();
    }

    public boolean loginGymOwner(String username,String password){
        return FLIP_FIT_GYM_OWNER_DAO.loginGymOwner(username,password);
    }

    public void registerGymOwner(String userId,String userName, String password, String email, String panNumber,String cardNumber) {
        FLIP_FIT_GYM_OWNER_DAO.registerGymOwner(new FlipFitGymOwner(userId,userName,email,password,panNumber,cardNumber));
    }
}
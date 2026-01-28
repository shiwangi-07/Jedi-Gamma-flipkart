package com.flipkart.bean;

/**
 * Represents an admin user in the FlipFit system, extending FlipFitUser.
 * This class provides a default constructor initializing admin details.
 */
public class FlipFitAdmin extends FlipFitUser {

    /**
     * Default constructor for creating an admin user.
     */
    public FlipFitAdmin() {
    }

    /**
     * Parameterized constructor for creating an admin user.
     * @param userId User ID of the admin
     * @param userName Username of the admin
     * @param email Email of the admin
     * @param password Password of the admin
     */
    public FlipFitAdmin(String userId, String userName, String email, String password) {
        super(userId, userName, email, password, FlipFitRole.ADMIN);
    }
}

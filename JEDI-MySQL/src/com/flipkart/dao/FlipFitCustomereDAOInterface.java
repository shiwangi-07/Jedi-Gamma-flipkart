package com.flipkart.dao;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.UserInvalidException;

public interface FlipFitCustomereDAOInterface {

    void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException;

    boolean isUserValid(String userName, String password) throws UserInvalidException;

    FlipFitCustomer getCustomerById(String userName);

    /**
     * Updates the password in the database for a specific user
     * @return true if update was successful, false otherwise
     */
    boolean changePassword(String userName, String oldPassword, String newPassword);
}
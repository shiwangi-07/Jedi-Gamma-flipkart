package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

/**
 * Exception thrown when GymOwner not found
 */
public class GymOwnerNotFoundException extends Exception {
    public GymOwnerNotFoundException(String gymOwnerId){
        super(RED_COLOR+"Gym Owner with ID: " + gymOwnerId + " does not exist."+RESET_COLOR);
    }
}
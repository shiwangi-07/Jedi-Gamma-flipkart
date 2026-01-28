package com.flipkart.exceptions;

import static com.flipkart.constants.Constants.RED_COLOR;
import static com.flipkart.constants.Constants.RESET_COLOR;

/**
 * Exception thrown when GymCentre not found
 */
public class CenterNotFoundException extends RuntimeException{
    public CenterNotFoundException(String gymId){
        super(RED_COLOR+"Gym Centre" + gymId + " not found!"+RESET_COLOR);
    }
}
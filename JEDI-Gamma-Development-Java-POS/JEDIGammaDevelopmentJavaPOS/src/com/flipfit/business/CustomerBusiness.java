/**
 * 
 */
package com.flipfit.business;

import java.util.Date;

/**
 * 
 */
public interface CustomerBusiness {
	public void viewCentres(String city);
    public boolean viewAvailability(Date date, int centerId);
    public boolean bookSlot(int slotId, Date date, int userId);
    public boolean cancelBooking(int bookingId);
    public boolean makePayment(int userId, double amount);
    public boolean joinWaitList(int slotId, int userId);
}

/**
 * 
 */
package com.flipfit.business;

/**
 * 
 */
public interface UserBusiness {
	public boolean login(String email, String password);
	public void logout(String userID);
	public boolean register(com.flipfit.bean.User user);
}

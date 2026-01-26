package com.flipfit.business;

import java.util.ArrayList;
import java.util.List;
import com.flipfit.bean.User;

public class UserBusinessImpl implements UserBusiness {
    
    // Simulating a database
    private List<User> userList = new ArrayList<>();

    public UserBusinessImpl() {
        // Add a default user for testing
        User u1 = new User();
        u1.setUserId("101");
        u1.setEmail("testuser@gmail.com");
        u1.setPassword("password123");
        userList.add(u1);
    }

    @Override
    public boolean login(String email, String password) {
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                 System.out.println("Login Success! Welcome " + user.getEmail());
                 return true;
            }
        }
        System.out.println("Login Failed: Invalid credentials.");
        return false;
    }

    @Override
    public void logout(String userId) {
        System.out.println("User " + userId + " has been logged out.");
    }

    @Override
    public boolean register(User user) {
        userList.add(user);
        System.out.println("User " + user.getEmail() + " registered successfully!");
        return true;
    }
}
package com.flipkart.client;

import com.flipkart.bean.FlipFitRole;
import com.flipkart.dao.FlipFitCustomerDAO;
import com.flipkart.dao.FlipFitCustomereDAOInterface;
import com.flipkart.exceptions.LoginFailedException;

import java.text.ParseException;
import java.util.Scanner;

public class FlipFitApplication {
    public static Scanner scanner = new Scanner(System.in);
    private static FlipFitAdminMenu adminClient = new FlipFitAdminMenu();
    private static FlipFitCustomerMenu customerClient = new FlipFitCustomerMenu();
    private static FlipFitGymOwnerMenu gymOwnerClient = new FlipFitGymOwnerMenu();

    // Injecting DAO to handle the password change logic
    private static FlipFitCustomereDAOInterface customerDao = new FlipFitCustomerDAO();

    private static void mainPage() {
        System.out.println("\n1. Login\n2. Registration\n3. Change Password\n4. Exit");
        System.out.print("Enter Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registration();
                break;
            case 3:
                changepassword();
                break;
            case 4:
                System.out.println("Thanks for visiting!");
                System.exit(0);
                return;
            default:
                System.out.println("Invalid choice selected");
                break;
        }
        mainPage();
    }

    private static void login() {
        try {
            System.out.println("Choose your Role:");
            System.out.println("1. Admin\n2. Gym Owner\n3. Customer");
            int roleChoice = scanner.nextInt();

            FlipFitRole role = null;
            switch (roleChoice) {
                case 1: role = FlipFitRole.ADMIN; break;
                case 2: role = FlipFitRole.GYM_OWNER; break;
                case 3: role = FlipFitRole.CUSTOMER; break;
                default:
                    System.out.println("Invalid Option Selected");
                    return;
            }

            System.out.println("Enter your Username");
            String userName = scanner.next();
            System.out.println("Enter your Password");
            String password = scanner.next();

            switch (role) {
                case ADMIN: adminClient.adminLogin(userName, password); break;
                case GYM_OWNER: gymOwnerClient.gymOwnerLogin(userName, password); break;
                case CUSTOMER: customerClient.customerLogin(userName, password); break;
            }
        } catch (IllegalArgumentException | ParseException | LoginFailedException e) {
            System.out.println("Login Failed: " + e.getMessage());
        }
    }

    private static void changepassword() {
        System.out.println("--- Change Password ---");
        System.out.print("Enter Username: ");
        String userName = scanner.next();

        System.out.print("Enter Current Password: ");
        String oldPassword = scanner.next();

        System.out.print("Enter New Password: ");
        String newPassword = scanner.next();

        System.out.print("Confirm New Password: ");
        String confirmPassword = scanner.next();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Error: New passwords do not match!");
            return;
        }

        // Logic call to DAO
        boolean success = customerDao.changePassword(userName, oldPassword, newPassword);

        if (success) {
            System.out.println("Your Password was changed Successfully :)");
        } else {
            System.out.println("Failed to change password. Invalid username or current password.");
        }
    }

    private static void registration() {
        try {
            System.out.println("Choose your Role:");
            System.out.println("1. Admin\n2. Gym Owner\n3. Customer");
            int roleChoice = scanner.nextInt();

            FlipFitRole role = null;
            switch (roleChoice) {
                case 1: role = FlipFitRole.ADMIN; break;
                case 2: role = FlipFitRole.GYM_OWNER; break;
                case 3: role = FlipFitRole.CUSTOMER; break;
                default:
                    System.out.println("Invalid Option Selected");
                    return;
            }

            switch (role) {
                case ADMIN: adminClient.register(); break;
                case CUSTOMER: customerClient.register(); break;
                case GYM_OWNER: gymOwnerClient.register(); break;
            }
        } catch (IllegalArgumentException | ParseException e) {
            System.out.println("Registration Error.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to FlipFit Application!\n");
        mainPage();
    }
}
package com.flipkart.client;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

import static com.flipkart.utils.Util.*;

public class FlipFitAdminMenu {
    private static FlipFitAdmin admin = new FlipFitAdmin();
    private static FlipFitAdminInterface adminService = new FlipFitAdminService();
    private static FlipFitGymOwnerInterface gymOwnerService = new FlipFitGymOwnerService();
    private static FlipFitGymCenterInterface gymCenterService = new FlipFitGymCenterService();
    public static Scanner scanner = new Scanner(System.in);

    public boolean adminLogin(String userName, String password) {
        if (adminService.isUserValid(userName, password)) {
            System.out.println("Successfully logged in");
            adminClientMainPage();
            return true;
        } else {
            System.out.println("Login Failed");
            return false;
        }
    }

    public void register() {
        System.out.println("Enter email:");
        String email = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();
        System.out.println("Enter username:");
        String userName = scanner.next();

        adminService.registerAdmin(userName, password, email);
        System.out.println("Admin registered successfully!");
    }

    private void handleGymOwnerApprovalRequests() {
        // print the list with indexes from 1
        System.out.println("Admin Approval for a Gym Owner ----------");

        System.out.println("(Press 0 to exit)\nEnter the Id of Gym Owner:");
        String requestGymOwnerId = scanner.next();

        if (requestGymOwnerId.equals("0")) {
            return;
        }

        System.out.println("1. Approve the request\n2. Reject the request");
        int choice = scanner.nextInt();
        if (choice == 1) {
            adminService.approveGymOwner(requestGymOwnerId, true);
            System.out.println("Approved Gym Owner");
        } else if (choice == 2) {
            adminService.approveGymOwner(requestGymOwnerId, false);
            System.out.println("Disapproved Gym Owner");
        }

    }

    private void handleGymCenterApprovalRequests() {
        // print the list with indexes from 1
        System.out.println("Press 0 to Exit or Choose the Gym Centre To Modify:");
        String requestGymCenterId = scanner.next();
        if (requestGymCenterId.equals("0"))
            return;
        // Now Admin will select an request and we will pop up with two
        System.out.println("1. Approve the request\n2. Reject the request\n");
        int choice = scanner.nextInt();
        if (choice == 1) {
            adminService.approveGymCenter(requestGymCenterId, true);
            System.out.println("Approved Gym Centre");
        } else if (choice == 2) {
            adminService.approveGymCenter(requestGymCenterId, false);
            System.out.println("Disapproved Gym Centre");
        }
        // modify the list
        // adminClientMainPage();
    }

    public void adminClientMainPage() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println("Welcome ADMIN to FlipFit Application\nLogin Time: " + currentTime);
        while (true) {
            System.out.println(
                    "0. View All Gym Owners\n1. View All Gym Centres\n2. View Pending GymOwner Approval Requests\n3. View Pending GymCenter's Approval Requests\n4. Go Back To Previous Menu");
            int pendingChoice = scanner.nextInt();
            switch (pendingChoice) {
                case 0:
                    List<FlipFitGymOwner> allGymOwners = gymOwnerService.viewAllGymOwners();
                    printOwnerList(allGymOwners);
                    break;
                case 1:
                    List<FlipFitGymCenter> allGymCenters = gymCenterService.viewAllGymCenters();
                    printGymCentres(allGymCenters);
                    break;
                case 2:
                    List<FlipFitGymOwner> pendingGymOwners = adminService.viewPendingGymOwners();
                    printOwnerList(pendingGymOwners);
                    if (!pendingGymOwners.isEmpty())
                        handleGymOwnerApprovalRequests();
                    break;

                case 3:
                    List<FlipFitGymCenter> pendingGymCentres = adminService.viewPendingGymCentres();// get
                                                                                                    // listGymCenterIds
                    printGymCentres(pendingGymCentres);
                    if (!pendingGymCentres.isEmpty())
                        handleGymCenterApprovalRequests();
                    break;
                case 4:
                    return;
            }
        }
    }
}

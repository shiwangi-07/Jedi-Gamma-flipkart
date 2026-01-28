package com.flipkart.client;

//imports
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.business.FlipFitCustomerInterface;
import com.flipkart.business.FlipFitCustomerService;
import com.flipkart.utils.Util;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import static com.flipkart.utils.Util.*;

public class FlipFitCustomerMenu {
    private FlipFitCustomerInterface customerService = new FlipFitCustomerService();
    Scanner scanner = new Scanner(System.in);

    public boolean customerLogin(String userName, String password) throws ParseException {
        if (customerService.isUserValid(userName, password)) {
            System.out.println("Successfully logged in");
            customerClientMainPage(userName);
        } else {
            System.out.println("UserName or password doesn't match");
            return false;
        }
        return true;
    }

    public void register() throws ParseException {
        System.out.println("Enter your Username");
        String userName = scanner.next();

        System.out.println("Enter your Password");
        String password = scanner.next();

        System.out.println("Enter your Email");
        String email = scanner.next();

        System.out.println("Enter your Phone Number");
        String phoneNumber = scanner.next();

        System.out.println("Enter your Card Number");
        String cardNumber = scanner.next();

        customerService.registerCustomer(userName, password, email, phoneNumber, cardNumber);
        customerClientMainPage(userName);
    }

    private void handleEmptyGymList(String userName) throws ParseException {
        System.out.println("Please choose an option:");
        System.out.println("1. Try a different location (Return to Book Slot Menu)");
        System.out.println("2. Exit to main customer menu");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                // Recalls bookSlotSubMenu, allowing the user to search again
                bookSlotSubMenu(userName);
                break;
            case 2:
                // Returns control flow to customerClientMainPage loop
                System.out.println("Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice, returning to main menu.");
                break;
        }
    }


    private void bookSlotSubMenu(String userName) throws ParseException {
        System.out.println("Provide Location to search : ");
        String location = scanner.next();
        List<FlipFitGymCenter> centreListByLocation = customerService.getAllGymCenterDetailsByCity(location);
        Util.printGymCentres(centreListByLocation);
        if (centreListByLocation.isEmpty()) {
            System.out.println(
                    "There are no available GYM Centres in " + location + ". Please Select some other location");
            //bookSlotSubMenu(userName);
            handleEmptyGymList(userName);
            return;
        }
        System.out.print("Choose a gymCentre ID to proceed: ");
        String chosenGym = scanner.next();
        Date date = selectDate();
        chooseSlot(chosenGym, userName, date, chosenGym);
    }

    private void chooseSlot(String gymCentreId, String userName, Date sqlDate, String centreId) throws ParseException {
        System.out.println("Choose from the Below Slots");
        List<FlipFitSlot> availableSlots = customerService.getAvailableSlots(gymCentreId, sqlDate);
        printSlots(availableSlots);
        if (availableSlots.isEmpty()) {
            System.out.println("There are no available slots in the " + gymCentreId + ". Please Select some other gym");
            bookSlotSubMenu(userName);
            return;
        }
        System.out.println("Enter SlotID");
        String slotID = scanner.next();
        if (!customerService.bookSlot(userName, sqlDate, slotID, centreId))
            chooseSlot(gymCentreId, userName, sqlDate, centreId);
    }

    private void printbookingsSubMenu(String userName) {
        System.out.println("Bookings : ");
        List<FlipFitBooking> allBookingList = customerService.getCustomerBookings(userName);
        System.out.println("-----");
        System.out.printf("%-8s\t", "BOOKING-ID");
        System.out.printf("%47s\t\n", "SCHEDULE-ID");
        System.out.println("-----");
        for (FlipFitBooking booking : allBookingList) {
            System.out.printf("%-8s\t", booking.getBookingID());
            System.out.printf("%-8s\t\n", booking.getScheduleID());
        }
        System.out.println("----");
    }

    private void cancelBookingSubMenu(String userName) {
        printbookingsSubMenu(userName);
        System.out.println("Select the Booking you want to cancel: ");
        String bookingId = scanner.next();
        customerService.cancelBookingbyID(bookingId);

    }

    private void viewScheduleSubMenu(String userName) throws ParseException {
        Date date = selectDate();
        List<UserPlan> userPlans = customerService.getCustomerPlan(userName, date);
        System.out.println("Schedule for " + date + " :");
        System.out.printf("%-15s\t%-15s\t%-15s\t\n", "Centre-ID", "Slot-ID", "Time");
        for (UserPlan plan : userPlans) {
            System.out.printf("%-15s\t%-15s\t%-15s\t\n", plan.getCentreID(), plan.getSlotId(), plan.getTime());
        }
    }

    public static void getCustomerMenu() {
        System.out.println("Welcome to Customer Menu");
    }

    public void customerClientMainPage(String userName) throws ParseException {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentTime.format(myFormat);
        System.out.println(
                "WELCOME " + userName + " !!\nPlease choose among the following options\nLogin TIME: " + currentTime);
        while (true) {
            System.out.println(
                    "1. View My Profile \n2. Book a slot in a Gym \n3. View Bookings\n4. Cancel Bookings\n5. Go Back to previous menu\n6. View My Schedule");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    FlipFitCustomer customer = customerService.viewMyProfile(userName);
                    printCustomerProfile(customer);
                    break;
                case 2:
                    bookSlotSubMenu(userName);
                    break;
                case 3:
                    printbookingsSubMenu(userName);
                    break;
                case 4:
                    cancelBookingSubMenu(userName);
                    break;
                case 5:
                    System.out.println("Taking back to previous menu");
                    return;
                case 6:
                    viewScheduleSubMenu(userName);
                    break;
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
        }
    }
}

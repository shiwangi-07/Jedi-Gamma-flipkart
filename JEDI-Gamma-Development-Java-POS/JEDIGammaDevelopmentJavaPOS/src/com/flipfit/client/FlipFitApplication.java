package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main Client Application for FlipFit
 */
public class FlipFitApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookingBusinessService bookingBusiness = new BookingBusinessService();
    private static final UserBusiness userBusiness = new UserBusinessImpl();

    private static final List<Slot> masterSlot = new ArrayList<>();
    private static final GymCentre gc1 = new GymCentre(1, "Mahadevapura", "06:00 - 21:00", 500);

    public static void main(String[] args) {
        initializeData();

        System.out.println("==========================================");
        System.out.println("      WELCOME TO FLIPFIT SYSTEMS");
        System.out.println("==========================================");

        mainPage();
    }

    private static void mainPage() {
        System.out.println("\n--- LANDING PAGE ---");
        System.out.println("1. Login\n2. Registration\n3. Exit");
        System.out.print("Choose option: ");
        int portalOption = scanner.nextInt();

        switch (portalOption) {
            case 1:
                login();
                break;
            case 2:
                registration();
                break;
            case 3:
                System.out.println("Thanks for visiting! Stay Healthy.");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again.");
        }
        mainPage();
    }

    private static void registration() {
        System.out.println("\n--- REGISTRATION ---");
        System.out.print("Enter Email: ");
        String regEmail = scanner.next();
        System.out.print("Enter Password: ");
        String regPass = scanner.next();
        System.out.print("Enter User ID (Integer): ");
        String regUserId = scanner.next();

        System.out.println("Choose Role: 1. GymAdmin 2. GymCustomer 3. GymOwner");
        int roleChoice = scanner.nextInt();
        String role = (roleChoice == 1) ? "GymAdmin" : "GymCustomer";

        User newUser = new User();
        newUser.setEmail(regEmail);
        newUser.setPassword(regPass);
        newUser.setUserId(regUserId);
        newUser.setRole(role);

        userBusiness.register(newUser);
        System.out.println("\n[SUCCESS] Registered as " + role + "! Please Login now.");
    }

    private static void login() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        if (userBusiness.login(email, password)) {
            String role = (email.toLowerCase().contains("admin")) ? "GymAdmin" : "GymCustomer";

            System.out.println("\nLogin Successful! Welcome, " + role);
            if (role.equals("GymAdmin")) {
                adminMenu();
            } else {
                customerMenu();
            }
        } else {
            System.out.println("\n[ERROR] Invalid Credentials.");
        }
    }

    private static void customerMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. View Available Slots");
            System.out.println("2. Book a Slot");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayMasterSlots(gc1.getSlots());
                    break;
                case 2:
                    handleBooking();
                    break;
                case 3:
                    handleCancellation();
                    break;
                case 4:
                    loggedIn = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View System Snapshot");
            System.out.println("2. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displaySystemState(gc1.getSlots(), bookingBusiness.getAllBookings());
                    break;
                case 2:
                    loggedIn = false;
                    System.out.println("Logging out Admin...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // --- LOGIC HELPERS ---

    private static void handleBooking() {
        System.out.print("Confirm User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter Slot ID to book: ");
        int slotId = scanner.nextInt();

        Slot selectedSlot = findSlotById(gc1.getSlots(), slotId);
        if (selectedSlot != null) {
            bookingBusiness.createBooking(userId, gc1.getCentreId(), selectedSlot, 1, gc1.getPrice());
        } else {
            System.out.println("\n[ERROR] Invalid Slot ID.");
        }
    }

    private static void handleCancellation() {
        System.out.print("Enter Booking ID to cancel: ");
        int bId = scanner.nextInt();
        boolean result = bookingBusiness.cancelBooking(bId, gc1.getSlots());
        if(!result) System.out.println("\n[ERROR] Cancellation failed. Check Booking ID.");
    }

    private static void initializeData() {
        // Adding slots with the new constructor format: (id, startTime, capacity)
        masterSlot.add(new Slot(1, 6, 2));
        masterSlot.add(new Slot(2, 7, 2));
        masterSlot.add(new Slot(3, 8, 2));
        masterSlot.add(new Slot(4, 18, 10));
        masterSlot.add(new Slot(5, 19, 10));
        masterSlot.add(new Slot(6, 20, 10));

        for(Slot s : masterSlot) {
            gc1.addSlot(s);
        }
    }

    private static Slot findSlotById(List<Slot> slots, int id) {
        for (Slot s : slots) {
            if (s.getSlotId() == id) return s;
        }
        return null;
    }

    private static void displaySystemState(List<Slot> slots, List<Booking> bookings) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                ADMIN SYSTEM SNAPSHOT");
        System.out.println("=".repeat(60));
        displayMasterSlots(slots);
        displayWaitingLists(slots);
        displayBookings(bookings);
        System.out.println("=".repeat(60));
    }

    private static void displayMasterSlots(List<Slot> slots) {
        System.out.println("\n[SLOT AVAILABILITY]");
        System.out.printf("%-10s %-10s %-12s %-15s\n", "ID", "Time", "Category", "Available");
        for (Slot s : slots) {
            String cat = (s.getStartTime() < 12) ? "Morning" : "Evening";
            System.out.printf("%-10d %-10d %-12s %-1d/%-1d Seats\n",
                    s.getSlotId(), s.getStartTime(), cat, s.getAvailableSeats(), s.getMaximumSeats());
        }
    }

    private static void displayWaitingLists(List<Slot> slots) {
        System.out.println("\n[WAITING LISTS]");
        System.out.printf("%-10s %-25s %-10s\n", "Slot ID", "Queue (Users)", "Count");
        for (Slot s : slots) {
            WaitingList wl = s.getWaitingList();
            System.out.printf("%-10d %-25s %-10d\n",
                    s.getSlotId(), wl.getWaitingQueue().toString(), wl.getQueueSize());
        }
    }

    private static void displayBookings(List<Booking> bookings) {
        System.out.println("\n[ACTIVE BOOKINGS]");
        if (bookings.isEmpty()) {
            System.out.println("No active bookings.");
            return;
        }
        System.out.printf("%-12s %-10s %-10s %-10s\n", "Booking ID", "User ID", "Slot ID", "Cost");
        for (Booking b : bookings) {
            System.out.printf("%-12d %-10d %-10d Rs. %-10.2f\n",
                    b.getBookingId(), b.getUserId(), b.getSlotId(), b.getTotalCost());
        }
    }
}
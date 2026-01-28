package com.flipkart.utils;

import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.bean.FlipFitSlot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {

    public static void printGymCentres(List<FlipFitGymCenter> centreListByLocation) {
        System.out.println("-----");
        System.out.printf("%-8s\t", "Centre-ID");
        System.out.printf("%-8s\t", "OWNER-ID");
        System.out.printf("%-8s\t", "CENTER-NAME");
        System.out.printf("%8s\t", "GST-IN");
        System.out.printf("%-8s\t", "CITY");
        System.out.printf("%-8s\t", "CAPACITY");
        System.out.printf("%-8s\t", "PRICE");
        System.out.printf("%-8s\t\n", "APPROVED");
        System.out.println("-----");
        for (FlipFitGymCenter gymCenter : centreListByLocation) {
            System.out.printf("%-8s\t", gymCenter.getGymCenterId());
            System.out.printf("%-8s\t", gymCenter.getOwnerID());
            System.out.printf("%-8s\t", gymCenter.getGymCenterName());
            System.out.printf("%-8s\t", gymCenter.getGstin());
            System.out.printf("%-8s\t", gymCenter.getCity());
            System.out.printf("%-8s\t", gymCenter.getCapacity());
            System.out.printf("%-8s\t", gymCenter.getPrice());
            System.out.printf("%-8s\t\n", gymCenter.isApproved());
        }
        System.out.println("-----");
    }

    public static void printOwnerList(List<FlipFitGymOwner> gymOwnerList) {
        System.out.println("-----");
        System.out.printf("%-8s\t", "ID");
        System.out.printf("%-8s\t", "NAME");
        System.out.printf("%-8s\t", "EMAIL-ID");
        System.out.printf("%11s\t", "PAN");
        System.out.printf("%23s\t\n", "IS-APPROVED");
        System.out.println("-----");
        System.out.println("");
        for (FlipFitGymOwner gymOwner : gymOwnerList) {
            System.out.printf("%-8s\t", gymOwner.getUserID());
            System.out.printf("%-8s\t", gymOwner.getUserName());
            System.out.printf("%-8s\t", gymOwner.getEmail());
            System.out.printf("%-8s\t", gymOwner.getPanNumber());
            if (gymOwner.isApproved()) {
                System.out.println("Yes\n");
            } else if (!gymOwner.isApproved()) {
                System.out.println("No\n");
            } else {
                System.out.println("Pending\n");
            }
        }
        System.out.println("----");
    }

    public static void printCustomerProfile(FlipFitCustomer customer) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("USER ID: " + customer.getUserID());
        System.out.println("USER NAME: " + customer.getUserName());
        System.out.println("EMAIL: " + customer.getEmail());
        System.out.println("CONTACT: " + customer.getCustomerPhone());
        System.out.println("CARD DETAILS: " + customer.getCardDetails());
        System.out.println("------------------------------------------------------------------------");
    }

    public static void printSlots(List<FlipFitSlot> slots) {
        System.out.println("-----");
        System.out.printf("%-8s\t", "SLOT-ID");
        System.out.printf("%-8s\t\n", "SLOT-TIME");
        System.out.println("----");
        for (FlipFitSlot slot : slots) {
            System.out.printf("%-8s\t", slot.getSlotId());
            System.out.printf("%-8s\t\n", slot.getTime());
        }
        System.out.println("----");
    }

    public static Date selectDate() throws ParseException {
        System.out.print("Enter Date (dd/MM/yyyy): ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String dateStr = scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(dateStr);
    }
}

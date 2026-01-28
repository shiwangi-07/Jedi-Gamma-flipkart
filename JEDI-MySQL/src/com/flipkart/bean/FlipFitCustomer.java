package com.flipkart.bean;

/**
 * Represents a customer in the FlipFit system, extending FlipFitUser.
 * Contains customer-specific details such as phone number and card details.
 */
public class FlipFitCustomer extends FlipFitUser {

    private String customerPhone;
    private String cardDetails;

    /**
     * Default constructor.
     */
    public FlipFitCustomer() {

    }

    /**
     * Parameterized constructor to initialize customer details.
     * @param userId User ID of the customer
     * @param userName Username of the customer
     * @param email Email of the customer
     * @param password Password of the customer
     * @param customerPhone Phone number of the customer
     * @param cardDetails Card details of the customer
     */
    public FlipFitCustomer(String userId, String userName, String email, String password, String customerPhone, String cardDetails) {
        super(userId, userName, email, password, FlipFitRole.CUSTOMER);
        this.customerPhone = customerPhone;
        this.cardDetails = cardDetails;
    }

    /**
     * Retrieves the phone number of the customer.
     * @return Customer's phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the phone number of the customer.
     * @param customerPhone Customer's phone number to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Retrieves the card details of the customer.
     * @return Customer's card details
     */
    public String getCardDetails() {
        return cardDetails;
    }

    /**
     * Sets the card details of the customer.
     * @param cardDetails Customer's card details to set
     */
    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }
}

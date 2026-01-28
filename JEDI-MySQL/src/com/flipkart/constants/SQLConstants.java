package com.flipkart.constants;

public class SQLConstants {

    // -------------------CHANGE PASSWORD---------------------

    // ------------------------ GYM OWNER ------------------------
    public static final String FETCH_ALL_GYM_OWNERS_QUERY = "SELECT * FROM FlipFitNew.GymOwner";
    public static final String FETCH_ALL_PENDING_GYM_OWNERS_QUERY = "SELECT * FROM FlipFitNew.GymOwner where isApproved = 0";
    public static final String SEND_GYM_OWNER_APPROVAL_REQ_QUERY = "UPDATE FlipFitNew.GymOwner SET isApproved = 0 WHERE Id =?;";
    public static final String UPDATE_PASSWORD_QUERY = "UPDATE Customer SET password = ? WHERE name = ? AND password = ?;";

    public static final String ADD_GYM_CENTRE_QUERY = "INSERT INTO FlipFitNew.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved, noOfSlots, openTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    // public static final String ADD_GYM_CENTRE_QUERY = "INSERT INTO
    // FlipFitNew.GymCentre (centreId, ownerId, centreName, gstin, city, capacity,
    // price, isApproved) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    // ------------ GYM OWNER ------------
    public static final String REGISTER_GYM_OWNER = "Insert into FlipFitNew.GymOwner values (?,?,?,?,?,?,?)";
    public static final String LOGIN_GYM_OWNER = "Select * from FlipFitNew.GymOwner where name=? and password=?";
    public static final String SQL_APPROVE_GYM_OWNER_BY_ID_QUERY = "Update FlipFitNew.GymOwner Set isApproved=? WHERE Id=?";
    // public static final String SQL_APPROVE_GYM_OWNER_BY_ID_QUERY ="Update
    // GymOwner Set isApproved=? WHERE Id=?";

    // ------------------------ GYM CENTRE ------------------------
    public static final String FETCH_ALL_GYM_CENTRES = "SELECT * FROM FlipFitNew.GymCentre";
    public static final String FETCH_GYM_CENTRES_BY_OWNER_ID = "SELECT * FROM FlipFitNew.GymCentre where ownerId = ?";
    public static final String FETCH_ALL_PENDING_GYM_CENTRES_QUERY = "SELECT * FROM FlipFitNew.GymCentre where isApproved = 0";
    public static final String SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY = "Update FlipFitNew.GymCentre Set isApproved=? WHERE centreId=?";
    public static final String FETCH_GYM_CENTRES_BY_CITY = "SELECT * FROM FlipFitNew.GymCentre where city = ?";

    public static final String FETCH_GYM_CENTRE_BY_ID = "SELECT * FROM FlipFitNew.GymCentre where centreId = ?";

    // public static final String SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY = "Update
    // GymCentre Set isApproved=? WHERE centreId=?";

    // ------------------------ CUSTOMER ------------------------
    // public static final String ADD_NEW_CUSTOMER = "INSERT INTO
    // FlipFitNew.GymCentre (userId,userName,password,email,phoneNumber,cardNumber)
    // VALUES (?, ?, ?, ?, ?, ?);";

    // ------- Customer ---------------
    public static final String CUSTOMER_LOGIN_QUERY = "SELECT * FROM FlipFitNew.Customer WHERE name = ? AND password = ?";
    public static final String ADD_NEW_CUSTOMER = "INSERT INTO FlipFitNew.Customer (Id,name,password,email,phone,cardDetails) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM FlipFitNew.Customer WHERE name = ?;";

    // ----------- Booking -----------
    public static final String GET_BOOKING_BY_CUSTOMER_ID = "Select * From FlipFitNew.Booking where userID = ?";
    public static final String CANCEL_BOOKING_BY_ID = "Delete from FlipFitNew.Booking where bookingId = ?";
    public static final String ADD_BOOKING = "INSERT INTO FlipFitNew.Booking (bookingId, userID, scheduleID) values( ?, ?, ?)";

    // ------------- Schedule -------------
    public static final String ADD_SCHEDULE = "INSERT INTO FlipFitNew.Schedule(scheduleId, date, slotId, availability ) values (?,?,?,?)";
    public static final String GET_SCHEDULES_BY_DATE = "SELECT * FROM FlipFitNew.Schedule WHERE date=?";
    public static final String GET_SCHEDULE_BY_ID = "SELECT * FROM FlipFitNew.Schedule WHERE scheduleId=?";
    public static final String MODIFY_SCHEDULE_AVAILABILITY = "UPDATE `FlipFitNew`.`Schedule` SET availability = ? WHERE (`scheduleId` = ?)";

    // ---------------- Slot ----------------
    public static final String FETCH_ALL_SLOTS = "SELECT * FROM FlipFitNew.Slot";
    public static final String FETCH_SLOT_BY_CENTRE = "SELECT * FROM FlipFitNew.Slot WHERE centreId=?";
    public static final String ADD_SLOT = "INSERT INTO FlipFitNew.Slot(slotId, centreId, time) values (?, ?, ?)";
    public static final String FETCH_SLOT_BY_ID = "SELECT * FROM FlipFitNew.Slot WHERE slotId=?";
    public static final String FETCH_SLOT_BY_ID_AND_CENTRE_ID = "SELECT * FROM FlipFitNew.Slot WHERE slotId=? AND centreId=?";

    public static final String GET_BOOKING_BY_BOOKING_ID = "Select * From FlipFitNew.Booking where bookingId = ?";

    public static final String GET_USERPLAN_BY_CUSTOMER_ID = "select * from slot join schedule where slot.slotId=schedule.slotId and schedule.scheduleId=?";

    // ------------------------ ADMIN ------------------------
    public static final String ADD_NEW_ADMIN = "INSERT INTO FlipFitNew.Admin (Id, name, password, email) VALUES (?, ?, ?, ?);";
    public static final String ADMIN_LOGIN_QUERY = "SELECT * FROM FlipFitNew.Admin WHERE name = ? AND password = ?";
    public static final String GET_ADMIN_BY_ID = "SELECT * FROM FlipFitNew.Admin WHERE name = ?;";
}
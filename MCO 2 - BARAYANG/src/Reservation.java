/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

/**
 * This class represents a reservation made by a guest for a room in a hotel.
 */
public class Reservation {

    /**
     * The name of the guest.
     * */
    private String guestName;

    /**
     * The check-in date.
     * */
    private int checkInDate;

    /**
     * The check-out date.
     * */
    private int checkOutDate;

    /**
     * Calls the Room Class.
     * */
    private Room room;

    /**
     * The total price of the reservation.
     * */
    private double totalPrice;

    /**
     * The discount code to apply.
     * */
    private String discountCode;

    // Constants for discount rates
    private static final double I_WORK_HERE_DISCOUNT = 0.10;
    private static final int STAY4_GET1_THRESHOLD = 5;
    private static final double PAYDAY_DISCOUNT = 0.07;

    /**
     * Initializes a new Reservation object.
     * @param guestName The name of the guest making the reservation.
     * @param checkInDate The check-in date.
     * @param checkOutDate The check-out date.
     * @param room The room being reserved.
     * @param totalPrice The total price of the reservation.
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, double totalPrice) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.totalPrice = totalPrice;
        this.discountCode = "";
    }

    /**
     * Retrieves the name of the guest associated with the reservation.
     * @return The guest's name.
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Retrieves the check-in date of the reservation.
     * @return The check-in date.
     */
    public int getCheckInDate() {
        return checkInDate;
    }

    /**
     * Retrieves the check-out date of the reservation.
     * @return The check-out date.
     */
    public int getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Retrieves the room associated with the reservation.
     * @return The reserved room.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Retrieves the total price of the reservation based on the number of nights and room price per night.
     * @return The total price of the reservation.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    /**
     * Applies a discount code to the reservation.
     * @param code The discount code.
     */
    public boolean applyDiscountCode(String code) {
        if (isValidDiscountCode(code)) {
            this.discountCode = code;
            applyDiscount(code);
            return true;
        } else {
            System.out.println("Invalid discount code.");
            return false;
        }
    }
    /**
     * Checks if a discount code is valid.
     * @param code The discount code.
     * @return True if the discount code is valid; false otherwise.
     */
    private boolean isValidDiscountCode(String code) {
        return code.equals("I_WORK_HERE") || code.equals("STAY4_GET1") || code.equals("PAYDAY");
    }

    /**
     * Applies a discount to the reservation based on the discount code.
     * @param code The discount code.
     */
    private void applyDiscount(String code) {
        switch (code) {
            case "I_WORK_HERE":
                applyIWorkHereDiscount();
                break;
            case "STAY4_GET1":
                applyStay4Get1Discount();
                break;
            case "PAYDAY":
                applyPaydayDiscount();
                break;
            default:
                break;
        }
    }

    private void applyIWorkHereDiscount() {
        totalPrice *= (1 - I_WORK_HERE_DISCOUNT);
    }

    private void applyStay4Get1Discount() {
        int duration = checkOutDate - checkInDate;
        if (duration >= STAY4_GET1_THRESHOLD) {
            totalPrice -= room.getPricePerNight(); // First night free
        }
    }

    private void applyPaydayDiscount() {
        if (checkInDate == 15 || checkOutDate == 15 || checkInDate == 30 || checkOutDate == 30) {
            totalPrice *= (1 - PAYDAY_DISCOUNT);
        }
    }

    /**
     * Displays details of the reservation including guest name, room name, dates, and total price.
     */
    public void displayReservationDetails() {
        System.out.println("Guest Name: " + getGuestName());
        System.out.println("Room Name: " + room.getName());
        System.out.println("Check-In Date: " + getCheckInDate());
        System.out.println("Check-Out Date: " + getCheckOutDate());
        System.out.println("Total Price: " + getTotalPrice());
    }
}
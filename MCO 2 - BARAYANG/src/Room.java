/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a room in a created hotel.
 */
public class Room {

    /**
     * The name of the room.
     * */
    private String name;

    /**
     * The price per night of the room.
     * */
    private double pricePerNight;

    /**
     * The type of the room.
     * */
    private RoomType type;

    /**
     * The list of available dates for booking the room.
     * */
    private ArrayList<Boolean> availability;

    /**
     * Initializes a new Room object with a given name and price per night.
     * @param name The name of the room.
     * @param pricePerNight The price per night for the room.
     * @param type The type of the room (Standard, Deluxe, Executive).
     */
    public Room(String name, double pricePerNight, RoomType type) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.type = type;
        this.availability = new ArrayList<>(Collections.nCopies(31, true));
    }

    /**
     * Retrieves the name of the room.
     * @return The name of the room.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price per night for the room.
     * @return The price per night.
     */
    public double getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Sets the price per night for the room.
     * @param pricePerNight The new price per night.
     */
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight * type.getPriceMultiplier();
    }

    /**
     * Retrieves the type of the room.
     * @return The type of the room.
     */
    public RoomType getType() {
        return type;
    }

    /**
     * Checks if the room is available for a given date range.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return True if the room is available; false otherwise.
     */
    public boolean isAvailable(int checkIn, int checkOut) {
        for (int i = checkIn - 1; i < checkOut - 1; i++) {
            if (!availability.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Marks the room as booked for a given date range.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     */
    public void bookRoom(int checkIn, int checkOut) {
        for (int i = checkIn - 1; i < checkOut - 1; i++) {
            availability.set(i, false);
        }
    }

    /**
     * Cancels the booking for a given date range, marking the room as available again.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     */
    public void cancelBooking(int checkIn, int checkOut) {
        for (int i = checkIn - 1; i < checkOut; i++) {
            availability.set(i, true);
        }
    }

    /**
     * Retrieves the availability status as a string.
     * @return The availability status as a string.
     */
    public String getAvailabilityString() {
        StringBuilder availabilityString = new StringBuilder();
        for (int i = 0; i < availability.size(); i++) {
            availabilityString.append(i + 1).append(": ").append(availability.get(i) ? "Available" : "Booked").append("\n");
        }
        return availabilityString.toString();
    }

    /**
     * Displays details of the room including name, price, and availability.
     */
    public void displayRoomDetails() {
        System.out.println("Room Name: " + name);
        System.out.println("Room Type: " + type.getTypeName());
        System.out.println("Price Per Night: " + pricePerNight);
        System.out.print("Availability: ");
        for (int i = 0; i < availability.size(); i++) {
            System.out.print((i + 1) + ": " + (availability.get(i) ? "Available " : "Not Available "));
        }
        System.out.println();
    }
}
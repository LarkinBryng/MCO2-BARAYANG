/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a hotel with rooms and reservations.
 */
public class Hotel {

    /**
     * The name of the hotel.
     * */
    private String name;

    /**
     * The list of rooms in the hotel.
     * */
    private ArrayList<Room> rooms;

    /**
     * The list of reservations in the hotel.
     * */
    private ArrayList<Reservation> reservations;

    /**
     * The base price of the rooms in the hotel.
     * */
    private double basePrice;

    /**
     * Initializes a new Hotel object with a given name.
     * @param name The name of the hotel.
     */
    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.basePrice = 1299.0;
    }

    /**
     * Gets the name of the hotel.
     * @return The name of the hotel.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the hotel.
     * @param name The new name of the hotel.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the total number of rooms in the hotel.
     * @return The total number of rooms.
     */
    public int getTotalRooms() {
        return rooms.size();
    }

    /**
     * Gets the base price of the hotel rooms.
     * @return The base price.
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Adds a room to the hotel with a given name.
     * @param roomName The name of the room to add.
     * @param type The type of the room.
     * @throws IllegalArgumentException if a room with the same name already exists.
     */
    public void addRoom(String roomName, RoomType type) {
        if (rooms.size() >= 50) {
            throw new IllegalArgumentException("Cannot add more than 50 rooms.");
        } else if (doesRoomExist(roomName) != null) {
            throw new IllegalArgumentException("Room with this name already exists.");
        }

        double newBasePrice = basePrice * type.getPriceMultiplier();
        Room room = new Room(roomName, newBasePrice, type);
        rooms.add(room);
    }

    /**
     * Removes a room from the hotel by its name and ensures that no active reservations exist.
     * @param roomName The name of the room to remove.
     * @throws IllegalStateException if there are active reservations.
     */
    public void removeRoom(String roomName) {
        Room room = doesRoomExist(roomName);
        if (room != null) {
            for (Reservation reservation : reservations) {
                if (reservation.getRoom().equals(room)) {
                    throw new IllegalStateException("Cannot remove room with active reservations.");
                }
            }
            rooms.remove(room);
        } else {
            throw new IllegalArgumentException("Room not found.");
        }
    }

    /**
     * Updates the base price for all rooms in the hotel.
     * @param newPrice The new base price.
     */
    public void updateBasePrice(double newPrice) {
        if (newPrice < 100.0) {
            System.out.println("Price must be at least 100.0.");
            return;
        }
        this.basePrice = newPrice;
        for (Room room : rooms) {
            room.setPricePerNight(newPrice);
        }
        System.out.println("Base price updated successfully!");
    }

    /**
     * Finds a reservation by the guest's name.
     * @param guestName The name of the guest.
     * @return The Reservation object if found; null otherwise.
     */
    public Reservation findReservationByGuestName(String guestName) {
        for (Reservation reservation : reservations) {
            if (reservation.getGuestName().equalsIgnoreCase(guestName)) {
                return reservation;
            }
        }
        return null;
    }

    /**
     * Removes a reservation from the hotel.
     * @param guestName The reservation to remove under the guest name.
     */
    public void removeReservation(String guestName) {
        Reservation reservationToRemove = findReservationByGuestName(guestName);
        if (reservationToRemove != null) {
            reservations.remove(reservationToRemove);
            Room reservedRoom = reservationToRemove.getRoom();
            int checkIn = reservationToRemove.getCheckInDate();
            int checkOut = reservationToRemove.getCheckOutDate();

            reservedRoom.cancelBooking(checkIn, checkOut); // Mark room as available again
        } else {
            System.out.println("Reservation not found.");
        }
    }

    /**
     * Checks if a room with a given name exists in the hotel.
     * @param name The name of the room to check.
     * @return The Room object if found; null otherwise.
     */
    public Room doesRoomExist(String name) {
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(name)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Gets the list of rooms in the hotel.
     * @return The list of rooms.
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Displays details of the hotel including its name, total rooms, and estimated earnings.
     */
    public void viewHotelDetails() {
        double estimatedEarnings = 0.0;
        for (Reservation reservation : reservations) {
            estimatedEarnings += reservation.getTotalPrice();
        }
        System.out.println("Hotel Name: " + name);
        System.out.println("Total Rooms: " + rooms.size());
        System.out.println("Estimated Earnings for the Month: " + estimatedEarnings);
    }

    /**
     * Displays and handles the low-level information options in the View Hotel.
     */
    public void viewLowLevelDetails() {
        System.out.println();
        System.out.println("Available Low-level Information:");
        System.out.println();
        System.out.println("1. View total number of available and booked rooms for a selected date");
        System.out.println("2. View information about a selected room");
        System.out.println("3. View information about a selected reservation");
        System.out.println();
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter the date (1-31): ");
                int date = scanner.nextInt();
                viewAvailableAndBookedRooms(date);
                break;
            case 2:
                System.out.print("Enter the room name: ");
                String roomName = scanner.next();
                viewRoomDetails(roomName);
                break;
            case 3:
                System.out.print("Enter the guest name: ");
                String guestName = scanner.next();
                viewReservationDetails(guestName);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Displays available and booked rooms.
     * @param date The date to check availability.
     */
    private void viewAvailableAndBookedRooms(int date) {
        int availableCount = 0;
        int bookedCount = 0;
        for (Room room : rooms) {
            if (room.isAvailable(date, date + 1)) {
                availableCount++;
            } else {
                bookedCount++;
            }
        }
        System.out.println("Available Rooms: " + availableCount);
        System.out.println("Booked Rooms: " + bookedCount);
    }

    /**
     * Displays a room's details.
     * @param roomName The name of the room.
     */
    private void viewRoomDetails(String roomName) {
        Room room = doesRoomExist(roomName);
        if (room != null) {
            room.displayRoomDetails();
        } else {
            System.out.println("Room not found.");
        }
    }

    /**
     * Displays details of a reservation.
     * @param guestName The name of the guest.
     */
    private void viewReservationDetails(String guestName) {
        Reservation reservation = findReservationByGuestName(guestName);
        if (reservation != null) {
            reservation.displayReservationDetails();
        } else {
            System.out.println("Reservation not found.");
        }
    }

    /**
     * Simulates booking a room for a guest with specified dates.
     * @param roomName The name of the room to book.
     * @param guestName The name of the guest booking the room.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @param discountCode The discount code to apply.
     * @throws IllegalArgumentException if an invalid discount code is provided.
     */
    public void simulateBooking(String roomName, String guestName, int checkIn, int checkOut, String discountCode) {
        if (checkIn < 1 || checkIn > 31 || checkOut < 1 || checkOut > 31 || checkIn >= checkOut) {
            System.out.println("Invalid check-in or check-out dates.");
            return;
        }
        Room room = doesRoomExist(roomName);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }
        if (!room.isAvailable(checkIn, checkOut)) {
            System.out.println("Room is not available for the selected dates.");
            return;
        }
        double totalPrice = calculateTotalPrice(room.getPricePerNight(), checkIn, checkOut);

        // Create a reservation
        Reservation reservation = new Reservation(guestName, checkIn, checkOut, room, totalPrice);
        boolean discountApplied = applyDiscounts(reservation, discountCode);

        if (!discountApplied) {
            throw new IllegalArgumentException("Invalid discount code.");
        }

        reservations.add(reservation);
        room.bookRoom(checkIn, checkOut);

        System.out.println("Booking successful. Total price: " + reservation.getTotalPrice());
    }

    /**
     * Calculates the total price for a stay based on the room rate and duration.
     * @param pricePerNight The price per night of the room.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     * @return The total price for the stay.
     */
    private double calculateTotalPrice(double pricePerNight, int checkIn, int checkOut) {
        int duration = checkOut - checkIn;
        double totalPrice = pricePerNight * duration;

        if ((checkIn >= 5 && checkIn <= 6) || (checkOut >= 5 && checkOut <= 6)) {
            totalPrice *= 1.2; // 120% of the base price for 5th-6th
        } else if ((checkIn >= 8 && checkIn <= 9) || (checkOut >= 8 && checkOut <= 9)) {
            totalPrice *= 0.7; // 70% of the base price for 8th-9th
        } else if ((checkIn >= 18 && checkIn <= 19) || (checkOut >= 18 && checkOut <= 19)) {
            totalPrice *= 1.5; // 150% of the base price for 18th-19th
        }

        return totalPrice;
    }

    /**
     * Applies discounts to a reservation.
     * @param reservation The reservation to apply discounts to.
     * @param discountCode The discount code provided by the guest.
     * @return True if a valid discount was applied, false otherwise.
     */
    private boolean applyDiscounts(Reservation reservation, String discountCode) {
        int checkIn = reservation.getCheckInDate();
        int checkOut = reservation.getCheckOutDate();

        // Check if payday discount applies
        if ((checkIn == 15 || checkOut == 15 || checkIn == 30 || checkOut == 30)) {
            reservation.applyDiscountCode("PAYDAY");
        }

        // Check if stay 4 get 1 discount applies
        int duration = checkOut - checkIn;
        if (duration >= 5) {
            reservation.applyDiscountCode("STAY4_GET1");
        }

        // Apply provided discount code
        if (discountCode != null && !discountCode.isEmpty()) {
            return reservation.applyDiscountCode(discountCode);
        }

        return true;
    }

    /**
     * Removes a hotel if there are no active reservations.
     * @throws IllegalStateException if there are active reservations in the hotel.
     */
    public void removeHotel() {
        HotelSystem.hotels.remove(this);
    }

    /**
     * Gets the list of reservations in the Hotel.
     * */
    public ArrayList<Reservation> getReservations(){
        return this.reservations;
    }


}
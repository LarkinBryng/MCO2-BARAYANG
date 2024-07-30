/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a system that manages multiple hotels.
 */
public class HotelSystem {

    /**
     * The list of hotels.
     * */
    public static ArrayList<Hotel> hotels;

    /**
     * Calls the RoomType interface.
     * */
    private RoomType roomType;

    /**
     * Initializes a new HotelSystem object with an empty list of hotels.
     */
    public HotelSystem() {
        this.hotels = new ArrayList<>();
    }

    /**
     * Creates a new hotel and adds it to the system if it doesn't already exist.
     * @param name The name of the hotel to create.
     */
    public void createHotel(String name) {
        if (doesHotelExist(name) != null) {
            System.out.println("Hotel with this name already exists.");
            return;
        }
        hotels.add(new Hotel(name));
    }

    /**
     * Retrieves the list of hotels managed by the system.
     * @return The list of hotels.
     */
    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    /**
     * Checks if a hotel with a given name already exists in the system.
     * @param name The name of the hotel to check.
     * @return The Hotel object if found; null otherwise.
     */
    public Hotel doesHotelExist(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(name)) {
                return hotel;
            }
        }
        return null;
    }

    /**
     * Views details of a specific hotel including its overall details and low-level information.
     * @param hotelName The name of the hotel to view.
     */
    public void viewHotel(String hotelName) {
        Hotel hotel = doesHotelExist(hotelName);
        if (hotel != null) {
            hotel.viewHotelDetails();
            hotel.viewLowLevelDetails();
        } else {
            System.out.println("Hotel not found.");
        }
    }

    /**
     * Manages a hotel such as renaming, adding/removing rooms, updating prices, etc.
     * @param hotelName The name of the hotel to manage.
     * @param scanner Scanner object for user input.
     */
    public void manageHotel(String hotelName, Scanner scanner) {
        Hotel hotel = doesHotelExist(hotelName);
        if (hotel != null) {
            boolean managehotel = true;
            while (managehotel) {
                System.out.println("---------------------");
                System.out.println("     Manage Hotel    ");
                System.out.println("---------------------");
                System.out.println("1. Change Hotel Name");
                System.out.println("2. Add Room");
                System.out.println("3. Remove Room");
                System.out.println("4. Update Base Price");
                System.out.println("5. Remove Reservation");
                System.out.println("6. Remove Hotel");
                System.out.println("7. Exit Manage Hotel");
                System.out.println("---------------------");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new hotel name: ");
                        String newHotelName = scanner.nextLine();
                        hotel.setName(newHotelName);
                        System.out.println("Hotel name changed successfully.");
                        break;
                    case 2:
                        System.out.println("Choose room type:");
                        System.out.println("1. Standard");
                        System.out.println("2. Deluxe");
                        System.out.println("3. Executive");
                        System.out.print("Enter your choice: ");
                        int roomTypeChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        RoomType roomType;
                        switch (roomTypeChoice) {
                            case 1:
                                roomType = new StandardRoom();
                                break;
                            case 2:
                                roomType = new DeluxeRoom();
                                break;
                            case 3:
                                roomType = new ExecutiveRoom();
                                break;
                            default:
                                System.out.println("Invalid room type choice.");
                                continue; // Restart loop
                        }
                        System.out.print("Enter room name: ");
                        String roomName = scanner.nextLine();
                        hotel.addRoom(roomName, roomType);
                        break;
                    case 3:
                        System.out.print("Enter room name: ");
                        String removeRoomName = scanner.nextLine();
                        hotel.removeRoom(removeRoomName);
                        break;
                    case 4:
                        System.out.print("Enter new base price: ");
                        double newPrice = scanner.nextDouble();
                        hotel.updateBasePrice(newPrice);
                        break;
                    case 5:
                        System.out.print("Enter guest name to remove reservation: ");
                        String guestName = scanner.nextLine();
                        Reservation reservation = hotel.findReservationByGuestName(guestName);
                        if (reservation != null) {
                            hotel.removeReservation(guestName);

                            System.out.println("Reservation removed successfully.");
                        } else {
                            System.out.println("Reservation not found.");
                        }
                        break;
                    case 6:
                        hotels.remove(hotel);
                        System.out.println("Hotel removed successfully.");
                        managehotel = false;
                        break;

                    case 7:
                        managehotel = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Hotel not found.");
        }
    }

    /**
     * Simulates booking for a room in a specific hotel.
     * @param hotelName The name of the hotel to book in.
     * @param roomName The name of the room to book.
     * @param guestName The name of the guest making the reservation.
     * @param checkIn The check-in date.
     * @param checkOut The check-out date.
     */
    public void simulateBooking(String hotelName, String roomName, String guestName, int checkIn, int checkOut, String discountCode) {
        Hotel hotel = doesHotelExist(hotelName);
        if (hotel != null) {
            hotel.simulateBooking(roomName, guestName, checkIn, checkOut, discountCode);
        } else {
            System.out.println("Hotel not found.");
        }
    }
}

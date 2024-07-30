/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HotelSystem hotelSystem = new HotelSystem();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("----------------------------");
            System.out.println("  Hotel Reservation System  ");
            System.out.println("----------------------------");
            System.out.println("1. Create Hotel");
            System.out.println("2. View Hotel");
            System.out.println("3. Manage Hotel");
            System.out.println("4. Simulate Booking");
            System.out.println("5. Exit");
            System.out.println("----------------------------");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter hotel name: ");
                    String hotelName = scanner.nextLine();
                    hotelSystem.createHotel(hotelName);
                    break;
                case 2:
                    System.out.print("Enter hotel name: ");
                    String viewName = scanner.nextLine();
                    hotelSystem.viewHotel(viewName);
                    break;
                case 3:
                    System.out.print("Enter hotel name: ");
                    String manageName = scanner.nextLine();
                    hotelSystem.manageHotel(manageName, scanner);
                    break;
                case 4:
                    System.out.print("Enter hotel name: ");
                    String bookingName = scanner.nextLine();
                    Hotel hotel = hotelSystem.doesHotelExist(bookingName);
                    if (hotel == null){
                        System.out.println("Hotel does not exist.");
                        break;
                    }
                    System.out.print("Enter room name: ");
                    String roomName = scanner.nextLine();
                    Room room = hotel.doesRoomExist(roomName);
                    if (room == null){
                        System.out.println("Room does not exist. ");
                        break;
                    }
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter check-in date (1-31): ");
                    int checkIn = scanner.nextInt();
                    System.out.print("Enter check-out date (1-31): ");
                    int checkOut = scanner.nextInt();
                    System.out.print("Enter discount code (if any): ");
                    String discountCode = scanner.next();
                    hotelSystem.simulateBooking(bookingName, roomName, guestName, checkIn, checkOut, discountCode);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
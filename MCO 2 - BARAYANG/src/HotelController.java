/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for handling interactions between the View and Model in the Hotel Management System.
 */
public class HotelController {

    /**
     * Calls the hotelSystem Class.
     * */
    private HotelSystem hotelSystem;

    /**
     * Calls the mainView Class.
     * */
    private MainView mainView;

    /**
     * Initializes a new HotelController with the specified view.
     * @param mainView The main view of the system.
     */
    public HotelController(MainView mainView) {
        this.hotelSystem = new HotelSystem();
        this.mainView = mainView;

        this.mainView.addCreateHotelListener(new CreateHotelListener());
        this.mainView.addViewHotelListener(new ViewHotelListener());
        this.mainView.addManageHotelListener(new ManageHotelListener());
        this.mainView.addSimulateBookingListener(new SimulateBookingListener());
        this.mainView.addExitListener(new ExitListener());
    }

    /**
     * Listener for creating a new hotel.
     */
    class CreateHotelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String hotelName = JOptionPane.showInputDialog("Enter Hotel Name:");
            if (hotelName == null || hotelName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hotel name cannot be empty.");
                return;
            }
            if (hotelSystem.doesHotelExist(hotelName) != null) {
                JOptionPane.showMessageDialog(null, "Hotel with this name already exists.");
                return;
            }
            hotelSystem.createHotel(hotelName);
            JOptionPane.showMessageDialog(null, "Hotel " + hotelName + " created successfully.");
        }
    }

    /**
     * Listener for viewing a hotel's details.
     */
    class ViewHotelListener implements ActionListener {

        /**
         * Handles the action of viewing a hotel's details.
         * @param e The action event.
         */
        public void actionPerformed(ActionEvent e) {
            String hotelName = JOptionPane.showInputDialog("Enter Hotel Name to View:");
            Hotel hotel = hotelSystem.doesHotelExist(hotelName);
            if (hotel == null) {
                JOptionPane.showMessageDialog(null, "Hotel not found.");
                return;
            }
            showHotelDetails(hotel);
        }

        /**
         * Displays the details of a given hotel.
         * @param hotel The hotel.
         */
        private void showHotelDetails(Hotel hotel) {
            String details = "Hotel Name: " + hotel.getName() + "\n" +
                    "Total Rooms: " + hotel.getTotalRooms() + "\n" +
                    "Base Price: " + hotel.getBasePrice() + "\n";
            JOptionPane.showMessageDialog(null, details, "Hotel Details", JOptionPane.INFORMATION_MESSAGE);

            int option = JOptionPane.showConfirmDialog(null, "Do you want to view low-level details?");
            if (option == JOptionPane.YES_OPTION) {
                showLowLevelOptions(hotel);
            }
        }

        /**
         * Displays low-level options for a given hotel.
         * @param hotel The hotel to manage.
         */
        private void showLowLevelOptions(Hotel hotel) {
            String[] options = {"View total number of available and booked rooms for a selected date",
                    "View information about a selected room",
                    "View information about a selected reservation",
                    "Cancel"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Low-Level Details",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // View total number of available and booked rooms for a selected date
                    int date = Integer.parseInt(JOptionPane.showInputDialog("Enter date (1-31):"));
                    showRoomAvailability(hotel, date);
                    break;
                case 1: // View information about a selected room
                    String roomName = JOptionPane.showInputDialog("Enter room name:");
                    showRoomDetails(hotel, roomName);
                    break;
                case 2: // View information about a selected reservation
                    String guestName = JOptionPane.showInputDialog("Enter guest name:");
                    showReservationDetails(hotel, guestName);
                    break;
                default:
                    break;
            }
        }

        /**
         * Displays the availability of rooms in a hotel for a specific date.
         * @param hotel The hotel to check availability for.
         * @param date The date to check availability on.
         */
        private void showRoomAvailability(Hotel hotel, int date) {
            int availableCount = 0;
            int bookedCount = 0;
            for (Room room : hotel.getRooms()) {
                if (room.isAvailable(date, date + 1)) {
                    availableCount++;
                } else {
                    bookedCount++;
                }
            }
            String message = "Available Rooms: " + availableCount + "\n" +
                    "Booked Rooms: " + bookedCount;
            JOptionPane.showMessageDialog(null, message, "Room Availability", JOptionPane.INFORMATION_MESSAGE);
        }

        /**
         * Displays the details of a specific room in a hotel.
         * @param hotel The hotel containing the room.
         * @param roomName The name of the room.
         */
        private void showRoomDetails(Hotel hotel, String roomName) {
            Room room = hotel.doesRoomExist(roomName);
            if (room == null) {
                JOptionPane.showMessageDialog(null, "Room not found.");
                return;
            }

            String details = "Room Name: " + room.getName() + "\n" +
                    "Room Type: " + room.getType().getTypeName() + "\n" +
                    "Price Per Night: " + room.getPricePerNight() + "\n" +
                    "Availability: " + room.getAvailabilityString();
            JOptionPane.showMessageDialog(null, details, "Room Details", JOptionPane.INFORMATION_MESSAGE);
        }

        /**
         * Displays the details of a specific reservation in a hotel.
         * @param hotel The hotel containing the reservation.
         * @param guestName The name of the guest with the reservation.
         */
        private void showReservationDetails(Hotel hotel, String guestName) {
            Reservation reservation = hotel.findReservationByGuestName(guestName);
            if (reservation != null) {
                String details = "Guest Name: " + reservation.getGuestName() + "\n" +
                        "Room Name: " + reservation.getRoom().getName() + "\n" +
                        "Check-In Date: " + reservation.getCheckInDate() + "\n" +
                        "Check-Out Date: " + reservation.getCheckOutDate() + "\n" +
                        "Total Price: " + reservation.getTotalPrice() + "\n";
                JOptionPane.showMessageDialog(null, details, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Reservation not found.");
            }
        }
    }

    /**
     * Listener for managing a hotel's details.
     */
    class ManageHotelListener implements ActionListener {

        /**
         * Handles the action of managing a hotel's details.
         * @param e The action event.
         */
        public void actionPerformed(ActionEvent e) {
            String hotelName = JOptionPane.showInputDialog("Enter Hotel Name to Manage:");
            Hotel hotel = hotelSystem.doesHotelExist(hotelName);
            if (hotel == null) {
                JOptionPane.showMessageDialog(null, "Hotel not found.");
                return;
            }
            showManageOptions(hotel);
        }

        /**
         * Displays management options for a specified hotel.
         * @param hotel The hotel to manage.
         */
        private void showManageOptions(Hotel hotel) {
            String[] options = {"Change Hotel Name", "Add Room", "Remove Room", "Update Base Price", "Remove Reservation", "Remove Hotel", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Manage Hotel",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    changeHotelName(hotel);
                    break;
                case 1:
                    addRoom(hotel);
                    break;
                case 2:
                    removeRoom(hotel);
                    break;
                case 3:
                    updateBasePrice(hotel);
                    break;
                case 4:
                    removeReservation(hotel);
                    break;
                case 5:
                    removeHotel(hotel);
                    break;
                default:
                    break;
            }
        }

        /**
         * Changes the name of a specified hotel.
         * @param hotel The hotel to rename.
         */
        private void changeHotelName(Hotel hotel) {
            String newName = JOptionPane.showInputDialog("Enter new hotel name:");
            if (newName == null || newName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hotel name cannot be empty.");
                return;
            }
            hotel.setName(newName);
            JOptionPane.showMessageDialog(null, "Hotel name changed to " + newName);
        }

        /**
         * Adds a new room to a specified hotel.
         * @param hotel The hotel to add a room to.
         */
        private void addRoom(Hotel hotel) {
            String roomName = JOptionPane.showInputDialog("Enter room name:");
            if (roomName == null || roomName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Room name cannot be empty.");
                return;
            }
            String[] roomTypes = {"Standard", "Deluxe", "Executive"};
            int roomTypeChoice = JOptionPane.showOptionDialog(null, "Select room type:", "Room Type",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, roomTypes, roomTypes[0]);

            RoomType roomType;
            switch (roomTypeChoice) {
                case 0:
                    roomType = new StandardRoom();
                    break;
                case 1:
                    roomType = new DeluxeRoom();
                    break;
                case 2:
                    roomType = new ExecutiveRoom();
                    break;
                default:
                    return;
            }

            try {
                hotel.addRoom(roomName, roomType);
                JOptionPane.showMessageDialog(null, "Room " + roomName + " added as " + roomTypes[roomTypeChoice]);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        /**
         * Removes a room from a specified hotel.
         * @param hotel The hotel to remove a room from.
         */
        private void removeRoom(Hotel hotel) {
            String roomName = JOptionPane.showInputDialog("Enter room name to remove:");
            try {
                hotel.removeRoom(roomName);
                JOptionPane.showMessageDialog(null, "Room " + roomName + " removed.");
            } catch (IllegalArgumentException | IllegalStateException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        /**
         * Updates the base price of a specified hotel.
         * @param hotel The hotel to update the base price for.
         */
        private void updateBasePrice(Hotel hotel) {
            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new base price:"));
            hotel.updateBasePrice(newPrice);
            JOptionPane.showMessageDialog(null, "Base price updated to " + newPrice);
        }

        /**
         * Removes a reservation from a specified hotel.
         * @param hotel The hotel to remove a reservation from.
         */
        private void removeReservation(Hotel hotel) {
            String guestName = JOptionPane.showInputDialog("Enter guest name to remove reservation:");
            Reservation reservation = hotel.findReservationByGuestName(guestName);
            if (reservation != null) {
                hotel.removeReservation(guestName);
                JOptionPane.showMessageDialog(null, "Reservation for " + guestName + " removed.");
            } else {
                JOptionPane.showMessageDialog(null, "Reservation not found.");
            }
        }

        /**
         * Removes a specified hotel from the system.
         * @param hotel The hotel to remove.
         */
        private void removeHotel(Hotel hotel) {
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the hotel?", "Confirm Remove Hotel", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                for (Reservation reservation : hotel.getReservations()) {
                    if (reservation.getRoom().equals(hotel.getName())) {
                        throw new IllegalStateException("Cannot remove Hotel with active reservations.");
                    }
                    else {
                        hotel.removeHotel();
                        JOptionPane.showMessageDialog(null, "Hotel removed successfully.");
                    }
                }
            }
        }
    }

    /**
     * Listener for simulating a booking.
     */
    class SimulateBookingListener implements ActionListener {

        /**
         * Handles the action of simulating a booking in a hotel.
         * @param e The action event.
         */
        public void actionPerformed(ActionEvent e) {
            String hotelName = JOptionPane.showInputDialog("Enter Hotel Name:");
            Hotel hotel = hotelSystem.doesHotelExist(hotelName);
            if (hotel == null) {
                JOptionPane.showMessageDialog(null, "Hotel not found.");
                return;
            }

            String roomName = JOptionPane.showInputDialog("Enter Room Name:");
            Room room = hotel.doesRoomExist(roomName);
            if (room == null) {
                JOptionPane.showMessageDialog(null, "Room not found.");
                return;
            }

            String guestName = JOptionPane.showInputDialog("Enter Guest Name:");
            int checkIn = Integer.parseInt(JOptionPane.showInputDialog("Enter Check-In Date (1-31):"));
            int checkOut = Integer.parseInt(JOptionPane.showInputDialog("Enter Check-Out Date (1-31):"));
            String discountCode = JOptionPane.showInputDialog("Enter Discount Code (if any):");

            try {
                if (!room.isAvailable(checkIn, checkOut)) {
                    JOptionPane.showMessageDialog(null, "The room is not available for the selected dates.", "Booking Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                hotel.simulateBooking(roomName, guestName, checkIn, checkOut, discountCode);
                JOptionPane.showMessageDialog(null, "Booking successful.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Booking Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Listener for exiting the application.
     */
    class ExitListener implements ActionListener {

        /**
         * Handles the action of exiting the application.
         * @param e The action event.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}



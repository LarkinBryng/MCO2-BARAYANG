/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The main view for the Hotel Management System.
 */
public class MainView extends JFrame {

    /**
     * JButton for Create Hotel option.
     * */
    private JButton createHotelButton;

    /**
     * JButton for View Hotel option.
     * */
    private JButton viewHotelButton;

    /**
     * JButton for Manage Hotel option.
     * */
    private JButton manageHotelButton;

    /**
     * JButton for Simulate Booking option.
     * */
    private JButton simulateBookingButton;

    /**
     * JButton for Exit option.
     * */
    private JButton exitButton;

    /**
     * Initializes a new MainView.
     */
    public MainView() {
        // frame
        setTitle("Hotel Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        // Initialize options
        createHotelButton = new JButton("Create Hotel");
        viewHotelButton = new JButton("View Hotel");
        manageHotelButton = new JButton("Manage Hotel");
        simulateBookingButton = new JButton("Simulate Booking");
        exitButton = new JButton("Exit");

        // Add options to the frame
        add(new JLabel("Hotel Management", SwingConstants.CENTER));
        add(createHotelButton);
        add(viewHotelButton);
        add(manageHotelButton);
        add(simulateBookingButton);
        add(exitButton);
    }

    /**
     * Listener for creating a hotel.
     * */
    public void addCreateHotelListener(ActionListener listener) {
        createHotelButton.addActionListener(listener);
    }

    /**
     * Listener for viewing a hotel.
     * */
    public void addViewHotelListener(ActionListener listener) {
        viewHotelButton.addActionListener(listener);
    }

    /**
     * Listener for managing a hotel.
     * */
    public void addManageHotelListener(ActionListener listener) {
        manageHotelButton.addActionListener(listener);
    }

    /**
     * Listener for simulating a booking.
     * */
    public void addSimulateBookingListener(ActionListener listener) {
        simulateBookingButton.addActionListener(listener);
    }

    /**
     * Listener for exiting the application.
     */
    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
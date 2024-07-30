/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 */

public class MainGUI {
    public static void main(String[] args) {
        // Initialize the View and Controller
        MainView mainView = new MainView();
        HotelController hotelController = new HotelController(mainView);
        mainView.setVisible(true);
    }
}

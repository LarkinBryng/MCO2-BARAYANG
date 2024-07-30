/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

/**
 * Implementation of RoomType for Deluxe rooms.
 */
public class DeluxeRoom implements RoomType{

    /**
     * Gets the name of the room type.
     * @return The name of the room type.
     */
    @Override
    public String getTypeName() {
        return "Deluxe";
    }

    /**
     * Gets the price multiplier for the room type.
     * @return The price multiplier.
     */
    @Override
    public double getPriceMultiplier() {
        return 1.20; // Deluxe room's base price multiplier
    }
}

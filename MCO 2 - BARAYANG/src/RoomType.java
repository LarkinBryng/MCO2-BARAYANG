/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

/**
 * Interface representing different types of rooms.
 */
public interface RoomType {

    /**
     * Gets the name of the room type.
     * @return The name of the room type.
     */
    String getTypeName();

    /**
     * Gets the price multiplier for the room type.
     * @return The price multiplier.
     */
    double getPriceMultiplier();

}
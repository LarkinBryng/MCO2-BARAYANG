/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

/**
 * Implementation of RoomType for Standard rooms.
 */
public class StandardRoom implements RoomType{

    /**
     * Gets the name of the room type.
     * @return The name of the room type.
     */
    @Override
    public String getTypeName() {
        return "Standard";
    }

    /**
     * Gets the price multiplier for the room type.
     * @return The price multiplier.
     */
    @Override
    public double getPriceMultiplier() {
        return 1.0; // Standard room's base price multiplier
    }
}
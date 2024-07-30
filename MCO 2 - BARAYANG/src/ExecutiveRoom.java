/**
 * Author: Larkin Patrick C. Barayang
 * Section: S14
 * */

/**
 * Implementation of RoomType for Executive rooms.
 */
public class ExecutiveRoom implements RoomType{

    /**
     * Gets the name of the room type.
     * @return The name of the room type.
     */
    @Override
    public String getTypeName() {
        return "Executive";
    }

    /**
     * Gets the price multiplier for the room type.
     * @return The price multiplier.
     */
    @Override
    public double getPriceMultiplier() {
        return 1.35; // Executive room's base price multiplier
    }
}
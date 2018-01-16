
/**
 * Write a description of class TemperatureUnit here.
 *
 * @author Mary
 * @version 1/15/18
 */
public class TemperatureUnit extends Unit
{
    private static final double CUBIC_FT_RATE = 1.0;
    private static final String TYPE = "temperature";
    
    /**
     * Constructor for objects of class TemperatureUnit
     */
    public TemperatureUnit(Location location, int length, int width, int height){
        super(location, length, width, height);
    }
    
   
    /**
     * Calculate the standard price of the unit.
     *
     * @return    the price of the unit
     */
    public double calcStandardPrice(){
        double standardPrice = getLocation().getBasePrice() + CUBIC_FT_RATE * getLength() *getWidth() * getHeight();
        return standardPrice;
    }
    
    /**
     * Get the type of the the unit
     *
     * @return    the type of the unit
     */
    public String getType(){
        return TYPE;
    }
}

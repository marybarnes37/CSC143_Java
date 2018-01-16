
/**
 * Write a description of class HumidityUnit here.
 *
 * @author Mary
 * @version 1/15/18
 */
public class HumidityUnit extends Unit
{
    private static final double SQ_FT_RATE = 5.0;
    private static final String TYPE = "humidity";

    /**
     * Constructor for objects of class HumidityUnit
     */
    public HumidityUnit(Location location, int length, int width, int height ){
        // initialise instance variables
        super(location, length, width, height);
    }
    
    
    /**
     * Calculate the standard price of the unit.
     *
     * @return    the price of the unit
     */
    public double calcStandardPrice(){
        double standardPrice = getLocation().getBasePrice() + SQ_FT_RATE * getLength() *getWidth();
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

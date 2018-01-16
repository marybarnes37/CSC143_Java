
/**
 * Write a description of class RegularUnit here.
 *
 * @author Mary
 * @version 1/15/18
 */
public class RegularUnit extends Unit
{
    private static final double BASE_RATE = 75.0;
    private static final String TYPE = "regular";
    
    /**
     * Constructor for objects of class RegularUnit
     */
    public RegularUnit(Location location, int length, int width, int height){
        // initialise instance variables
        super(location, length, width, height);
    }
   
    
    /**
     * Calculate the standard price of the unit.
     *
     * @return    the price of the unit
     */
    public double calcStandardPrice(){
        double standardPrice = getLocation().getBasePrice() + BASE_RATE;
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

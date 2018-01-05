
/**
 * Write a description of class Location here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Location
{
    // instance variables
    private String designation;
    private Customer[] customers;
    private int customerCount;
    private Unit[][] units;
    private int length = 8;
    private int width = 8;
    private int height = 8;
    // static Map<String, int> stateCounter = new HashMap<String, int>(); // make this a hash table!! ie, Python dictionary

    /**
     * Constructor for objects of class Location
     */
    public Location(String state, String city, String strNumber)
    {
        // check preconditions, including 
        if (!state.matches("[A-Z]{2}") || !city.matches("[A-Z]{1}[\\w]+")) {
            throw new IllegalArgumentException("State must be two uppercase letters. City must begin with an uppercase letter and only contain letters. No spaces allowed.");
        }
        
        // // increment or initialize stateCounter
        // if (state in stateCounter) {
            // // increment counter
        // }
        // else {
            // // add state to stateCounter and initialize to 1
        // }
        
        //
        // int number = stateCounter[state];
        // // turn 'number' into a string
        // String strNumber = ;
        // // initialize instance variables
        designation = state + strNumber  + city;
        customers = new Customer[240];
        units = new Unit[12][20];
        Type type = Type.REGULAR;
        for (int row = 0; row < units.length; row++){
            if (row % 4 == 0) {
                length += 4;
                height += 2;
            }
            for (int unit = 0; unit < units[row].length; unit++){
                if (unit % 4 == 0) {
                    width += 4;
                }
                if (unit % 2 == 0) {
                    type = Type.REGULAR;
                }
                if (unit % 3 == 0) {
                    type = Type.HUMIDITY;
                }
                if (unit % 5 == 0) {
                    type = Type.TEMPERATURE;
                }
                units[row][unit] = new Unit(type, length, width, height);
            }
            width = 8;
        }
        
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getDesignation()
    {
        // put your code here
        return this.designation; 
    }
    
    public Unit getUnit( int row, int unit )
    {
        return this.units[row][unit];
    }
    public Customer getCustomer( int index )
    {
        return this.customers[index];
    }
    public int getCustCount()
    {
        return this.customerCount;
    }
    public void addCustomer( String name, String phone )
    {
        customers[customerCount] = new Customer(name, phone, customerCount);
        customerCount++;
    }
    public Unit[] getUnitsForCustomer( Customer customer )
    {
    }
    public void chargeRent()
    {
    }
    public Unit[] getEmptyUnits( Type type )
    {
        Unit[] emptyUnits = new Unit[240];
        int emptyCount;
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                if (!units[row][unit].isRented()) {
                    emptyUnits[emptyCount] = units[row][unit];
                    emptyCount++;
                }
            }
        }
        return emptyUnits;
    }
    public String toString()
    {
        return designation;
    }
}

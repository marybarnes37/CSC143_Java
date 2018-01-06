
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
    private Unit[][] units;
    private Customer[] customers;
    private int customerCount;
    // static Map<String, int> stateCounter = new HashMap<String, int>(); // make this a hash table!! ie, Python dictionary

    /**
     * Constructor for objects of class Location
     */
    public Location(String state, String city, String strNumber)
    {
        // check preconditions, including 
        if (!state.matches("[A-Z]{2}") || !city.matches("[A-Z]{1}[a-zA-Z]+$")) {
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
        // the following section initializes all units, creating a variety of type and size combinations. 
        Type type = Type.REGULAR;
        int length = 4;
        int width = 4;
        int height = 6;
        for (int row = 0; row < units.length; row++){
            if (row % 4 == 0) {
                length += 4; // rows 0-3 = 8ft; 4-7 = 12ft, 8-11 = 16ft
                height += 2; // rows 0-3 = 8ft; 4-7 = 10ft, 8-11 = 12ft
            }
            for (int unit = 0; unit < units[row].length; unit++){
                if (unit % 4 == 0) {
                    width += 4; // units 0-3 = 8ft; 4-7 = 12ft, 8-11 = 16ft, 12-15 = 20ft, 16-19 = 24ft
                }
                if (unit % 2 == 0) { // set units 4, 8, 14, 16 to type REGULAR; unit 17 will also be REGULAR
                    type = Type.REGULAR;
                }
                if (unit % 3 == 0) { // set units 3, 6, 9, 12, 18 to type HUMIDITY; units 7, 13, 19 will also be HUMIDITY
                     type = Type.HUMIDITY;
                }
                if (unit % 5 == 0) { // set units 0, 5, 10, 15 to type TEMPERATURE; units 1, 2, 11 will also be TEMPERATURE
                    type = Type.TEMPERATURE;
                }
                units[row][unit] = new Unit(type, length, width, height);
            }
            width = 4; // reset width to minimum after each row
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
        /**
     * An example of a method - replace this comment with your own
     *
     * @param  name  
     * @param phone
     * @return    
     * @throws  IllegalArgumentException
     */
    public void addCustomer( String name, String phone )
    {
        customers[customerCount] = new Customer(name, phone);
        customerCount++;
    }
    public Unit[] getUnitsForCustomer( Customer customer )
    {
        if (customer == null){
            throw new IllegalArgumentException("Customer must not be null");
        }
        Unit[] customersUnits = new Unit[240];
        int customersCount = 0;
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                if (units[row][unit].getCustomer() == customer) {
                    customersUnits[customersCount] = units[row][unit];
                    customersCount++;
                }
            }
        }
        return customersUnits;   
    }
    public void chargeRent()
    {
        double price;
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                if (units[row][unit].isRented()) {
                    price = units[row][unit].getPrice();
                    units[row][unit].getCustomer().charge(price);
                }
            }
        }    
    }
    public double[] getMonthlyRevenue()
    {
        double revenue = 0;
        double possibleRevenue = 0;
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                if (units[row][unit].isRented()) {
                    revenue += units[row][unit].getPrice();
                }
                possibleRevenue += units[row][unit].getStandardPrice();
            }
        } 
        return new double[]{revenue, possibleRevenue};
    }
    public Unit[] getEmptyUnits( )
    {
        Unit[] emptyUnits = new Unit[240];
        int emptyCount = 0;
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
    public Unit[] getEmptyUnits( Type type )
    {
        Unit[] emptyUnits = new Unit[240];
        int emptyCount = 0;
            for (int row = 0; row < units.length; row++){
                for (int unit = 0; unit < units[row].length; unit++){
                    if (!units[row][unit].isRented() & (units[row][unit].getType() == type)) {
                        emptyUnits[emptyCount] = units[row][unit];
                        emptyCount++;
                    }
                }
            }
        return emptyUnits;
    }
    public int countEmptyUnits( Unit[] arr){
        int emptyCount = 0;
        for (int unit = 0; unit < arr.length; unit++){
            if (arr[unit] != null){
                emptyCount++;
            }
        }
        return emptyCount;
    }
    public String toString()
    {
        double[] revenue = getMonthlyRevenue();
        return ("designation: " + designation + "\ncustomer_count: " + customerCount + "\ntotal_units: " 
                + (units.length * units[0].length) + "\nempty_units: " + countEmptyUnits(getEmptyUnits()) +
                "\ncurrent_monthly_revenue: " + revenue[0] + "\npossible_monthly_revenue: " + revenue[1]);
    }
}

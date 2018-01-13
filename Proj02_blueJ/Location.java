import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.NumberFormat;

/**
 * A storage location with 240 storage units and up to 240 customers. 
 *
 * @author Mary
 * @version 1/8/2018
 * 
 *
 */
public class Location
{
    // instance variables
    private String designation;
    private Unit[][] units;
    private Customer[] customers;
    private int customerCount;
    private static final int LENGTH_WIDTH_BASE = 4;
    private static final int HEIGHT_BASE = 6;
    // static Map<String, int> stateCounter = new HashMap<String, int>(); // make this a hash table!! ie, Python dictionary

    /**
     * Constructor for objects of class Location
     */
    public Location(String state, String city, String strNumber) throws FileNotFoundException
    {
        // check preconditions, including 
        if (!state.matches("[A-Z]{2}") || !city.matches("[A-Z]{1}[a-zA-Z]+$")) {
            throw new IllegalArgumentException("State must be two uppercase letters. City must begin with an uppercase letter and only contain letters. No spaces allowed.");
        }
        if (!strNumber.matches("[0-9]{2}")) {
            throw new IllegalArgumentException("Number must be two digits between 00-99.");
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
        Unit.Type type = Unit.Type.REGULAR;
        int length = LENGTH_WIDTH_BASE;
        int width = LENGTH_WIDTH_BASE;
        int height = HEIGHT_BASE;
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
                    type = Unit.Type.REGULAR;
                }
                if (unit % 3 == 0) { // set units 3, 6, 9, 12, 18 to type HUMIDITY; units 7, 13, 19 will also be HUMIDITY
                     type = Unit.Type.HUMIDITY;
                }
                if (unit % 5 == 0) { // set units 0, 5, 10, 15 to type TEMPERATURE; units 1, 2, 11 will also be TEMPERATURE
                    type = Unit.Type.TEMPERATURE;
                }
                units[row][unit] = new Unit(type, length, width, height);
            }
            width = LENGTH_WIDTH_BASE; // reset width to minimum after each row
        }
        // add customers to customer list
        Scanner input = new Scanner( new File("customers.txt"));
        while (input.hasNextLine()) {
            String[] line = input.nextLine().split(",");
            String name = line[0];
            String phone = line[1].replaceAll("\\s","");
            customers[customerCount] = new Customer(name, phone);
            customerCount++;
            
        }
        
    }

    
    /**
     * Get the location's designation. 
     *
     * @return  the designation
     */
    public String getDesignation()
    {
        // put your code here
        return this.designation; 
    }
    
    
     /**
     * Get the unit at the specified location.
     *
     * @param row the row number
     * @param unit the unit number
     * @return  the unit specified
     */
    public Unit getUnit( int row, int unit )
    {
        return this.units[row][unit];
    }
    
    
     /**
     * Get the specified customer. 
     *
     * @param index the index of the customer
     * @return  the specified customer of x and y
     */
    public Customer getCustomer( int index )
    {
        return this.customers[index];
    }
    
    
     /**
     * Get the customer count. 
     *
     * @return  the customer count
     */
    public int getCustCount()
    {
        return this.customerCount;
    }
    
    
    /**
     * Add a customer. 
     *
     * @param name the first and last name of the customer in format John Smith
     * @param phone the phone number of the customer in format ##########
     * @throws  IllegalArgumentException
     */
    public void addCustomer( String name, String phone )
    {
        customers[customerCount] = new Customer(name, phone);
        customerCount++;
    }
    
    
     /**
     * Get the units for the specified customer. 
     *
     * @param customer the customer of interest
     * @return  the units associated with the specified customer
     */
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
    
    
     /**
     * Charge rent for all units belonging to the location. 
     *
     * @param y a sample parameter for a method
     * @return  the sum of x and y
     */
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
    
    
     /**
     * Get actual and potential monthly revenue.
     *
     * @return  array including actual revenue and possible revenue
     */
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
    
    
     /**
     * Get the empty (unrented) units.
     *
     * @return  array of empty units
     */
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
    
        
     /**
     * Get the empty (unrented) units of a specified type.  
     *
     * @param  type  the type of unit
     * @return    array of empty units
     */    
    public Unit[] getEmptyUnits( Unit.Type type )
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
 
    
     /**
     * Get the number of null units in an array. 
     *
     * @param  units  the array of units to be counted
     * @return    the number of empty units
     */
    public int countNullUnits( Unit[] units){
        int nullCount = 0;
        for (int unit = 0; unit < units.length; unit++){
            if (units[unit] == null ){
                nullCount++;
            }
        }
        return nullCount;
    }
    
    
     /**
     * Get the string representation of the location. 
     *
     * @return    the string representation of the location
     */
    public String toString()
    {
        double[] revenue = getMonthlyRevenue();
        String locationString =  ("\ndesignation: " + designation + "\ncustomer_count: " + customerCount + "\n# total_units: " 
                + (units.length * units[0].length) + "\n# empty_units: " + (getEmptyUnits().length - countNullUnits(getEmptyUnits())) +
                "\ncurrent_monthly_revenue: " + Helpers.formatMoney(revenue[0]) + "\npossible_monthly_revenue: " + Helpers.formatMoney(revenue[1]) +
                "\n\nUNITS:" + units.toString());
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                locationString += "\nunit " + row + "-" + unit + "\t";
                locationString += units[row][unit].toString();
            }
        }
        return locationString;
                
    }
}

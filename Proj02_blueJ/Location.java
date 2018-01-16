import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.NumberFormat;

/**
 * A storage location with 106 storage units (70 regular units, 24 humidity controlled units, and 12 temperature controlled units).
 *
 * @author Mary
 * @version 1/15/18
 *
 */
public class Location
{
    // instance variables
    private String designation;
    private Unit[][] units;
    private Customer[] customers;
    private int customerCount;
    private double basePrice;
    //location constants
    // number of rows
    private static final int ROWS_REG_UNIT = 7;
    private static final int ROWS_HUMID_UNIT = 3; 
    private static final int ROWS_TEMP_UNIT = 2; 
    private static final int TOTAL_ROWS = ROWS_REG_UNIT + ROWS_HUMID_UNIT + ROWS_TEMP_UNIT;
    // number of units per row
    private static final int REG_UNIT_PER_ROW = 10;
    private static final int HUMID_UNIT_PER_ROW = 8; 
    private static final int TEMP_UNIT_PER_ROW = 6; 
    private static final int TOTAL_UNITS = ROWS_REG_UNIT * REG_UNIT_PER_ROW  + ROWS_HUMID_UNIT * HUMID_UNIT_PER_ROW + ROWS_TEMP_UNIT * TEMP_UNIT_PER_ROW;
    // dimensions of units 
    private static final int UNIT_LENGTH = 12;
    private static final int UNIT_HEIGHT = 12;
    private static final int REG_UNIT_WIDTH = 12;
    private static final int HUMID_UNIT_WIDTH = 16;
    private static final int TEMP_UNIT_WIDTH = 20;

    /**
     * Constructor for objects of class Location
     * 
     * @param designation the designation ID for the location
     * @throws IllegalArgumentException designation must be at least five characters and in the form 'WA01Seattle'
     */
    public Location(String designation, double basePrice) throws FileNotFoundException
    {
        if (designation.length() < 5){
            throw new IllegalArgumentException("Designation must be at least five characters and in the form 'WA01Seattle'.");
        }
        
        String state = designation.substring(0, 2);
        String strNumber = designation.substring(2, 4);
        String city = designation.substring(4);
        
        if (!state.matches("[A-Z]{2}") || !city.matches("[A-Z]{1}[a-zA-Z]+$")) {
            throw new IllegalArgumentException("State must be two uppercase letters. City must begin with an uppercase letter and only contain letters. No spaces allowed.");
        }
        if (!strNumber.matches("[0-9]{2}")) {
            throw new IllegalArgumentException("Number must be two digits between 00-99.");
        }
        if (basePrice < -75.0) {
            throw new IllegalArgumentException("Base price must be greater than -75.00.");
        }
        
        // // initialize instance variables
        this.designation = designation;
        this.basePrice = basePrice;
        units = new Unit[TOTAL_ROWS][];
        customers = new Customer[TOTAL_UNITS];
 
        // the following section initializes all units 
        for (int row = 0; row < units.length; row++){
            if (row < ROWS_REG_UNIT ) {
                units[row] = new Unit[REG_UNIT_PER_ROW];
                for (int unit = 0; unit < REG_UNIT_PER_ROW; unit++){
                    units[row][unit] = new RegularUnit(this, UNIT_LENGTH, REG_UNIT_WIDTH, UNIT_HEIGHT);
                }
            }
            else if ( row < ROWS_REG_UNIT + ROWS_HUMID_UNIT){
                units[row] = new Unit[HUMID_UNIT_PER_ROW];
                for (int unit = 0; unit < HUMID_UNIT_PER_ROW; unit++){
                    units[row][unit] = new HumidityUnit(this, UNIT_LENGTH, HUMID_UNIT_WIDTH, UNIT_HEIGHT);                  
                }                
            }
            else {
                units[row] = new Unit[TEMP_UNIT_PER_ROW];
                for (int unit = 0; unit < TEMP_UNIT_PER_ROW; unit++){
                    units[row][unit] = new TemperatureUnit(this, UNIT_LENGTH, TEMP_UNIT_WIDTH, UNIT_HEIGHT);                    
                }                  
            }

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
        return this.designation; 
    }
    

     /**
     * Get the location's base price. 
     *
     * @return  the base price
     */
    public double getBasePrice()
    {
        return basePrice; 
    }
    
    
     /**
     * Set the location's base price. 
     *
     */
    public void setBasePrice(double basePrice)
    {
        if (basePrice < -75.0) {
            throw new IllegalArgumentException("Base price must be greater than -75.00.");
        }
        this.basePrice = basePrice; 
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
     * @throws  IllegalArgumentException customer name must be of forat "John Doe"; phone must be of format "##########"
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
        Unit[] customersUnits = new Unit[106];
        int customersCount = 0;
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                if (units[row][unit].getCustomer() == customer) {
                    customersUnits[customersCount] = units[row][unit];
                    customersCount++;
                }
            }
        }
        Unit[] customersUnitsSmall = new Unit[customersCount];
        for (int unit = 0; unit < customersCount; unit++){
            customersUnitsSmall[unit] = customersUnits[unit];
        }
        return customersUnitsSmall; 
    }
    
    
     /**
     * Charge rent for all units belonging to the location. 
     *
     */
    public void chargeRent()
    {  
        for ( int customer = 0; customer < customerCount; customer++) {
            Unit[] customersUnits = getUnitsForCustomer(customers[customer]);
            double totalCharge = 0.0;
            if (customersUnits.length > 1){
                for (Unit unit : customersUnits) {
                    totalCharge += unit.getPrice();
                }
                totalCharge = totalCharge * 0.95;
                customers[customer].charge(totalCharge);
            }
            else if (customersUnits.length > 0){
                customers[customer].charge(customersUnits[0].getPrice());
            }
            else{
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
                if (units[row][unit].getCustomer() != null) {
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
        Unit[] emptyUnits = new Unit[106];
        int emptyCount = 0;
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                if (units[row][unit].getCustomer() == null) {
                    emptyUnits[emptyCount] = units[row][unit];
                    emptyCount++;
                }
            }
        }
        Unit[] emptyUnitsSmall = new Unit[emptyCount];
        for (int unit = 0; unit < emptyCount; unit++){
            emptyUnitsSmall[unit] = emptyUnits[unit];
        }
        return emptyUnitsSmall;
        }
    
        
     /**
     * Get the empty (unrented) units of a specified type.  
     *
     * @param  type  the type of unit
     * @return    array of empty units
     */    
    public Unit[] getEmptyUnits( String type )
    {
        if (!type.equals("regular") & !type.equals("humidity") & !type.equals("temperature")) {
            throw new IllegalArgumentException("Type must be regular, humidity or temperature.");
        }
        Unit[] emptyUnits = new Unit[70];
        int emptyCount = 0;
            for (int row = 0; row < units.length; row++){
                for (int unit = 0; unit < units[row].length; unit++){
                    if (units[row][unit].getCustomer() == null & units[row][unit].getType().equals(type)) {
                        emptyUnits[emptyCount] = units[row][unit];
                        emptyCount++;
                    }
                }
            }
        Unit[] emptyUnitsSmall = new Unit[emptyCount];
        for (int unit = 0; unit < emptyCount; unit++){
            emptyUnitsSmall[unit] = emptyUnits[unit];
        }
        return emptyUnitsSmall;
    }
    
    
     /**
     * Get the string representation of the location. 
     *
     * @return    the string representation of the location
     */
    public String toString()
    {
        double[] revenue = getMonthlyRevenue();
        String locationString =  ("\ndesignation: " + designation + "\nbase_price: " + basePrice + "\ncustomer_count: " + customerCount + "\n# total_units: " 
                + TOTAL_UNITS + "\n# empty_units: " + getEmptyUnits().length +
                "\ncurrent_monthly_revenue: " + Helpers.formatMoney(revenue[0]) + "\npossible_monthly_revenue: " + Helpers.formatMoney(revenue[1]) +
                "\n\nUNITS:" + units.toString());
        for (int row = 0; row < units.length; row++){
            for (int unit = 0; unit < units[row].length; unit++){
                locationString += "unit " + row + "-" + unit + "\t";
                locationString += units[row][unit].toString();
            }
        }
        return locationString;         
    }
}

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A single storage unit. 
 *
 * @author Mary
 * @version 1/15/18
 */


public abstract class Unit
{
    // instance variables that will be set at instantiation
    private Location location;
    private double standardPrice;
    private int length;
    private int width;
    private int height;
    private String type;
    // instance variables that will be set at time of rental
    private Customer customer;
    private LocalDate rentalDate;
    private double rentalPrice;

    /**
     * Constructor for objects of class Unit
     */
    public Unit(Location location, int length, int width, int height, String type){
        if (length % 4 != 0 || width % 4 != 0 || length < 4 || width < 4 ){
            throw new IllegalArgumentException("Length and width arguments must be positive and divisible by 4.");
        }
        if (height < 2 || height % 2 != 0){
            throw new IllegalArgumentException("Height argument must be positive and divisible by 2.");
        }
        this.location = location;
        this.length = length;
        this.width = width;
        this.height = height;
        this.type = type;
        standardPrice = calcStandardPrice();
    }
    
    
    /**
     * Calculate the standard price of the unit.
     *
     * @return    the price of the unit
     */
    public abstract double calcStandardPrice();
    
    
    /**
     * Get the type of the the unit
     *
     * @return    the type of the unit
     */
    public String getType(){
        return type;
    }


    /**
     * Get the location of the unit
     *
     * @return    the location of the unit
     */
 
    public Location getLocation(){
        return location;
    }
    
    
    /**
     * Get the length of the the unit
     *
     * @return    the length of the unit
     */
 
    public int getLength(){
        return length;
    }
    
    
    /**
     * Get the width of the the unit
     *
     * @return    the width of the unit
     */
 
    public int getWidth(){
        return width;
    }
    

    /**
     * Get the height of the the unit
     *
     * @return    the height of the unit
     */
 
    public int getHeight(){
        return height;
    }

    
    /**
     * Get the price of the unit. Returns the rental price if the unit is rented. Otherwise returns the Standard Price. 
     *
     * @return    the standard price of the unit
     */
    public double getPrice(){
        if (!(rentalPrice == 0.0)){
            return rentalPrice;
        }
        else {
            return standardPrice;
        } 
    }
    
    
    /**
     * Get the standard price of the unit
     *
     * @return    the standard price of the unit
     */
    public double getStandardPrice(){
        return standardPrice;
    }

    
    /**
     * Get the customer renting the unit
     *
     * @return    the customer renting the unit
     */
 
    public Customer getCustomer(){
        return customer;
    }
    
    
    /**
     * Get the rental start date of the unit
     *
     * @return    the rental start date of the unit
     */
    public LocalDate getRentalDate(){
        return rentalDate;
    }
    
    
     // /**
     // * Rent the unit at a discount. 
     // *
     // * @param  date  the date the rental starts
     // * @param  customer  the customer of the unit
     // * @param  monthlyDiscount  the discount
     // * @throws IllegalArgumentException customer and date must not be null, monthly discount must be a positive amount
     // */
    // public void rentUnit( Date date, Customer customer, double monthlyDiscount ){
        // if (customer == null){
            // throw new IllegalArgumentException("Customer must not be null.");
        // }
        // if (date == null){
            // throw new IllegalArgumentException("Date must not be null.");
        // }
        // if (monthlyDiscount < 0.0){
            // throw new IllegalArgumentException("Monthly discount must be a positive amount.");
        // }
        // this.customer = customer;
        // rentalDate = date;
        // rentalPrice = standardPrice - monthlyDiscount;
    // }
    
    public void rentUnit( Customer customer, double monthlyDiscount ){
        if (customer == null){
            throw new IllegalArgumentException("Customer must not be null.");
        }
        if (monthlyDiscount < 0.0){
            throw new IllegalArgumentException("Monthly discount must be a positive amount.");
        }
        this.customer = customer;
        rentalDate = LocalDate.now();
        rentalPrice = standardPrice - monthlyDiscount;
    }


     // /**
     // * Rent the unit without a discount.
     // *
     // * @param  date  the date the rental starts
     // * @param  customer  the customer of the unit
     // * @throws IllegalArgumentException customer and date must not be null, monthly discount must be a positive amount
     // */
    // public void rentUnit( Date date, Customer customer){
        // rentUnit(date, customer, 0.0);
    // }
   
     /**
     * Rent the unit without a discount.
     *
     * @param  date  the date the rental starts
     * @param  customer  the customer of the unit
     * @throws IllegalArgumentException customer and date must not be null, monthly discount must be a positive amount
     */
    public void rentUnit(Customer customer){
        rentUnit(customer, 0.0);
    }

        
     /**
     * Release the unit.
     *
     */
    public void releaseUnit(){
        customer = null;
        rentalPrice = 0.0;
        rentalDate = null;
    }
    
    
     /**
     * Get the string representation of a unit.
     *
     * @return    the string representation of a unit
     */
    public String toString(){
        if (customer != null){
            return (customer.toString() +  "\trental_date: " + DateTimeFormatter.ofPattern("MM/dd/yyyy").format(rentalDate) + 
                    "\trental_price: " + Helpers.formatMoney(rentalPrice) + "\tstandard_price: " + Helpers.formatMoney(standardPrice) + "\tdimensions: " 
                    + length + "x" + width + "x" + height + "\ttype: " + this.getClass().getName() + "\n");
        }
        else{
            return ("standard_price: " + Helpers.formatMoney(standardPrice) + "\tdimensions: " 
                    + length + "x" + width + "x" + height + "\ttype: " + this.getClass().getName() + "\n");
        }
    }
}

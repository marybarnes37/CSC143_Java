
/**
 * Write a description of class Unit here.
 *
 * @author Mary
 */
public class Unit
{

    // instance variables - replace the example below with your own
    private boolean isRented = false;       
    private Date rentalDate;
    private Customer customer;
    private double rentalPrice;
    private double monthlyDiscount;
    private double standardPrice;
    private int length;
    private int width;
    private int height;
    private Type type;
    private final double PRICE_NORMALIZATION = 15.0;
    

    /**
     * Constructor for objects of class Unit
     */
    public Unit(Type type, int length, int width, int height) //how to make rentalPrice optional to pass in
     {
        // check preconditions
        if (length % 4 != 0 || width % 4 != 0 || height % 2 != 0 || length < 4 || width < 4 || height < 2){
            throw new IllegalArgumentException("Length and width arguments must be positive and divisible by 4. Height argument must be positive and divisible by 2.");
        }
        // determine typePremium
        double typePremium = 1;
        switch (type) {
            case REGULAR:       typePremium = 1.0;
                                break;
            case HUMIDITY:      typePremium = 1.5;
                                break;
            case TEMPERATURE:   typePremium = 2.0;
                                break;       
        }
        // initialise instance variables
        this.type = type;
        this.length = length;
        this.width = width;
        this.height = height;
        standardPrice = (double)(typePremium * length * width * height) / PRICE_NORMALIZATION;
 
    }

    
    /**
     * Get the customer renting the unit
     *
     * @return    the customer renting the unit
     */
 
    public Customer getCustomer()
    {
        return customer;
    }
    
    
    /**
     * Get the type of the unit
     *
     * @return    the type of the unit
     */
    public Type getType()
    {
        return type;
    }
    
    
    /**
     * Get the standard price of the unit
     *
     * @return    the standard price of the unit
     */
    public double getStandardPrice()
    {
        return standardPrice;
    }
    
    
    /**
     * Get the price of the unit. Returns the rental price if the unit is rented. Otherwise returns the Standard Price. 
     *
     * @return    the standard price of the unit
     */
    public double getPrice()
    {
        if (!(rentalPrice == 0.0)){
            return rentalPrice;
        }
        else {
            return standardPrice;
        } 
    }
    
    
    /**
     * Get the rental start date of the unit
     *
     * @return    the rental start date of the unit
     */
    public Date getRentalDate()
    {
        return rentalDate;
    }
    
    
    /**
     * Get whether or not the unit is rented.
     *
     * @return    true if unit is rented, false if unit is empty
     */
    public boolean isRented()
    {
        return isRented;
    }
    
    
     /**
     * Rent the unit at a discount. 
     *
     * @param  date  the date the rental starts
     * @param  customer  the customer of the unit
     * @param  monthlyDiscount  the discount
     * @throws IllegalArgumentException
     */
    public void rentUnit( Date date, Customer customer, double monthlyDiscount ) 
    {
        if (date == null || customer == null){
            throw new IllegalArgumentException("Date and customer must not be null.");
        }
        if (monthlyDiscount < 0.0){
            throw new IllegalArgumentException("Monthly discount must be a positive amount.");
        }
        isRented = true;
        this.customer = customer;
        rentalDate = date;
        this.monthlyDiscount = monthlyDiscount;
        rentalPrice = standardPrice - monthlyDiscount;
    }

    
    
     /**
     * Rent the unit without a discount.
     *
     * @param  date  the date the rental starts
     * @param  customer  the customer of the unit
     * @throws IllegalArgumentException
     */
    public void rentUnit( Date date, Customer customer) 
    {
        rentUnit(date, customer, 0.0);
    }
    
    
    /**
     * Set the monthly discount.
     *
     * @param  monthlyDiscount  the discount
     * @throws IllegalArgumentException
     */
    public void setDiscount(double monthlyDiscount ) 
    {
        if (monthlyDiscount < 0.0){
            throw new IllegalArgumentException("Monthly discount must be a positive amount.");
        }
        this.monthlyDiscount = monthlyDiscount;
        rentalPrice = standardPrice - monthlyDiscount;
    }
    
    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void releaseUnit()
    {
        isRented = false;
        rentalPrice = 0.0;
    }
    
    
    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */public String toString()
    {
        if (isRented){
            return ("\nrented: " + isRented + "\t " + customer.toString() +  "rental_date: " + rentalDate.toString() + 
                    "\trental_price: " + rentalPrice + "\tstandard_price: " + standardPrice + "\tdimensions: " 
                    + length + "x" + width + "x" + height + "\ttype: " + type + "\n");
        }
        else{
            return ("\nrented: " + isRented + "\tstandard_price: " + standardPrice + "\tdimensions: " 
                    + length + "x" + width + "x" + height + "\ttype: " + type + "\n");
        }
    }
}

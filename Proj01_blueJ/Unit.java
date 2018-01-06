
/**
 * Write a description of class Unit here.
 *
 * @author Mary
 */
public class Unit
{

    // instance variables - replace the example below with your own
    private boolean isRented;       
    private Date rentalDate;
    private Customer customer;
    private double rentalPrice;
    private double standardPrice;
    private int length;
    private int width;
    private int height;
    private Type type;





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
            case REGULAR:       typePremium = 1;
                                break;
            case HUMIDITY:      typePremium = 1.5;
                                break;
            case TEMPERATURE:   typePremium = 2;
                                break;       
        }
        // initialise instance variables
        this.type = type;
        this.length = length;
        this.width = width;
        this.height = height;
        standardPrice = (double)(typePremium * length * width * height) / 20;
        isRented = false; 
        // figure out rentalPrice;
 
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
 
    public Customer getCustomer()
    {
        return customer;
    }
    public Type getType()
    {
        return type;
    }
    public double getStandardPrice()
    {
        return standardPrice;
    }
    public double getPrice()
    {
        if (!(rentalPrice == 0.0)){
            return rentalPrice;
        }
        else {
            return standardPrice;
        } 
    }
    public Date getRentalDate()
    {
        return rentalDate;
    }
    public boolean isRented()
    {
        return isRented;
    }
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
        rentalPrice = standardPrice - monthlyDiscount;
    }
    public void rentUnit( Date date, Customer customer) 
    {
        rentUnit(date, customer, 0.0);
    }
    public void releaseUnit()
    {
        isRented = false;
        rentalPrice = 0.0;
    }
    public String toString()
    {
        if (isRented){
            return ("rented: " + isRented + "\ncustomer: " + customer.getName() +  "\nrental_date: " + rentalDate + 
                    "\nrental_price: " + rentalPrice + "\nstandard_price: " + standardPrice + "\ndimensions: " 
                    + length + "x" + width + "x" + height + "\ntype: " + type);
        }
        else{
            return ("rented: " + isRented + "\nstandard_price: " + standardPrice + "\ndimensions: " 
                    + length + "x" + width + "x" + height + "\ntype: " + type);
        }
    }
}

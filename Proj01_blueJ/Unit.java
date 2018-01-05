
/**
 * Write a description of class Unit here.
 *
 * @author Mary
 */
public class Unit
{

    // instance variables - replace the example below with your own
    private Type type;
    private int width;
    private int length;
    private int height;
    private double standardPrice;
    private double rentalPrice;
    private boolean isRented;

    /**
     * Constructor for objects of class Unit
     */
    public Unit(Type type, int length, int width, int height) //how to make rentalPrice optional to pass in
     {
        // check preconditions
        if (length % 4 != 0 || width % 4 != 0 || height % 2 != 0){
            throw new IllegalArgumentException("Length and width arguments must be divisible by 4. Height argument must be divisible by 2.");
        }
        // determine typePremium
        int typePremium = 1;
        switch (type) {
            case REGULAR:       typePremium = 1;
                                break;
            case HUMIDITY:      typePremium = 2;
                                break;
            case TEMPERATURE:   typePremium = 3;
                                break;       
        }
        // initialise instance variables
        this.type = type;
        this.width = width;
        this.length = length;
        this.height = height;
        standardPrice = width * length * height * typePremium;
        // figure out rentalPrice;
 
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return width;
    }
}

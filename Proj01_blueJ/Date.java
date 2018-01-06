
/**
 * Write a description of class Date here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Date
{
    // instance variables - replace the example below with your own
    private int month;
    private int day;
    private int year;

    /**
     * Constructor for objects of class Date
     */
    public Date(int month, int day, int year)
    {
        // initialise instance variables
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String toString(int y)
    {
        // put your code here
        return month + "/" + day + "/" + year;
    }
}

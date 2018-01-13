
/**
 * Write a description of class Date here.
 *
 * @author Mary
 * @version 1/8/2018
 */
public class Date
{
    // instance variables - replace the example below with your own
    private int month;
    private int day;
    private int year;

    /**
     * Constructor for objects of class Date
     * 
     * @param month the month as a digit 1-12
     * @param day the day as a digit 1-31
     * @param year the year as a digit 1950-3000
     * @throws IllegalArgumentException
     */
    public Date(int month, int day, int year)
    {
        if (month > 12 || day > 31 || year > 3000 || month < 1 || day < 1 || year < 1950){
            throw new IllegalArgumentException("Month must be in range 1-12. Day must be in range 1-31. Year must be in range 1950-3000.");  
        }
        // initialise instance variables
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Get the string representation of the date. 
     *
     * @return    the string representation of the date
     */
    public String toString()
    {
        return month + "/" + day + "/" + year;
    }
}


/**
 * Write a description of class Date here.
 *
 * @author Mary
 * @version 1/15/18
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
     * @throws IllegalArgumentException month must be in range 1-12, day must be in range 1-31, year must be in range 1950-3000
     */
    public Date(int month, int day, int year){
        if (month > 12 || month < 1 ){
            throw new IllegalArgumentException("Month must be in range 1-12.");  
        }
        if (day > 31 || day < 1 ){
            throw new IllegalArgumentException("Day must be in range 1-31.");  
        }
        if (year > 3000  || year < 1950 ){
            throw new IllegalArgumentException("Year must be in range 1950-3000.");  
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
    public String toString(){
        return month + "/" + day + "/" + year;
    }
}

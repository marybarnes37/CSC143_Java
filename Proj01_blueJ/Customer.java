
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private String name;
    private String phone;
    private int number;
    private double balance;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String name, String phone, int number)
    {
        // check preconditions
        checkName(name);
        checkPhone(phone);
        
        // initialise instance variables
        this.name = name;
        this.phone = phone;
        this.number = number;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void checkName(String name)
    {
        if (!name.matches("[A-Z]{1}[\\w]+\\s[A-Z]{1}[\\w]+")){
            throw new IllegalArgumentException("Name must be a first and last name with one space between.");
        }
    }
    private void checkPhone(String phone)
    {
        if (!phone.matches("[\\d]{10}")){
            throw new IllegalArgumentException("Phone must be ten digits with no spaces.");
        }
    }
    public String getName()
    {
        return name;
    }
    public Double getBalance()
    {
        return balance;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setName( String name )
    {
        checkName(name);
        this.name = name;
    }
    public void setPhone( String phone)
    {
        checkPhone(phone);
        this.phone = phone; 
    }
    public Double credit( double amount )
    {
        balance += amount;
        return balance;
    }
    public Double charge( double amount ) 
    {
        balance -= amount;
        return balance;
    }
    public String toString() 
    {
        return name;
    }
}

import java.text.NumberFormat;

/**
 * A customer of the storage location. 
 *
 * @author Mary
 * @version 1/15/18
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private String name;
    private String phone;
    private double balance;

    /**
     * Constructor for objects of class Customer
     * 
     * @param  name  Name of customer. Must be a capitalized first and last name with one space between.
     * @param phone Phone number of customer. Must be a ten-digit string. 
     * @throws IllegalArgumentException name must be a first and last name with one space between, phone must be ten digits with no spaces
     */
    public Customer(String name, String phone){
        // check preconditions
        checkName(name);
        checkPhone(phone);
        
        // initialise instance variables
        this.name = name;
        this.phone = phone;
    }

    
    /**
     * Throw an exception if name does not meet specifications. 
     *
     * @param  name  Name of customer. Must be a first and last name with one space between.
     * @throws IllegalArgumentException name must be a first and last name with one space between, phone must be ten digits with no spaces
     */
    private void checkName(String name){
        if (!name.matches("[A-Z]{1}[\\w]+\\s[A-Z]{1}[\\w]+")){
            throw new IllegalArgumentException("Name must be a first and last name with one space between.");
        }
    }
    
    
    /**
     *Throw an exception if customer phone does not meet specifications
     *
     * @param phone Phone number of customer. Must be a ten-digit string. 
     */
    private void checkPhone(String phone){
        if (!phone.matches("[\\d]{10}")){
            throw new IllegalArgumentException("Phone must be ten digits with no spaces.");
        }
    }
    
    
    /**
     * Get the customer name. 
     *
     * @return    the customer name 
     */
    public String getName(){
        return name;
    }
    
    
    /**
     * Get the customer's balance. 
     *
     * @return    the customer's balance 
     */
    public Double getBalance(){
        return balance;
    }
    
    
    /**
     * Get the customer's phone number. 
     *
     * @return    the customer's phone number
     */
    public String getPhone(){
        return phone;
    }
    
    
    /**
     * Set the customer name. 
     *
     * @param name the customer name
     * @throws IllegalArgumentException name must be a first and last name with one space between
     */
    public void setName( String name ){
        checkName(name);
        this.name = name;
    }
    
    
    /**
     * Set the customer's phone number. 
     *
     * @param phone  the customer's phone number
     * @throws IllegalArgumentException phone must be ten digits with no spaces
     */
    public void setPhone( String phone){
        checkPhone(phone);
        this.phone = phone; 
    }
    
    
    /**
     * Credit the customer a specied amount.
     *
     * @param  amount  the amount to credit
     * @return    the new balance
     * @throws IllegalArgumentException amount must be a positive number
     */
    public Double credit( double amount ){
        checkAmount(amount);
        balance += amount;
        return balance;
    }
    
    
    /**
     * Charge the customer a specied amount.
     *
     * @param  amount  the amount to charge, must be positive
     * @return    the new balance
     * @throws IllegalArgumentException amount must be a positive number
     */
    public Double charge( double amount ){
        checkAmount(amount);
        balance -= amount;
        return balance;
    }
    
    
    /**
     * Throw an exception if the amount is not positive. 
     *
     * @param  amount  the amount to check
     * @throws IllegalArgumentException
     */
    private void checkAmount( double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("Amount must be non-negative.");
        }
    }
    
    
    /**
     * Get the string representation of the customer. 
     *
     * @return    the string representation of the customer
     */
    public String toString() {
        return "customer_name: " + name + "\tcustomer_phone: " + phone + "\tcustomer_balance: " + Helpers.formatMoney(balance);
    }
}

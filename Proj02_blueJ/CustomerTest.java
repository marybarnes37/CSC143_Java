
import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    
    @Test
    public void testConstructor() 
    {
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        assertEquals("Mary Barnes", myCustomer.getName());
        assertEquals("2065431234", myCustomer.getPhone());
    }
    
    @Test
    public void testSetPhone() 
    {
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        myCustomer.setPhone("8889990000");
        assertEquals("8889990000", myCustomer.getPhone());
    }
    
    @Test
    public void testSetName() 
    {
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        myCustomer.setName("John Oliver");
        assertEquals("John Oliver", myCustomer.getName());
    }
    
    @Test
    public void testCreditAndCharge() throws FileNotFoundException {
        Location myLocation = new Location("WA", "Seattle", "01");
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        myLocation.getUnit(0, 0).rentUnit(new Date(1, 8, 2018), myCustomer);
        myLocation.chargeRent();
        assertEquals(-68.27, myCustomer.getBalance(), .1);
        myCustomer.credit(68.27);
        assertEquals(0.0, myCustomer.getBalance(), .1);
    }
    
    
    // @Test
    // public void checkToString() throws FileNotFoundException {
        // Location myLocation = new Location("WA", "Seattle", "01");
        // Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        // myLocation.getUnit(0, 0).rentUnit(new Date(1, 8, 2018), myCustomer);
        // myLocation.chargeRent();
        // assertEquals("customer_name: Mary Barnes    customer_phone: 2065431234  customer_balance: (68.27)\n", myCustomer.toString());
    // }
    
    
    // @Test (expected = IllegalArgumentException.class)
    // public void testCheckAmount(){
        // Customer.checkAmount(-5.0);
    // }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckNameSpace() 
    {
        Customer myCustomer = new Customer("MaryBarnes", "2065431234");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckNameCapital() 
    {
        Customer myCustomer = new Customer("mary Barnes", "2065431234");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckPhoneLength() 
    {
        Customer myCustomer = new Customer("Mary Barnes", "20654312341");
    }

    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckPhoneDigits() 
    {
        Customer myCustomer = new Customer("Mary Barnes", "2b65431234");
    }
}


import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author Mary
 * @version 1/15/18
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
    public void testConstructor(){
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        assertEquals("Mary Barnes", myCustomer.getName());
        assertEquals("2065431234", myCustomer.getPhone());
    }
    
    @Test
    public void testSetPhone(){
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        myCustomer.setPhone("8889990000");
        assertEquals("8889990000", myCustomer.getPhone());
    }
    
    @Test
    public void testSetName(){
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        myCustomer.setName("John Oliver");
        assertEquals("John Oliver", myCustomer.getName());
    }
    
    @Test
    public void testCreditAndCharge() throws FileNotFoundException {
        Location myLocation = new Location("WA01Seattle", 10.0);
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        myLocation.getUnit(0, 0).rentUnit( myCustomer);
        assertEquals(85.0, myLocation.getUnit(0, 0).getPrice(), 001);
        assertEquals(1, myLocation.getUnitsForCustomer(myCustomer).length);
        assertEquals(myLocation.getUnit(0, 0), myLocation.getUnitsForCustomer(myCustomer)[0]);
        //myLocation.chargeRent();
        myCustomer.charge(85.0);
        assertEquals(-85.0, myCustomer.getBalance(), .001);
        myCustomer.credit(85.0);
        assertEquals(0.0, myCustomer.getBalance(), .001);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckNameSpace() {
        Customer myCustomer = new Customer("MaryBarnes", "2065431234");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckNameCapital() {
        Customer myCustomer = new Customer("mary Barnes", "2065431234");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckPhoneLength() {
        Customer myCustomer = new Customer("Mary Barnes", "20654312341");
    }

    
    @Test (expected = IllegalArgumentException.class)
    public void testCheckPhoneDigits() {
        Customer myCustomer = new Customer("Mary Barnes", "2b65431234");
    }
}

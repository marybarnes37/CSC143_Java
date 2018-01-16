
import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UnitTest.
 *
 * @author Mary
 * @version 1/15/18
 */
public class UnitTest
{
    /**
     * Default constructor for test class UnitTest
     */
    public UnitTest(){
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    }

    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
    }
    
    
    @Test
    public void testRentUnit() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0);
        Unit myUnit = new RegularUnit(myLocation, 12, 12, 12);
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        Date myDate = new Date(1, 8, 2018);
        myUnit.rentUnit(myDate, myCustomer);
        assertEquals(myCustomer, myUnit.getCustomer());
        assertEquals(myDate, myUnit.getRentalDate());
        assertEquals(85.0, myUnit.getPrice(), .001);
    }
    
    
    @Test
    public void testReleaseUnit() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0);
        Unit myUnit = new RegularUnit(myLocation, 12, 12, 12);
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        Date myDate = new Date(1, 8, 2018);
        myUnit.rentUnit(myDate, myCustomer);
        myUnit.releaseUnit();
        assertEquals(null, myUnit.getCustomer());
        assertEquals(null, myUnit.getRentalDate());
    }

    
    @Test (expected = IllegalArgumentException.class)
    public void testPrecondtionLength() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0);
        Unit myUnit = new RegularUnit(myLocation, 9, 12, 8);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testPrecondtionWidth() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0);
        Unit myUnit = new RegularUnit(myLocation, 8, 11, 8);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testPrecondtionHeight() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0);
        Unit myUnit = new RegularUnit(myLocation, 8, 12, 9);
    }
}

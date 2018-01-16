
import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LocationTest.
 *
 * @author Mary
 * @version 1/15/18
 */
public class LocationTest
{
    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest(){
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
    public void testConstructor() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0);
        assertEquals("WA01Seattle", myLocation.getDesignation());
        assertEquals(106, myLocation.getEmptyUnits().length);
        assertEquals(85.0, myLocation.getUnit(0, 0).getStandardPrice(), .001);
        assertEquals(970.0, myLocation.getUnit(7, 0).getStandardPrice(), .001);
        assertEquals(2890.0, myLocation.getUnit(10, 0).getStandardPrice(), .001);
    }
    
    
    @Test 
    public void testAddGetCountCustomer() throws FileNotFoundException {
        Location myLocation = new Location("WA01Seattle", 10.0); 
        myLocation.addCustomer("Mary Barnes", "8809872345");
        assertEquals(11, myLocation.getCustCount());
        assertEquals("Mary Barnes", myLocation.getCustomer(10).getName());
    }
    
    
    @Test 
    public void testGetEmptyUnitsNoType() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0); 
        // all of our 106 units start empty
        assertEquals(106, myLocation.getEmptyUnits().length);                 
    }
    
    @Test 
    public void testGetEmptyUnitsWithType() throws FileNotFoundException{
        Location myLocation = new Location("WA01Seattle", 10.0); 
        // start with 106 empty units
        assertEquals(106, (myLocation.getEmptyUnits("humidity").length) + 
                    (myLocation.getEmptyUnits("temperature").length) +
                    (myLocation.getEmptyUnits("regular").length));
        // rent one unit
        myLocation.getUnit(0, 0).rentUnit(new Date(1, 8, 2017), myLocation.getCustomer(0));
        assertEquals(105, myLocation.getEmptyUnits().length);
        assertEquals(105, (myLocation.getEmptyUnits("humidity").length) + 
                    (myLocation.getEmptyUnits("temperature").length) +
                    (myLocation.getEmptyUnits("regular").length));   
    }
    
    
    @Test
    public void testGetUnitsForCustomer() throws FileNotFoundException {
        Location myLocation = new Location("WA01Seattle", 10.0); 
        myLocation.getUnit(10, 0).rentUnit(new Date(1, 8, 2018), myLocation.getCustomer(0), 50);
        myLocation.getUnit(10, 1).rentUnit(new Date(1, 7, 2018), myLocation.getCustomer(0));
        myLocation.getUnit(9, 0).rentUnit(new Date(1, 9, 2018), myLocation.getCustomer(1));
        assertEquals(2, myLocation.getUnitsForCustomer(myLocation.getCustomer(0)).length);
        assertEquals(1, myLocation.getUnitsForCustomer(myLocation.getCustomer(1)).length);
    }
    
    
    @Test
    public void testChargeRentGetRevenue() throws FileNotFoundException {
        Location myLocation = new Location("WA01Seattle", 10.0); 
        myLocation.getUnit(0, 0).rentUnit(new Date(1, 8, 2018), myLocation.getCustomer(0));
        myLocation.chargeRent();
        assertEquals(-85.0 , myLocation.getCustomer(0).getBalance(), .001);
        assertEquals(85.00, myLocation.getMonthlyRevenue()[0], .001);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionNumberLength() throws FileNotFoundException{
        Location myLocation = new Location("WASeattle090", 10.0);
    }  
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionNumberDigit() throws FileNotFoundException{
        Location myLocation = new Location("WASeattlewa", 10.0);
    }   
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateCap() throws FileNotFoundException{
        Location myLocation = new Location("wa01Seattle", 10.0);
    }    
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateLengthShort() throws FileNotFoundException{
        Location myLocation = new Location("WSeattle01", 10.0);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateLengthLong() throws FileNotFoundException{
        Location myLocation = new Location("WACSeattle01", 10.0);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCityCap() throws FileNotFoundException{
        Location myLocation = new Location("WA01seattle", 10.0);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCitySpaces() throws FileNotFoundException{
        Location myLocation = new Location("WASeattle Ballard01", 10.0);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCityNumbers() throws FileNotFoundException{
        Location myLocation = new Location("WASeattle301", 10.0);
    }
}

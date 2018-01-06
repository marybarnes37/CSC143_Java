

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LocationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LocationTest
{
    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
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
        Location myLocation = new Location("WA", "Seattle", "01");
        assertEquals("WA01Seattle", myLocation.getDesignation());
        assertEquals(240, myLocation.getEmptyUnits().length);
        assertEquals(345.6, myLocation.getUnit(11, 19).getStandardPrice(), .001);
        assertEquals(115.2, myLocation.getUnit(11, 4).getStandardPrice(), .001);
    }
    
    @Test 
    public void testAddGetCountCustomer(){
        Location myLocation = new Location("WA", "Seattle", "01"); 
        myLocation.addCustomer("Mary Barnes", "8809872345");
        assertEquals(1, myLocation.getCustCount());
        assertEquals("Mary Barnes", myLocation.getCustomer(0).getName());
    }
    
    @Test 
    public void testGetEmptyUnits(){
        Location myLocation = new Location("WA", "Seattle", "01"); 
        myLocation.addCustomer("Mary Barnes", "8809872345");
        assertEquals(240, myLocation.getEmptyUnits().length);
        assertEquals(240, myLocation.countEmptyUnits(myLocation.getEmptyUnits(Type.HUMIDITY)) + 
                    myLocation.countEmptyUnits(myLocation.getEmptyUnits(Type.TEMPERATURE)) +
                    myLocation.countEmptyUnits(myLocation.getEmptyUnits(Type.REGULAR)));
    }
    
    @Test
    public void testGetUnitsForCustomer(){
        Location myLocation = new Location("WA", "Seattle", "01"); 
        myLocation.addCustomer("Mary Barnes", "8809872345");
        myLocation.addCustomer("Mary Shaw", "8809879084");
        myLocation.getUnit(0, 0).rentUnit(new Date(1, 8, 18), myLocation.getCustomer(0), 50);
        myLocation.getUnit(2, 1).rentUnit(new Date(1, 7, 18), myLocation.getCustomer(0));
        myLocation.getUnit(3, 0).rentUnit(new Date(1, 9, 18), myLocation.getCustomer(1));
        assertEquals(2, myLocation.countEmptyUnits(myLocation.getUnitsForCustomer(myLocation.getCustomer(0))));
        assertEquals(1, myLocation.countEmptyUnits(myLocation.getUnitsForCustomer(myLocation.getCustomer(1))));
    }
    
    @Test
    public void testChargeRent(){
    }
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateCap(){
        Location myLocation = new Location("wa", "Seattle", "01");
    }    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateLengthShort(){
        Location myLocation = new Location("W", "Seattle", "01");
    }
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateLengthLong(){
        Location myLocation = new Location("WAC", "Seattle", "01");
    }
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCityCap(){
        Location myLocation = new Location("WA", "seattle", "01");
    }
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCitySpaces(){
        Location myLocation = new Location("WA", "Seattle Ballard", "01");
    }
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCityNumbers(){
        Location myLocation = new Location("WA", "Seattle3", "01");
    }

    }

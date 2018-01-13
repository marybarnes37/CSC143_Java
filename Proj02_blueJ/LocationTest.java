
import java.io.FileNotFoundException;
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
    public void testConstructor() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "01");
        assertEquals("WA01Seattle", myLocation.getDesignation());
        assertEquals(240, myLocation.getEmptyUnits().length);
        assertEquals(460.8, myLocation.getUnit(11, 19).getStandardPrice(), .001);
        assertEquals(153.6, myLocation.getUnit(11, 4).getStandardPrice(), .001);
    }
    
    
    @Test 
    public void testAddGetCountCustomer() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "01"); 
        myLocation.addCustomer("Mary Barnes", "8809872345");
        assertEquals(11, myLocation.getCustCount());
        assertEquals("Mary Barnes", myLocation.getCustomer(10).getName());
    }
    
    
    @Test 
    public void testGetEmptyUnits() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "01"); 
        // all of our 240 units start empty
        assertEquals(240, myLocation.getEmptyUnits().length);
        // each call to getEmptyUnits returns an array length 240 (3 * 240 = 720). Our Location is initialized with 240 non-null units
        // 720 - 240 = 480
        assertEquals(480, myLocation.countNullUnits(myLocation.getEmptyUnits(Unit.Type.HUMIDITY)) + 
                    myLocation.countNullUnits(myLocation.getEmptyUnits(Unit.Type.TEMPERATURE)) +
                    myLocation.countNullUnits(myLocation.getEmptyUnits(Unit.Type.REGULAR)));
        // rent one unit
        myLocation.getUnit(0, 0).rentUnit(new Date(1, 8, 2017), myLocation.getCustomer(0));
        // length of the empty units array will always be 240, so we subtract the count of null units to get the actual number of empty units
        assertEquals(239, myLocation.getEmptyUnits().length - myLocation.countNullUnits(myLocation.getEmptyUnits()));
        // there is now one fewer empty unit, so the total null units in the return array of empty units is one greater (480 --> 481)
        assertEquals(481, myLocation.countNullUnits(myLocation.getEmptyUnits(Unit.Type.HUMIDITY)) + 
                    myLocation.countNullUnits(myLocation.getEmptyUnits(Unit.Type.TEMPERATURE)) +
                    myLocation.countNullUnits(myLocation.getEmptyUnits(Unit.Type.REGULAR)));
                    
    }
    
    
    @Test
    public void testCountNullUnits() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "01"); 
        // to start, the EmptyUnits array is full because we haven't rented any of our units, thus the null count is 0
        assertEquals(0, myLocation.countNullUnits(myLocation.getEmptyUnits()));
        int row = 0;
        Date date = new Date(1, 8, 2018);
        for (int unit = 0; unit < myLocation.getCustCount(); unit++){
            myLocation.getUnit(row, unit).rentUnit(date, myLocation.getCustomer(unit));
        }
        row++;
        for (int unit = 0; unit < myLocation.getCustCount(); unit++){
            myLocation.getUnit(row, unit).rentUnit(date, myLocation.getCustomer(unit));
        }
        // we just rented out 20 units above, so we  now expect the empty units array to have 20 null spaces
        assertEquals(20, myLocation.countNullUnits(myLocation.getEmptyUnits()));
    }
    
    
    @Test
    public void testGetUnitsForCustomer() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "01"); 
        myLocation.addCustomer("Mary Barnes", "8809872345");
        myLocation.addCustomer("Mary Shaw", "8809879084");
        myLocation.getUnit(10, 0).rentUnit(new Date(1, 8, 2018), myLocation.getCustomer(0), 50);
        myLocation.getUnit(10, 1).rentUnit(new Date(1, 7, 2018), myLocation.getCustomer(0));
        myLocation.getUnit(9, 0).rentUnit(new Date(1, 9, 2018), myLocation.getCustomer(1));
        assertEquals(238, myLocation.countNullUnits(myLocation.getUnitsForCustomer(myLocation.getCustomer(0))));
        assertEquals(239, myLocation.countNullUnits(myLocation.getUnitsForCustomer(myLocation.getCustomer(1))));
    }
    
    
    @Test
    public void testChargeRentGetRevenue() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "01"); 
        myLocation.getUnit(0, 0).rentUnit(new Date(1, 8, 2018), myLocation.getCustomer(0));
        myLocation.chargeRent();
        assertEquals(-68.26, myLocation.getCustomer(0).getBalance(), .01);
        assertEquals(68.26, myLocation.getMonthlyRevenue()[0], .01);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionNumberLength() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "090");
    }  
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionNumberDigit() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle", "wa");
    }   
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateCap() throws FileNotFoundException
    {
        Location myLocation = new Location("wa", "Seattle", "01");
    }    
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateLengthShort() throws FileNotFoundException
    {
        Location myLocation = new Location("W", "Seattle", "01");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionStateLengthLong() throws FileNotFoundException
    {
        Location myLocation = new Location("WAC", "Seattle", "01");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCityCap() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "seattle", "01");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCitySpaces() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle Ballard", "01");
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionCityNumbers() throws FileNotFoundException
    {
        Location myLocation = new Location("WA", "Seattle3", "01");
    }

    }

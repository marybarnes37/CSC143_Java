
import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TemperatureUnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TemperatureUnitTest
{
    /**
     * Default constructor for test class TemperatureUnitTest
     */
    public TemperatureUnitTest()
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
    public void testConstructorGetType() throws FileNotFoundException {
        Location myLocation = new Location("WA01Seattle", 10.0);
        TemperatureUnit myUnit = new TemperatureUnit(myLocation, 12, 20, 12);
        assertEquals("temperature", myUnit.getType());
        assertEquals(2890.0, myUnit.getStandardPrice(), .001);
    }

    @Test
    public void testCalcStandardPrice() throws FileNotFoundException {
        Location myLocation = new Location("WA01Seattle", 10.0);
        TemperatureUnit myUnit = new TemperatureUnit(myLocation, 12, 16, 12);
        assertEquals(2314.0, myUnit.calcStandardPrice(), .001);
    }
}

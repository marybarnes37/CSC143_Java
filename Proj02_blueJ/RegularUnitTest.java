
import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RegularUnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RegularUnitTest
{
    /**
     * Default constructor for test class RegularUnitTest
     */
    public RegularUnitTest()
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
        RegularUnit myUnit = new RegularUnit(myLocation, 12, 16, 12);
        assertEquals("regular", myUnit.getType());
        assertEquals(85.0, myUnit.getStandardPrice(), .001);
    }

    @Test
    public void testCalcStandardPrice() throws FileNotFoundException {
        Location myLocation = new Location("WA01Seattle", 10.0);
        RegularUnit myUnit = new RegularUnit(myLocation, 12, 20, 12);
        assertEquals(85.0, myUnit.calcStandardPrice(), .001);
    }
}

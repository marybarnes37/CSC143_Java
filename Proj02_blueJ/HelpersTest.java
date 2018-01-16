import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HelpersTest.
 *
 * @author Mary
 * @version 1/15/18
 */
public class HelpersTest
{
    /**
     * Default constructor for test class HelpersTest
     */
    public HelpersTest(){
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
    public void testFormatMoney(){
        assertEquals("$4.00", Helpers.formatMoney(4.0).toString());
    }
}

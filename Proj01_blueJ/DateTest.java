

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DateTest.
 *
 * @author  Mary
 * @version 1/8/18
 */
public class DateTest
{
    /**
     * Default constructor for test class DateTest
     */
    public DateTest()
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
        Date myDate = new Date(1, 3, 2015);
        assertEquals("1/3/2015", myDate.toString());
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionYear(){
        new Date(1, 3, 15);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionMonth(){
        new Date(13, 3, 15);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionDay(){
        new Date(1, 32, 2015);
    }
}

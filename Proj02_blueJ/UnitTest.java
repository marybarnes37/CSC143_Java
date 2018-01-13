

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class UnitTest
{
    /**
     * Default constructor for test class UnitTest
     */
    public UnitTest()
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
        Unit myUnit = new Unit(Unit.Type.REGULAR, 8, 12, 8);
        assertEquals(Unit.Type.REGULAR, myUnit.getType());
        assertEquals(51.2, myUnit.getStandardPrice(), .001);
    }
    
    
    @Test
    public void testRentUnit() 
    {
        Unit myUnit = new Unit(Unit.Type.REGULAR, 8, 12, 8);
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        Date myDate = new Date(1, 8, 2018);
        myUnit.rentUnit(myDate, myCustomer);
        assertEquals(myCustomer, myUnit.getCustomer());
        assertEquals(myDate, myUnit.getRentalDate());
        assertEquals(51.2, myUnit.getPrice(), .001);
    }

    
    @Test
    public void testSetDiscountGetPrice() 
    {
        Unit myUnit = new Unit(Unit.Type.REGULAR, 8, 12, 8);
        Customer myCustomer = new Customer("Mary Barnes", "2065431234");
        Date myDate = new Date(1, 8, 2018);
        myUnit.rentUnit(myDate, myCustomer);
        myUnit.setDiscount(24.0);
        assertEquals(27.2, myUnit.getPrice(), .001);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testPrecondtionLength() 
    {
        Unit myUnit = new Unit(Unit.Type.REGULAR, 9, 12, 8);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testPrecondtionWidth() 
    {
        Unit myUnit = new Unit(Unit.Type.REGULAR, 8, 11, 8);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testPrecondtionHeight() 
    {
        Unit myUnit = new Unit(Unit.Type.REGULAR, 8, 12, 9);
    }
}

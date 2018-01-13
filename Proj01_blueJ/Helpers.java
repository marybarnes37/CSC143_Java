import java.text.NumberFormat;

/**
 * Helpers needed in all classes.
 *
 * @author Mary
 * @version 1/8/2018
 */
public class Helpers
{

    /**
     * Format an amount as US currency. 
     *
     * @param  money  the money to be formatted as a string
     * @return    the money formatted as a string
     */
    public static String formatMoney(double money){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return (formatter.format(money));
    }
}

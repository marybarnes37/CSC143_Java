import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Write a description of class Main here.
 *
 * @author Mary
 * @version 1/15/18
 */
public class Main
{
    private static final String LINE = "\n-----------------------------------------------------------------\n";
    private static Scanner checkInput(String answer, Scanner console){
        while (!answer.equals("yes") & !answer.equals("y") & !answer.equals("Y")){
            System.out.print("\nAre you sure you don't want to continue? You don't really have any other option. Press 'y' to continue.\n");
            answer = console.next();
        }
        return console;
    }

    
    private static Scanner converseWithUser(Scanner console, String message){
        System.out.print(LINE);
        System.out.print(message);     
        String procedeString = "\nReady to procede? Type 'y' for yes'. \n";
        System.out.print(procedeString);
        String answer = console.next();
        console = checkInput(answer, console);
        System.out.print(LINE);
        return console;

    }
    
    
    private static void printUnitArray(Unit[] units, Location myLocation){   
        System.out.print("\nUNITS OF INTEREST: \n");
            for (int unitIndex = 0; unitIndex < units.length; unitIndex++){
            System.out.print(units[unitIndex]);
        }
    }
    
    
    private static void printCustomers(Location myLocation){
        System.out.print("\nCURRENT CUSTOMERS: \n");
        for (int customerIndex = 0; customerIndex < 10; customerIndex++){
            System.out.print(myLocation.getCustomer(customerIndex).toString() + "\n");
        }
    }
    
    
    private static void printRevenue( Location myLocation){
        System.out.print("\nCURRENT STATUS OF REVENUE: \n");
        double[] revenues = myLocation.getMonthlyRevenue();
        double actualRevenue = revenues[0];
        double potentialRevenue = revenues[1];
        System.out.print("\nActual revenue (amount you are charging per month): \t" + Helpers.formatMoney(actualRevenue) + 
                        "\nPotential revenue: (amount you could be charging per month if all units were rented): \t" +
                        Helpers.formatMoney(potentialRevenue) + "\n");
    }
    
    
    private static void printStatusOfUnits (Location myLocation){
        System.out.print("\nSTATUS OF RENTED UNITS: \n");
        Unit[] customerUnits;
        for (int customerIndex = 0; customerIndex < myLocation.getCustCount(); customerIndex++){
            customerUnits = myLocation.getUnitsForCustomer(myLocation.getCustomer(customerIndex));
            for (int unit = 0; unit < customerUnits.length; unit++){
                System.out.print(customerUnits[unit].toString());
            }
        }
    }
    
    
    /**
     * Main method demonstrating the capacity of the other classes in project 1.
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        // first

        Scanner console = new Scanner(System.in);

        String message = ("Welcome!\n\nFirst, let's start by initializing a new location and looking at all the units. Your location is new\n" +
                           "so the base price is just $10.00. You'll see some general information about your location at the top and then\n" +
                           "a line representing each unit in your new location. That's 106 lines!");                 
        console = converseWithUser(console, message);
        Location myLocation = new Location("WA01Seattle", 10.0); 
        System.out.print(myLocation);
        
        message = ("\n\nWe also have a map view of our units. Let's see that now.\n");
        console = converseWithUser(console, message);
        System.out.print(myLocation.toMap());
        
        message = ("\n\nPhew, that was a little overwhelming (so many units!). But also a little underwhelming because we haven't rented any units yet.\n" +
                        "However, we do have some interested customers provided for us. Let's check them out first.\n");
        console = converseWithUser(console, message);
        printCustomers(myLocation);
        
        message = ("\n\nWow, we have interesting clientele. No matter. Let's rent them each a couple of units.\n" +
                   "We'll rent out a row of each kind of units to them and then look at the status of those units.\n");
        console = converseWithUser(console, message);
        int row = 0;
        for (int unit = 0; unit < myLocation.getCustCount(); unit++){
            try {
                myLocation.getUnit(row, unit).rentUnit(myLocation.getCustomer(unit));
            }
            catch (Exception e) {
                break;    
            }
        }
        row = 7;
        for (int unit = 0; unit < myLocation.getCustCount(); unit++){
            try {
                myLocation.getUnit(row, unit).rentUnit(myLocation.getCustomer(unit));
            }
            catch (Exception e) {
                break;    
            }
        }
        row = 10;
        for (int unit = 0; unit < myLocation.getCustCount(); unit++){
            try {
                myLocation.getUnit(row, unit).rentUnit(myLocation.getCustomer(unit));
            }
            catch (Exception e) {
                break;    
            }
        }
        printStatusOfUnits(myLocation);
        
        message = ("\n\nGreat, some folks have one unit, others have two, and still others have three. " + 
                   "We're in business! Let's check our potential monthly revenue and our actual revenue.\n");
        console = converseWithUser(console, message);
        printRevenue(myLocation);
                         
        message = ("\n\nNot bad. But wait a minute, your customers are here and they are demanding a discount.\n" +
                    "You aexplain that those of them with multiple units will already be getting a 5% discount that will.\n"+
                    "be applied at the time of billing. You decide to demonstrate this to them. \n\nFirst, you show James Kirk"+
                    "(index 1, renting three units) and Claire Fraser (index 9, only renting one) the full price of their units. \n");
        console = converseWithUser(console, message);
        
        int jamesIdx = 1;
        Unit[] jamesUnits = myLocation.getUnitsForCustomer(myLocation.getCustomer(jamesIdx));
        double jamesFullPrice = 0.0;
        for (Unit unit : jamesUnits){
            jamesFullPrice += unit.getStandardPrice();
        }
        
        int claireIdx = 9;
        Unit[] claireUnits = myLocation.getUnitsForCustomer(myLocation.getCustomer(claireIdx));
        double claireFullPrice = 0.0;
        for (Unit unit : claireUnits){
            claireFullPrice += unit.getStandardPrice();
        }
        
        System.out.print("The standard price of James' three units is: " + Helpers.formatMoney(jamesFullPrice));
        System.out.print("\nThe standard price of Claire's one unit is: " + Helpers.formatMoney(claireFullPrice));
        
        message = "\n\nNext, you charge rent and display James and Claire's balances to demonstrate James' discount. Let's do it.\n";
        console = converseWithUser(console, message);
        myLocation.chargeRent();
        
        double jamesBalance = myLocation.getCustomer(jamesIdx).getBalance();
        System.out.print("James' balance is: " + Helpers.formatMoney(jamesBalance));
        double clairesBalance = myLocation.getCustomer(claireIdx).getBalance();
        System.out.print("\nClaire's balance is: " + Helpers.formatMoney(clairesBalance));
        
        message = "\n\nThe customers' with multiple units are satisfied with the discount. For good measure, let's check everyone's balances.\n";
        console = converseWithUser(console, message);
        printCustomers(myLocation);
        
        message = ("\n\nYup, everyone has a negative balance now. While she's here, Claire wants to go ahead and pay hers. You credit her acount\n" +
                    "and show her that her balance is now $0.00.\n");
        console = converseWithUser(console, message);
        myLocation.getCustomer(claireIdx).credit(-clairesBalance);
        clairesBalance = myLocation.getCustomer(claireIdx).getBalance();
        System.out.print("Claire's balance is: " + Helpers.formatMoney(clairesBalance));
        
        message = ("\n\nYour customers are happy and they've started to tell their friends about\n" +
                    "your storage location. You've got a new potential customer and she wants to see your available units. Let's see 'em!\n");
        console = converseWithUser(console, message);  
        Unit[] emptyUnits = myLocation.getEmptyUnits();
        printUnitArray(emptyUnits, myLocation);
                
        message = ("\n\nThat's a lot to look at. She know she wants a temperature controlled unit.\n " +
                    "She asks to see a list of those units. Can you generate such a list? Yes, you can!\n"); 
        console = converseWithUser(console, message);        
        Unit[] emptyTempUnits = myLocation.getEmptyUnits("temperature");
        printUnitArray(emptyTempUnits, myLocation);
        
        message = ("\n\nThat's better. The new customer wants to rent the first available temperature unit. Her name is Dana Scully\n" +
                    "and her phone number is 2061357913. Let's set her up and check the results.\n");
        console = converseWithUser(console, message);
        myLocation.addCustomer("Dana Scully", "2061357913");
        Customer dana = myLocation.getCustomer(myLocation.getCustCount() - 1);
        emptyTempUnits[0].rentUnit(dana);
        printStatusOfUnits(myLocation);        
 
        message = ("\n\nPerfect! Things are good at your storage location. But don't get too comfortable. Here comes Winston Smith (idx 3).\n" +
                    "He seems a little paranoid and has decided to use a different storage location. You release his units.\n" +
                    "With these recent changes, you decide to check the status of your rented units and your revenue again.\n");
        console = converseWithUser(console, message);    
        int winstonIdx = 3;
        Unit[] winstonsUnits = myLocation.getUnitsForCustomer(myLocation.getCustomer(winstonIdx));
        for (int unit = 0; unit < (winstonsUnits.length) ; unit++){
            winstonsUnits[unit].releaseUnit();
        }
        printStatusOfUnits(myLocation);
        printRevenue(myLocation);
        
        message = ("\n\nDana is there, Winston is not, and Claire and Dana are the only customers with zero balances. It seems to be exactly as it should be.\n" +
                    "For good measure, you decide to look over all of your units one last time. Let's take a final look.\n");
        console = converseWithUser(console, message);       
        System.out.print(myLocation.toString());
        
        message = ("\n\nAnd finally (I Promise!), the map one more time.\n");
        console = converseWithUser(console, message);
        System.out.print(myLocation.toMap());
        
        System.out.print("\n\n\nThank you!");
        
    }
}

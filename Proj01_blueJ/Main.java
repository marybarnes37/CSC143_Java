import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
            for (int unitIndex = 0; unitIndex < (units.length - myLocation.countNullUnits(units)); unitIndex++){
            System.out.print(units[unitIndex]);
        }
    }
    
    
    private static void printCustomers(Location myLocation){
        System.out.print("\nCURRENT CUSTOMERS: \n");
        for (int customerIndex = 0; customerIndex < 10; customerIndex++){
            System.out.print(myLocation.getCustomer(customerIndex).toString());
        }
    }
    
    
    private static void printRevenue( Location myLocation){
        System.out.print("\nCURRENT STATUS OF REVENUE: \n");
        double[] revenues = myLocation.getMonthlyRevenue();
        double actualRevenue = revenues[0];
        double potentialRevenue = revenues[1];
        System.out.print("Actual revenue (amount you are charging per month): \t" + actualRevenue + 
                        "\nPotential revenue: (amount you could be charging per month if all units were rented): \t" +
                        + potentialRevenue);
    }
    
    
    private static void printStatusOfUnits (Location myLocation){
        System.out.print("\nSTATUS OF RENTED UNITS: \n");
        Unit[] customerUnits;
        for (int customerIndex = 0; customerIndex < myLocation.getCustCount(); customerIndex++){
            customerUnits = myLocation.getUnitsForCustomer(myLocation.getCustomer(customerIndex));
            for (int unit = 0; unit < (customerUnits.length - myLocation.countNullUnits(customerUnits)); unit++){
                System.out.print(customerUnits[unit].toString());
            }
        }
    }
    
    
    /**
     * Main method demonstrating the capacity of the other classes in project 1.
     *
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        // first

        Scanner console = new Scanner(System.in);

        String message = ("Welcome!\n\nFirst, let's start by initializing a new location and looking at all the units.\n" +
                           "You'll see a line representing each unit in your new location. That's 240 lines!");                 
        console = converseWithUser(console, message);
        Location myLocation = new Location("WA", "Seattle", "01"); 
        System.out.print(myLocation);
        
        message = ("\n\nPhew, that was a little overwhelming. But also a little underwhelming because we haven't rented any units yet.\n" +
                        "However, we do have some interested customers provided for us. Let's check them out first.\n");
        console = converseWithUser(console, message);
        printCustomers(myLocation);
        
        message = ("\n\nWow, we have interesting clientele. No matter. Let's rent them each a couple of units.\n" +
                   "We'll rent them each two units and then look at the status of those units.\n");
        console = converseWithUser(console, message);
        int row = 0;
        Date date = new Date(1, 8, 2018);
        for (int unit = 0; unit < myLocation.getCustCount(); unit++){
            myLocation.getUnit(row, unit).rentUnit(date, myLocation.getCustomer(unit));
        }
        row++;
        for (int unit = 0; unit < myLocation.getCustCount(); unit++){
            myLocation.getUnit(row, unit).rentUnit(date, myLocation.getCustomer(unit));
        }
        printStatusOfUnits(myLocation);
        
        message = "\n\nWe're in business! Let's check our potential monthly revenue and our actual revenue.\n";
        console = converseWithUser(console, message);
        printRevenue(myLocation);
                         
        message = ("\n\nNot bad. But wait a minute, your customers are here and they are demanding a discount.\n" +
                    "You agree to create a 'two unit special' and give them each $25 off their second units.\n"+
                    "Let's check the status of the rented units and our updated revenue.\n");
        console = converseWithUser(console, message);
        int secondUnitIndex = 1;
        for (int customerIndex = 0; customerIndex < myLocation.getCustCount(); customerIndex++){
            Unit[] customerUnits = myLocation.getUnitsForCustomer(myLocation.getCustomer(customerIndex));
            customerUnits[secondUnitIndex].setDiscount(25.0);
        }
        printStatusOfUnits(myLocation);
        printRevenue(myLocation);
        
        message = "\n\nGreat. Now that you've rented units, you better not forget to charge rent. Let's do it. And check everyone's balances.\n";
        console = converseWithUser(console, message);
        myLocation.chargeRent();
        printCustomers(myLocation);
        
        message = ("\n\nEveryone has a negative balance now. Your customers are happy and they've started to tell their friends about \n" +
                    "your storage location. You've got a new potential customer and she wants to see your available units. Let's see 'em!\n");
        console = converseWithUser(console, message);  
        Unit[] emptyUnits = myLocation.getEmptyUnits();
        printUnitArray(emptyUnits, myLocation);
        
        message = ("\n\nThat's a lot to look at. She know she wants a regular unit without temperature or humidity control.\n " +
                    "She asks to see a list of those units. Can you generate such a list? Yes, you can!\n"); 
        console = converseWithUser(console, message);        
        Unit[] emptyRegularUnits = myLocation.getEmptyUnits(Type.REGULAR);
        printUnitArray(emptyRegularUnits, myLocation);
        
        message = ("\n\nThat's better. The new customer wants to rent the first two available regular units. Her name is Dana Scully\n" +
                    "and her phone number is 2061357913. She'll get the 'two unit special' as well. Let's set her up and check the results.\n");
        console = converseWithUser(console, message);
        myLocation.addCustomer("Dana Scully", "2061357913");
        Customer dana = myLocation.getCustomer(myLocation.getCustCount() - 1);
        emptyRegularUnits[0].rentUnit(new Date(1, 12, 2018), dana);
        emptyRegularUnits[1].rentUnit(new Date(1, 12, 2018), dana, 50);
        printStatusOfUnits(myLocation);        
        
        message = "\n\nLooks good. Oh yay, your customer Claire Fraser (cust # 9) is here to pay her balance. First she wants to see it. Let's show her.\n";
        console = converseWithUser(console, message);
        double clairesBalance = myLocation.getCustomer(9).getBalance();
        System.out.print("Claire's balance is: " + clairesBalance);
        
        message = "\n\nShe says that looks right and pays. She wants to check her balance before she leaves to make sure her payment was registered.\n";
        console = converseWithUser(console, message);
        myLocation.getCustomer(9).credit(-clairesBalance);
        clairesBalance = myLocation.getCustomer(9).getBalance();
        System.out.print("Claire's balance is: " + clairesBalance);
 
        message = ("\n\nPerfect! Things are good at your storage location. But don't get too comfortable. Here comes Winston Smith (cust # 4).\n" +
                    "He seems a little paranoid and has decided to use a different storage location. You release his units.\n" +
                    "With these recent changes, you decide to check the status of your rented units and your revenue again.\n");
        console = converseWithUser(console, message);        
        Unit[] winstonsUnits = myLocation.getUnitsForCustomer(myLocation.getCustomer(4));
        for (int unit = 0; unit < (winstonsUnits.length - myLocation.countNullUnits(winstonsUnits)) ; unit++){
            winstonsUnits[unit].releaseUnit();
        }
        printStatusOfUnits(myLocation);
        printRevenue(myLocation);
        
        message = ("\n\nDana is there, Winston is not, and Claire and Dana are the only customers with zero balances. It seems to be exactly as it should be.\n" +
                    "For good measure, you decide to look over all of your units one last time. Let's take a final look.\n");
        console = converseWithUser(console, message);       
        System.out.print(myLocation.toString());
        
        System.out.print("\n\n\nThank you!");
        
    }
}

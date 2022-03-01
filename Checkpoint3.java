// Imports
import java.util.*;

public class Checkpoint3 {

    // Add new records - Returns 
    public int addRecord(){
        int retVal = 0;

        return retVal;
    }

    // Edit records
    public void editRecord(){

    }

    // Search records
    // Return time will be changed to whatever data structure we decide to use.
    public void lookupRecord(){

    }

    // Order items
    public int placeOrder(){
        int retVal = 0;

        return retVal;
    }

    // Useful reports

    public static void main (String args[]){
        // Variable Initialization & Declaration.
        boolean inUse = true;
        Scanner input = new Scanner(System.in);

        // While the program is in use, accept and run commands.
        System.out.println("Welcome to Team 11's Database System!");
        while(inUse){
            System.out.println("\nPlease select an option!");
            System.out.println("\n--- Option Menu ---");
            System.out.println("1: Add a Record\n2: Edit an Existing Record\n3: Lookup a Record\n4: Place an order\n0: Exit");
            System.out.print("Enter # of Choice: ");
            switch(input.nextInt()){
                case 0:
                    // Exit program
                    System.out.println("\nExiting Program...");
                    inUse = false;
                    break;
                case 1:
                    // Add a record
                    System.out.println("\nAdding Record...");
                    break;
                case 2:
                    // Edit an existing record
                    System.out.println("\nEditing Record...");
                    break;
                case 3:
                    // Search for a record
                    System.out.println("\nSearching for Record...");
                    break;
                case 4:
                    // Order items
                    System.out.println("\nPlace Order...");
                    break;
                default:
                    System.out.println("\nInvalid Input Received. Please Try Again!");
                    break;
            }
        }
        input.close();
    }
}

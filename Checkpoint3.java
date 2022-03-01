import java.util.Scanner;

public class Checkpoint3 {

    public static void main (String args[]){
        // Variable Initialization & Declaration.
        boolean inUse = true;
        Scanner input = new Scanner(System.in);
        Library library = new Library();

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
                    int id = library.addRecord(input);
                    System.out.println("\nRecord Added! ID: " + id);
                    break;
                case 2:
                    // Edit an existing record
                    id = 0;
                    System.out.println("\nInput the ID of the record you want to edit: ");
                    id = input.nextInt();
                    library.editRecord(id, input);
                    System.out.println("\nEditing Record...");
                    System.out.println("\nRecord " + id + " has been edited.");
                    
                    break;
                case 3:
                    // Search for a record
                    System.out.println("\nEnter the Name of the Record: ");
                    String search = input.nextLine();
                    System.out.println("\nSearching for Record...");
                    int res = library.lookupRecord(search);
                    if(res > 0){
                        System.out.println("Record found! ID: " + res);
                    }else{
                        System.out.println("Record not found.");
                    }
                    break;
                case 4:
                    // Order items
                    id = 0;
                    System.out.println("\nInput the ID of the record you want to order: ");
                    id = input.nextInt();
                    System.out.println("\nPlacing Order...");
                    library.placeOrder(id);
                    break;
                default:
                    System.out.println("\nInvalid Input Received. Please Try Again!");
                    break;
            }
        }
        input.close();
    }
    
}

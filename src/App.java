import java.sql.*;
import java.util.*;

public class App {
    // Class Variables
    private static String DATABASE = "project.db";
    private static String DATABASE_TWO = "database_binary.db";

    /**
     * Connects to the database if it exists, creates it if it does not, and returns the connection object.
     * 
     * @param databaseFileName the database file name
     * @return a connection object to the designated database
     */
    public static Connection initializeDB(String databaseFileName) {
    	/**
    	 * The "Connection String" or "Connection URL".
    	 * 
    	 * "jdbc:sqlite:" is the "subprotocol".
    	 * (If this were a SQL Server database it would be "jdbc:sqlserver:".)
    	 */
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null; // If you create this variable inside the Try block it will be out of scope
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
            	// Provides some positive assurance the connection and/or creation was successful.
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The connection to the database was successful.");
            } else {
            	// Provides some feedback in case the connection failed but did not throw an exception.
            	System.out.println("Null Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("There was a problem connecting to the database.");
        }
        return conn;
    }
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        boolean inUse = true;
        // While the program is in use, accept and run commands.
        System.out.println("Welcome to Team 11's Database System!");
        while(inUse){
            System.out.println("\nPlease select an option!");
            System.out.println("\n--- Option Menu ---");
            System.out.println("1: Add Record\n2: Edit Record\n3: Record Search\n4: Order Items\n5: Generate Report\n0: Exit\n");
            System.out.print("Select Choice: ");
            int choice = keyboard.nextInt();
            switch(choice){
                case 0:
                    // Exit program
                    System.out.println("\nExiting Program...");
                    inUse = false;
                    break;
                case 1:
                    Library library = new Library();
                    // Add a record
                    int id = library.addRecord(keyboard);
                    System.out.println("\nRecord Added! ID: " + id);
                    System.out.println("Add Record Called");
                    break;
                case 2:
                    // Edit an existing record
                    /* id = 0;
                    System.out.println("\nInput the ID of the record you want to edit: ");
                    id = input.nextInt();
                    library.editRecord(id, input);
                    System.out.println("\nEditing Record...");
                    System.out.println("\nRecord " + id + " has been edited."); */
                    System.out.println("Edit Record Called");
                    break;
                case 3:
                    // Search for a record
                    /* System.out.println("\nEnter the Name of the Record: ");
                    String search = input.nextLine();
                    System.out.println("\nSearching for Record...");
                    int res = library.lookupRecord(search);
                    if(res > 0){
                        System.out.println("Record found! ID: " + res);
                    }else{
                        System.out.println("Record not found.");
                    } */
                    System.out.println("Search Record Called");
                    break;
                case 4:
                    // Order items
                    /* id = 0;
                    System.out.println("\nInput the ID of the record you want to order: ");
                    id = input.nextInt();
                    System.out.println("\nPlacing Order...");
                    library.placeOrder(id); */
                    Connection conn = initializeDB(DATABASE);
                    OrderItems.executeOrder(conn, keyboard);
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    Connection connTwo = initializeDB(DATABASE_TWO);
                    Reports.executeReport(connTwo, keyboard);
                    try {
                        connTwo.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("\nInvalid Input Received. Please Try Again!");
                    break;
            }
        }
        keyboard.close();
    }
}

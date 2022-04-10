import java.sql.*;
import java.util.*;
public class OrderItems {
    public static void executeOrder(Connection conn, Scanner keyboard){
        System.out.println("1: Add Order\n2: Activate Order");
        System.out.println("Please Select Your Action: ");
        int choice = keyboard.nextInt();
        switch(choice){
            case 1:
                addOrder(conn, keyboard);
                break;
            case 2:
                System.out.print("Enter Order ID: ");
                int id = keyboard.nextInt();
                activateOrder(conn, keyboard, id);
                break;
            default:
                System.out.println("An Error Occurred. Please try again later!");
                break;
        }
        

    }

    private static void addOrder(Connection conn, Scanner keyboard){
        // Get Movie Title.
        System.out.print("Enter Movie Title: ");
        String title = keyboard.next();

        // Get Movie Title.
        System.out.print("Enter The Director's Name: ");
        String director = keyboard.next();

        // Get Movie Genre.
        System.out.print("Enter Movie Genre: ");
        String genre = keyboard.next();

        // Get Movie Length.
        System.out.print("Enter Movie Length (EX: 2h:00m:00s): ");
        String length = keyboard.next();

        // Get Movie Medium.
        System.out.print("Enter Movie Format (0 = Physical, 1 = Digital, 2 = Either): ");
        int[] movFormat = {0, 0};
        switch(keyboard.nextInt()){
            case 0:
                movFormat[0] = 1;
                break;
            case 1:
                movFormat[1] = 1;
                break;
            case 2:
                movFormat[0] = 1;
                movFormat[1] = 1;
                break;
        }

        String queryStr = "INSERT INTO Movie(title, genre, length, physical, digital, director, on_backorder) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setString(3, length);
            stmt.setInt(4, movFormat[0]);
            stmt.setInt(5, movFormat[1]);
            stmt.setString(6, director);
            stmt.setInt(7, 1);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void activateOrder(Connection conn, Scanner keyboard, int id){
        String queryStr = "UPDATE Movie SET on_backorder = 0 WHERE movie_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(queryStr);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

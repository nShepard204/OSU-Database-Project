import java.util.*;
import java.sql.*;

public class Reports {
    public static void executeReport(Connection conn, Scanner keyboard){
        System.out.println("--- Select Report ---");
        System.out.println("1: Tracks by Artist Released Before Year\n2: Count of albums checked out by patron\n3: Most Popular Artist\n4: Most Listened to Artist\n5: Patron w/ the most checked out videos");
        System.out.print("\nEnter Choice: ");
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        switch(choice){
            case 1:
                firstReport(conn, keyboard);
                break;
            case 2:
                // 2
                break;
            case 3:
                thirdReport(conn);
                break;
            case 4:
                // 4
                break;
            case 5:
                // 5
                break;
            default:
                System.out.println("An Error Occurred. Please try again later!");
                break;
        }
    }

    private static void firstReport(Connection conn, Scanner keyboard){
        int artistId = 0;
        System.out.print("Enter Artist Name: ");
        String artist = keyboard.nextLine();
        String queryStr = "SELECT Artist_ID from ARTIST WHERE Artist_Name = ?";

        // Get Artist ID.
        try {
            PreparedStatement stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, artist);
            ResultSet rs = stmt.executeQuery();
            artistId = rs.getInt("Artist_ID");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        // Get Tracks?
        queryStr = "SELECT Track_Name FROM TRACK WHERE Album_ID IN (Select Record_ID FROM ALBUM WHERE Artist_ID = ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(queryStr);
            stmt.setInt(1, artistId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n--- Results ---");
            while(rs.next()){
                System.out.println(rs.getString("Track_Name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void secondReport(){
        // TODO: Implement the thing.
    }

    private static void thirdReport(Connection conn){
        // TODO: Implement the thing.
        String queryStr = "SELECT Artist_ID, COUNT(Record_ID) AS total FROM ALBUM GROUP BY Artist_ID";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryStr);
            int max = 0, artistId = 0;
            while(rs.next()){
                if(rs.getInt("total") > max){
                    max = rs.getInt("total");
                    artistId = rs.getInt("Artist_ID");
                }
            }
            System.out.println("Artist ID: " + artistId);
            System.out.println("Album Count: " + max);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void fourthReport(){
        // TODO: Implement the thing.
    }

    private static void fifthReport(){
        // TODO: Implement the thing.
    }
}

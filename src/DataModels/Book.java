package DataModels;
import java.sql.*;

public class Book extends RecordType{

    int id, author_id, physical, digital, AB, backorder;
    String title, genre;

    public Book(int id, int author_id, String title, String genre
    , int physical, int digital, int AB, int backorder){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.physical = physical;
        this.digital = digital;
        this.backorder = backorder;
        this.author_id = author_id;
        this.AB = AB;
    }

    @Override
    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Book VALUES " +
        "(" + id + ", " + author_id + ", " + title + ", " + genre + ", " + physical + ", " + 
        digital + ", " + AB + ", " + backorder + ");";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.executeQuery();
        
            if(stmt != null) { stmt.close(); }
        
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Connection conn, int id) {
        String insertStr = "DELETE FROM Book \n" +
        "Where album_id = ?;";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setInt(1, id);
            stmt.executeQuery();
        
            if(stmt != null) { stmt.close(); }
        
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void order(Connection conn) {
        // TODO Auto-generated method stub
        
    }
    
}
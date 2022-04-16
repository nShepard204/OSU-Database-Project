package DataModels;
import java.sql.*;

public class Movie extends RecordType{

    int id, year, artist_id, physical, digital, backorder, length;
    String title, genre, format;

    public Movie(int id, String title, String genre, int digital, int physical,
    int length, int backorder, int director_id, String format){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.physical = physical;
        this.digital = digital;
        this.backorder = backorder;
        this.artist_id = director_id;
        this.format = format;
    }

    @Override
    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Movie VALUES " +
        "(" + id + ", " + title + ", " + genre + ", " + length + ", " + artist_id + 
        physical + ", " + digital  + ", " + backorder + ", " + format +  ");";
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
    public void delete() {
        // TODO Auto-generated method stub
        
    }

    public void order(Connection conn) {
        // TODO Auto-generated method stub
        
    }
    
}
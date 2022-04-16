package DataModels;
import java.sql.*;

public class Album extends RecordType{

    int id, year, artist_id, track_count, duration, backorder, count;
    String title, genre, format;

    public Album(int id, String title, String genre, int year, int track_count,
    int duration, int backorder, int artist_id, String format, int count){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.track_count = track_count;
        this.duration = duration;
        this.backorder = backorder;
        this.artist_id = artist_id;
        this.format = format;
        this.count = count;
    }

    @Override
    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Album VALUES " +
        "(?, ?, ?, ?, ?, ?, ?, ?, ? , ?);";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(insertStr);
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, genre);
            stmt.setInt(4, year);
            stmt.setInt(5, track_count);
            stmt.setInt(6,duration);
            stmt.setInt(7, backorder);
            stmt.setInt(8, artist_id);
            stmt.setString(9, format);
            stmt.setInt(10, count);
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
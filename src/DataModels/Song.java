package DataModels;
import java.sql.*;

public class Song extends RecordType{

    int id, album_id, number, duration, size;
    String title;

    public Song(int id, int album_id, String title, int number, int duration, 
    int size_bytes){
        this.id = id;
        this.album_id = album_id;
        this.title = title;
        this.number = number;
        this.duration = duration;
        this.size = size_bytes;

    }

    @Override
    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Song VALUES " +
        "(" + id + ", " + album_id + ", " + title + ", " + number + ", " + duration + ", " + size + ");";
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
        String insertStr = "DELETE FROM Song \n" +
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
    
}
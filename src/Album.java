import java.sql.*;

public class Album extends MediaType{

    int id, year, artist_id, track_count, duration, backorder, count;
    String title, genre, format;

    public Album(int id, String title, String genre, int year, int track_count,
    int duration, int backorder, int album_count, int artist_id, String format){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.track_count = track_count;
        this.duration = duration;
        this.backorder = backorder;
        this.count = album_count;
        this.artist_id = artist_id;
        this.format = format;
    }

    @Override
    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Album VALUES " +
        "(" + id + ", " + title + year + ", " + genre + artist_id + ", " + track_count + 
        duration + ", " + backorder + format + ", " + count + ");";
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
    
}
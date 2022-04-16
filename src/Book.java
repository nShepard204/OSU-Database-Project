import java.sql.*;

public class Book extends RecordType{

    int id, author_id, physical, digital, backorder;
    String title, genre;

    public Book(int id, String title, String genre, int backorder, int album_count, 
    int author_id, int physical, int digital){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.physical = physical;
        this.digital = digital;
        this.backorder = backorder;
        this.author_id = author_id;
    }

    @Override
    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Book VALUES " +
        "(" + id + ", " + author_id + ", " + title + ", " + genre + ", " + physical + ", " + 
        digital + ", " + backorder + ");";
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
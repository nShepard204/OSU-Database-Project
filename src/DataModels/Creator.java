package DataModels;
import java.sql.*;

public class Creator extends RecordType{

    int id;
    String type, name;

    public Creator(String type, int id, String name){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO `" + type + "` VALUES " +
        "(`" + id + "`, `" + name + "`)";
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
        String insertStr = "DELETE FROM " + type + " \n" +
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
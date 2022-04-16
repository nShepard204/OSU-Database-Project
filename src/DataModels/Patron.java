package DataModels;
import java.sql.*;

public class Patron {
    String name, email, address;
    int lib_card_id;
    String order_list_name;

    public Patron(int id, String name, String email, String address){
        this.lib_card_id = id;
        this.name = name;
        this.email = email;
        this.address = address;

    }

    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Patron VALUES " +
        "(" + lib_card_id + ", " + name + ", " + email + ", " + address + ");";
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

    public void edit() {
        // TODO Auto-generated method stub
        
    }

    public void delete() {
        // TODO Auto-generated method stub
        
    }

}

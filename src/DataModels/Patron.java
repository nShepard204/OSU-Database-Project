package DataModels;
import java.sql.*;

public class Patron {
    String name, email, address, checkout_list_id;
    int id, lib_card_num;
    String order_list_name;

    public Patron(int id, int lib_card_num, String name, String email, String address){
        this.id = id;
        this.lib_card_num = lib_card_num;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Patron VALUES " +
        "(" + lib_card_num + ", " + name + ", " + email + ", " + address +
        ", " + checkout_list_id + ");";
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

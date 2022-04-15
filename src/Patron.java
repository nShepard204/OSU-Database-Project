import java.sql.*;

public class Patron {
    String name, email, address, checkout_list_id;
    int lib_card_id;
    String order_list_name;

    public Patron(String name, String email, String address,
    String chekout_id, int id){
        this.lib_card_id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.checkout_list_id = chekout_id;

    }

    public void add(Connection conn) {
        
        String insertStr = "INSERT INTO Patron VALUES " +
        "(" + lib_card_id + ", " + name + ", " + email + ", " + address +
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

    public void makeOrderList(Connection conn){
        //TODO: SQL code to make new order list table
    }

    public void addToOrderList(Connection conn){

    }

    public void updateOrderList(Connection conn){
        
    }

    public void edit() {
        // TODO Auto-generated method stub
        
    }

    public void delete() {
        // TODO Auto-generated method stub
        
    }

}

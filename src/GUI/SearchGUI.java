package GUI;

import javax.swing.*;

import TableModels.*;
import DataModels.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class SearchGUI extends JFrame implements ActionListener{
    // Class Swing Variables.
    private JPanel master, top, criteria, buttons;
    private JButton addFilter = new JButton("Add Filter");
    private JButton removeFilter = new JButton("Remove Filter");
    private JButton quit = ShepGuiUtils.makeQuitButton(this);
    private JButton submit = new JButton("Search Database");
    private JTable resultsTable = new JTable();

    // Other shit.
    private int criteriaCount = 0;
    private LinkedList<String> attrList = new LinkedList<String>(Arrays.asList("Select Column", "title", "genre"));
    private Connection conn;

    // Table Selector.
    private final String[] tables = {"N/A", "Patron"};
    private JComboBox<String> tblSelect = new JComboBox<String>(this.tables);
    
    // Constructor.
    public SearchGUI(Connection conn){
        // Initialize Master Panel.
        this.master = new JPanel();
        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));
        this.conn = conn;

        // Initialize Top Panel.
        this.top = new JPanel(new SpringLayout());
        JLabel tableLabel = new JLabel("Select Table to Search:", JLabel.TRAILING);
        tblSelect.addActionListener(this);
        this.top.add(tableLabel);
        this.top.add(tblSelect);
        SpringUtilities.makeGrid(this.top, 1, 2, 5, 5, 5, 5);

        // Initialize Center Panel.
        // Attribute Selector
        this.criteria = new JPanel(new SpringLayout());

        // Initialize Buttons Panel.
        this.buttons = new JPanel(new SpringLayout());
        this.addFilter.addActionListener(this);
        this.buttons.add(this.addFilter);
        this.removeFilter.addActionListener(this);
        this.buttons.add(this.removeFilter);
        this.quit.addActionListener(this);
        this.buttons.add(this.quit);
        this.submit.addActionListener(this);
        this.buttons.add(this.submit);
        SpringUtilities.makeGrid(this.buttons, 2, 2, 5, 5, 5, 5);

        // Bring it all together.
        this.master.add(this.top);
        this.master.add(this.criteria);
        this.master.add(this.buttons);
        this.setContentPane(this.master);
        this.setTitle("Search Database");
        ShepGuiUtils.frameCleanup(this);
    }

    private void executeSQL(){
        Component[] criteriaFields = this.criteria.getComponents();
        // Attribute, Value
        //Map<String, String> userInput = new HashMap<String, String>();
        LinkedList<String> criteriaStrings = new LinkedList<String>();
        String temp = "";
        for(Component c : criteriaFields){
            if(c.getName().equals("attrSelect") && c.getClass().equals(JComboBox.class))
            {
                JComboBox<?> tempBox = (JComboBox<?>)c;
                temp += String.valueOf(tempBox.getSelectedItem()) + " ";
            } 
            else if(c.getName().equals("opSelect") && c.getClass().equals(JComboBox.class))
            {
                JComboBox<?> tempBox = (JComboBox<?>)c;
                temp += String.valueOf(tempBox.getSelectedItem()) + " ";
            } 
            else if (c.getName().equals("userInput") && c.getClass().equals(JTextField.class))
            {
                JTextField field = (JTextField)c;
                temp += field.getText();
                criteriaStrings.add(temp);
                temp = "";
            }
        }
        
        //
        String table = String.valueOf(this.tblSelect.getSelectedItem());
        if(table == this.tables[1]){
            this.renderPatronTable();
        }
    }

    private void renderPatronTable(){
        ArrayList<Object[]> dataList = new ArrayList<Object[]>();
        ArrayList<String> columnNameList = new ArrayList<String>();

        String sqlStr = "SELECT * FROM Patron";
        try {
            PreparedStatement sqlStmt = conn.prepareStatement(sqlStr);
            ResultSet results = sqlStmt.executeQuery();
            ResultSetMetaData meta = results.getMetaData();

            int colCount = meta.getColumnCount();
            for(int i = 1; i <= colCount; i++){
                String colName = meta.getColumnName(i);
                columnNameList.add(colName);
            }
            
            while(results.next()){
                int id = results.getInt("id");
                int lib_card_num = results.getInt("lib_card_num");
                String name = results.getString("name");
                String email = results.getString("email");
                String address = results.getString("address");
                //Patron fuck = new Patron(id, lib_card_num, name, email, address);
                Object[] temp = {id, lib_card_num, name, email, address};
                dataList.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Object[][] data = new Object[dataList.size()][];
        for(int i = 0; i < dataList.size(); i++){
            data[i] = dataList.get(i);
        }
        String[] columnNames = new String[columnNameList.size()];
        for(int i = 0; i < columnNameList.size(); i++){
            columnNames[i] = columnNameList.get(i);
        }

        JFrame tableFrame = new JFrame();
        tableFrame.setTitle("Search Results");
        this.resultsTable = new JTable(new PatronModel(data, columnNames));
        this.resultsTable.setBounds(30, 40, 200, 300);
        // Set scroll pane.
        JScrollPane sp = new JScrollPane(this.resultsTable);
        tableFrame.add(sp);
        // Set the frame and render the table.
        tableFrame.setSize(500, 200);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

/*     private void renderTable(){
        JFrame tableFrame = new JFrame();
        tableFrame.setTitle("Search Results");
        this.resultsTable = new JTable(data, colNames);
        this.resultsTable.setBounds(30, 40, 200, 300);
        // Set scroll pane.
        JScrollPane sp = new JScrollPane(this.resultsTable);
        tableFrame.add(sp);
        // Set the frame and render the table.
        tableFrame.setSize(500, 200);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    } */

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource().equals(this.addFilter)){
            JComboBox<String> attrSelect = new JComboBox<String>(attrList.toArray(new String[attrList.size()]));
            attrSelect.setName("attrSelect");
            attrSelect.addActionListener(this);

            // Operator Selector.
            String[] operators = {"==", "!=", ">=", "<=", ">", "<"};
            JComboBox<String> opSelect = new JComboBox<String>(operators);
            opSelect.setName("opSelect");
            opSelect.addActionListener(this);

            // User Input for Comparison.
            JTextField userInput = new JTextField();
            userInput.setName("userInput");

            this.criteria.add(attrSelect, -1);
            this.criteria.add(opSelect, -1);
            this.criteria.add(userInput, -1);
            this.criteriaCount++;
            SpringUtilities.makeGrid(this.criteria, this.criteriaCount, 3, 5, 5, 5, 5);
            this.criteria.revalidate();
            this.criteria.repaint();
            this.pack();
        }

        if(e.getSource().equals(this.removeFilter)){
            // Remove the last three components from the criteria panel.
            int currentCount = this.criteria.getComponentCount();
            for(int i = 1; i <= 3; i++){
                this.criteria.remove(currentCount-i);
            }
            this.criteriaCount--;
            if(this.criteriaCount == 0){
                this.criteria.removeAll();
                this.master.remove(this.criteria);
                this.master.remove(this.buttons);
                this.criteria = new JPanel(new SpringLayout());
                this.master.add(this.criteria);
                this.master.add(this.buttons);
            } else {
                SpringUtilities.makeGrid(this.criteria, this.criteriaCount, 3, 5, 5, 5, 5);
            }
            this.criteria.revalidate();
            this.criteria.repaint();
            this.pack();

        }

        if(e.getSource().equals(this.submit)){
            System.out.println("Executing SQL");
            this.executeSQL();
        }

        
    }
}

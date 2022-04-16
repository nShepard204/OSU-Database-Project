package GUI;

import javax.swing.*;

import java.awt.Component;
import java.awt.event.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import DataModels.*;

public class DeleteGUI extends JFrame implements ActionListener{
    // Class Variables.
    private JPanel addPanel = new JPanel(new SpringLayout());
    private String[] tables = {"N/A", "Artist", "Actor", "Author", "Director", "Album", "Book", "Movie", "Song", "Checkout List", "Order List", "Patron"};
    //private String[] tables = {"no", "fuck", "shit"};
    private JComboBox<String> tableSelect = new JComboBox<String>(this.tables);

    // Spring Utility Variables.
    private int numColumns = 2, initialX = 5, initialY = 5, xPad = 5, yPad = 5;
    private String current = "";
    // Visual Components.
    private JButton quitButton = ShepGuiUtils.makeQuitButton(this);
    private JButton submitButton = new JButton("Add To Database");
    private Connection conn;

    // Constructor.
    public DeleteGUI(Connection conn){
        // Add selector logic to the layout.
        this.tableSelect.addActionListener(this);
        this.renderSelect();
        // Add cancel and execute buttons to the layout.
        this.submitButton.addActionListener(this);
        this.quitButton.addActionListener(this);
        this.renderButtons();
        // Prepare and render the GUI.
        this.renderGUI();
        this.conn = conn;
    }

    // Helper Methods.
    private void renderGUI(){
        System.out.println(this.addPanel.getComponentCount());
        int numRows = Math.max((this.addPanel.getComponentCount()/2), 2);
        SpringUtilities.makeGrid(this.addPanel, numRows, this.numColumns, this.initialX, this.initialY, this.xPad, this.yPad);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Delete Records");
        this.addPanel.revalidate();
        this.setContentPane(this.addPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void renderSelect(){
        JLabel selectLabel = new JLabel("Select Record Type to Delete:", JLabel.TRAILING);
        selectLabel.setLabelFor(this.tableSelect);
        this.addPanel.add(selectLabel);
        this.addPanel.add(this.tableSelect);
    }

    private void renderButtons(){
        this.addPanel.add(this.submitButton);
        this.addPanel.add(this.quitButton);
    }

    private void renderForm(){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel idLabel = new JLabel("Enter "+ this.current +" id:", JLabel.TRAILING);
        JTextField idField = new JTextField();
        idLabel.setLabelFor(idField);
        // Set field name to column attribute in DB (will be important later).
        idField.setName("id");
        this.addPanel.add(idLabel);
        this.addPanel.add(idField);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    

    private void executeSQL(){
        Component[] formFields = this.addPanel.getComponents();
        // Attribute, Value
        Map<String, String> userInput = new HashMap<String, String>();
        // For each text field in the form, grab the attribute name & its value.
        System.out.println(formFields[0].toString());
        for (Component c : formFields){
            if(c.getClass().equals(JTextField.class)){
                JTextField field = (JTextField)c;
                userInput.put(field.getName(), field.getText());
            }
        }
        switch(this.current){
            case("Album"):
                Album.delete(conn, Integer.parseInt(userInput.get("id")));
                break;
            case("Book"):
                Book.delete(conn, Integer.parseInt(userInput.get("id")));
                break;
            case("Movie"):
                Movie.delete(conn, Integer.parseInt(userInput.get("id")));
                break;
            case("Creator"):
                Creator.delete(conn, Integer.parseInt(userInput.get("id")));
                break;
            case("Patron"):
                Patron.delete(conn, Integer.parseInt(userInput.get("id")));
                break;
            case("Song"):
                Song.delete(conn, Integer.parseInt(userInput.get("id")));
                break;
        }

        /* This is there the variable type transmutation and actual SQL will go. */
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // Form Rendering Logic.
        if(e.getSource().equals(this.submitButton)){
            // SQL Logic Here. Perhaps its own method?
            this.executeSQL();
        }
        else if (e.getSource().equals(this.tableSelect)) {
            this.current = String.valueOf(this.tableSelect.getSelectedItem());
            // Add Artist to DB
            /* private String[] tables = {"N/A", "Artist", "Actor", "Author", "Director", "Album", "Book", "Movie", "Song", 
                    "Checkout List", "Order List", "Patron", }; */
            this.renderForm();
        }
        
    }

    
}

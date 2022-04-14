package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddGUI extends JFrame implements ActionListener{
    // Class Variables.
    private JPanel addPanel = new JPanel(new SpringLayout());
    private String[] tables = {"N/A", "Artist"};
    private JComboBox<String> tableSelect = new JComboBox<String>(this.tables);
    // Spring Utility Variables.
    private int numColumns = 2, initialX = 5, initialY = 5, xPad = 5, yPad = 5;
    private String current = "";
    // Visual Components.
    private JButton quitButton = ShepGuiUtils.makeQuitButton(this);
    private JButton submitButton = new JButton("Add To Database");

    // Constructor.
    public AddGUI(){
        // Add selector logic to the layout.
        this.tableSelect.addActionListener(this);
        this.configureSelect();
        // Add cancel and execute buttons to the layout.
        this.submitButton.addActionListener(this);
        this.quitButton.addActionListener(this);
        this.configureButtons();
        // Prepare and render the GUI.
        this.renderGUI();
    }

    // Helper Methods.
    private void renderGUI(){
        int numRows = Math.max((this.addPanel.getComponentCount()/2), 2);
        SpringUtilities.makeGrid(this.addPanel, numRows, this.numColumns, this.initialX, this.initialY, this.xPad, this.yPad);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Add Records");
        this.setContentPane(this.addPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void configureSelect(){
        JLabel selectLabel = new JLabel("Select Record Type to Add:", JLabel.TRAILING);
        selectLabel.setLabelFor(this.tableSelect);
        this.addPanel.add(selectLabel);
        this.addPanel.add(tableSelect);
    }

    private void configureButtons(){
        this.addPanel.add(this.submitButton);
        this.addPanel.add(this.quitButton);
    }

    private void renderArtistForm(){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.configureSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter Artists Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("name");
        this.addPanel.add(nameLabel);
        this.addPanel.add(nameField);
        // Add the buttons bac.
        this.configureButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    private void executeSQL(){
        Component[] formFields = this.addPanel.getComponents();
        // Attribute, Value
        Map<String, String> userInput = new HashMap<String, String>();
        // For each text field in the form, grab the attribute name & its value.
        for (Component c : formFields){
            if(c.getClass().equals(JTextField.class)){
                JTextField field = (JTextField)c;
                userInput.put(field.getName(), field.getText());
            }
        }
        
        /* This is there the variable type transmutation and actual SQL will go. */
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(this.submitButton)){
            // SQL Logic Here. Perhaps its own method?
            this.executeSQL();
        }
        // Form Rendering Logic.
        else if (e.getSource().equals(this.tableSelect)) {
            this.current = String.valueOf(this.tableSelect.getSelectedItem());
            // Add Artist to DB
            if(this.current.equals(this.tables[1])){ 
                this.renderArtistForm();
            }
            // Render Base Form (haha). 
            else {
                this.addPanel.removeAll();
                this.configureSelect();
                this.configureButtons();
                this.renderGUI();
            } 
        }
    }
}

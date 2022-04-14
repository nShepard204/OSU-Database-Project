package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EditGUI extends JFrame implements ActionListener{
    // Class Variables.
    private JPanel editPanel = new JPanel(new SpringLayout());
    private String[] tables = {"N/A", "Artist"};
    private JComboBox<String> tableSelect = new JComboBox<String>(this.tables);
    // Spring Utility Variables.
    private int numColumns = 2, initialX = 5, initialY = 5, xPad = 5, yPad = 5;
    private String current = "";
    private int editId = 0;
    // Visual Components.
    private JButton quitButton = ShepGuiUtils.makeQuitButton(this);
    private JButton submitButton = new JButton("Query Record");
    private JTextField idField = new JTextField();

    // Constructor.
    public EditGUI(){
        // Add selector logic to the layout.
        this.tableSelect.addActionListener(this);
        this.renderSelect();
        // Add cancel and execute buttons to the layout.
        this.submitButton.addActionListener(this);
        this.quitButton.addActionListener(this);
        this.idField.addActionListener(this);
        this.renderButtons();
        // Prepare and render the GUI.
        this.renderGUI();
    }

    // Helper Methods.
    private void renderGUI(){
        int numRows = Math.max((this.editPanel.getComponentCount()/2), 2);
        SpringUtilities.makeGrid(this.editPanel, numRows, this.numColumns, this.initialX, this.initialY, this.xPad, this.yPad);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Edit Records");
        this.setContentPane(this.editPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void renderSelect(){
        // Dropdown.
        JLabel selectLabel = new JLabel("Select Table to Query:", JLabel.TRAILING);
        selectLabel.setLabelFor(this.tableSelect);
        this.editPanel.add(selectLabel);
        this.editPanel.add(tableSelect);

        // ID to query.
        JLabel idLabel = new JLabel("Enter ID to Edit:", JLabel.TRAILING);
        idLabel.setLabelFor(idField);
        this.editPanel.add(idLabel);
        this.editPanel.add(idField);
    }

    private void renderButtons(){
        this.editPanel.add(this.submitButton);
        this.editPanel.add(this.quitButton);
    }

    private void renderArtistForm(){
        // Remove all components & add the selector back.
        this.editPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter Artists Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("name");
        this.editPanel.add(nameLabel);
        this.editPanel.add(nameField);
        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    private void executeSQL(){
        Component[] formFields = this.editPanel.getComponents();
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
        // Update the id field.
        if(e.getSource().equals(this.idField)){
            this.editId = Integer.parseInt(this.idField.getText());
        }

        // Get the record if our input is good.
        if(e.getSource().equals(this.submitButton) && this.editId != 0 && this.submitButton.getText() == "Query Record"){
            
        }

        // Get the record if our input is good.
        if(e.getSource().equals(this.submitButton) && this.editId != 0 && this.submitButton.getText() == "Update Record"){

        }
    }
}

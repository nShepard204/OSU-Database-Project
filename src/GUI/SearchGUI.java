package GUI;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SearchGUI extends JFrame implements ActionListener{
    // Class Variables.
    private JPanel searchPanel;
    private JTable resultsTable;
    private JComboBox<String> tblSelect;
    private JButton quitButton;
    private JButton submitButton;

    // Constants.
    private final String[] tables = {"N/A", "Movie"};

    // Constructor.
    public SearchGUI(){
        this.searchPanel = new JPanel(new SpringLayout());
        this.tblSelect = new JComboBox<String>(this.tables);
        // Button Setup.
        this.quitButton = ShepGuiUtils.makeQuitButton(this);
        this.quitButton.addActionListener(this);
        this.submitButton = new JButton("Find Records");
        this.submitButton.addActionListener(this);
        this.setTitle("Search Database");
    }

    @Override
    public void actionPerformed(ActionEvent e){

    }
}

package GUI;

// Imports.
import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainGUI extends JFrame implements ActionListener{
    // Class Variables.
    
    private void createButton(JPanel mainPane, String buttonTxt){
        JButton button = new JButton(buttonTxt);
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPane.add(button);
    }

    public MainGUI(){
        // Setup the frame and the panel to put components on.
        JFrame mainFrame = new JFrame("Team 11 CSE 3241 Database Project");
        JPanel mainPane = new JPanel();
        FlowLayout mainLayout = new FlowLayout(FlowLayout.CENTER, 10, 10);
        mainPane.setLayout(mainLayout);

        // Add buttons to the database.
        createButton(mainPane, "Add Records");
        createButton(mainPane, "Edit Records");
        createButton(mainPane, "Search Database");
        createButton(mainPane, "Manage Orders");
        createButton(mainPane, "Generate Reports");

        // Display the main gui.
        mainFrame.setContentPane(mainPane);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String buttonTxt = ((JButton)e.getSource()).getText();
        switch(buttonTxt){
            case "Add Records":
                new AddGUI();
                break;
            case "Edit Records":
                new EditGUI();
                break;
            case "Search Database":
                new SearchGUI();
                break;
            case "Manage Orders":
                new OrderGUI();
                break;
            case "Generate Reports":
                // new ReportGUI();
                break;
            default:
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.", "Error!", JOptionPane.ERROR_MESSAGE);
                break;
        }
        
    }
}

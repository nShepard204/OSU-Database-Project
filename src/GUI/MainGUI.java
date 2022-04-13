package GUI;

// Imports.
import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainGUI extends JFrame implements ActionListener{
    // Class Variables.
    private String[] buttonStrings = {"Add Records"};
    
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
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String button = ((JButton)e.getSource()).getText();
        System.out.println(button);
    }
}

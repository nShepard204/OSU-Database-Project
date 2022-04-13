// Imports.
import java.util.*;
import java.sql.*;
import javax.swing.*;

import GUI.MainGUI;
import GUI.SpringUtilities;

import java.awt.*;
import java.awt.event.*;


public class AppGUI {
    private static JFrame f = new JFrame();
    private static String[] listTables = {"N/A", "Artists", "Actors", "Authors", "Albums", "Books", "Movies", "Songs"};
    private static JComboBox<String> selector = new JComboBox<>(listTables);
    // Dimension Variables.
    private static final int buttonWidth = 200, buttonHeight = 30, guiWidth = 400, guiHeight = 400, buttonYPos = 40, buttonXPos = 95;

    // Makes a nice and easy quit button for the forms.
    private static JButton makeQuitButton(JFrame parent){
        JButton quitButton = new JButton("Cancel");
        quitButton.addActionListener(e -> {
            parent.dispose();
        });
        return quitButton;
    }

    // WIP message for incase we need it.
    private static void wipMessage(){
        JOptionPane.showMessageDialog(null, "This is WIP! Please try again later.", "Feature Not Implemented", JOptionPane.INFORMATION_MESSAGE);
    }

    // Handles the searching.
    private static void searchWindow(){
        String selectionStr = "";
        String message = "Select records to search for.";
        int result = JOptionPane.showOptionDialog(f, new Object[] {message, selector}, "Search Records", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if(result == JOptionPane.OK_OPTION){
            selectionStr = String.valueOf(selector.getSelectedItem());
        }

        // Adjust the next input form based on selectionStr's value.
        if(selectionStr.equals(listTables[0])){
            message = "Search for albums & songs by Artist Name.";
            String artistName = JOptionPane.showInputDialog(null, message);

            // SELECT record_id, title, release_year, genre FROM Album WHERE artist_id IN (SELECT id FROM Artist WHERE artist = artistName).
            // SELECT title, number, duration FROM Song WHERE album_id IN *The list of record IDs we get from the 1st query.*

            // Display the info in two tables, one for albums, one for songs.
        } else if(selectionStr.equals(listTables[1])){
            message = "Search for movies starring this actor.";
            String actorName = JOptionPane.showInputDialog(null, message);
            // Query & display all movies the actor was in.
        } else if(selectionStr.equals(listTables[2])){
            message = "Search for books written by this author.";
            String authorName = JOptionPane.showInputDialog(null, message);
            // Query & display all books written by the entered author.
        } else if(selectionStr.equals(listTables[3])){
            // Need to search for albums by: name, genre, possibly duration.

            // if genre; display all albums matching the genre.
            // if name; get the id of the album and display all songs that have that album ID.
        } else if(selectionStr.equals(listTables[4])){
            // Need to search for albums by: name, genre, possibly duration.

            // if genre; display all books matching the genre.
            // if name; return the books name and format info.
        } else if(selectionStr.equals(listTables[5])){ // Movies.
            // Search by name, genre, length, director

            // Display all the above info + format info.

        } else if(selectionStr.equals(listTables[6])){ // Songs.
            // Search by title.

            // Display song title, the album its on, and the name of the artist.
        }
    }

    private static void addClosers(JFrame add, JPanel addPanel){
        // Setup Spring Layout.
        int numRows = addPanel.getComponentCount()/2;
        SpringUtilities.makeCompactGrid(addPanel, numRows, 2, 5, 5, 5, 5);

        // Final operations.
        addPanel.setPreferredSize(addPanel.getPreferredSize());
        add.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add.setContentPane(addPanel);
        add.pack();
        add.setVisible(true);
    }

    private static void addWindow(){
        JFrame add = new JFrame("Add Records");
        JPanel addPanel = new JPanel(new SpringLayout());
        
        // Table Selector.
        JLabel selectorLabel = new JLabel("Select Record Type to Add:");
        selectorLabel.setLabelFor(selector);
        
        // Dynamic Form Shit.
        selector.addActionListener(e -> {
            System.out.println(selector.getSelectedItem());
            String selection = String.valueOf(selector.getSelectedItem());
            switch(selection){
                case "Artists":
                    JLabel label = new JLabel("Enter Artists Name:", JLabel.TRAILING);
                    JTextField name = new JTextField();
                    label.setLabelFor(name);
                    addPanel.add(label);
                    addPanel.add(name);
                    addClosers(add, addPanel);
                    addPanel.validate();
                    break;
                default:
                    break;
            }
        });

        
        // Add components to panel.
        addPanel.add(selectorLabel);
        addPanel.add(selector);
        addClosers(add, addPanel);
    }

    /**
     * General idea: ask the user for the id of record they want to edit and the table, give them a selection of what they want to edit and let it rock
     */
    private static void editWindow(){
        int numComponents = 0;
        JFrame edit = new JFrame();
        JPanel p = new JPanel(new SpringLayout());
        // id field.
        JLabel idLabel = new JLabel("Enter Record ID:", JLabel.TRAILING);
        JTextField idField = new JTextField(10);
        idLabel.setLabelFor(idField);
        p.add(idLabel);
        p.add(idField);
        numComponents += 1;

        // Table Selector.
        JLabel selectLabel = new JLabel("Select Record Type:", JLabel.TRAILING);
        selectLabel.setLabelFor(selector);
        p.add(selectLabel);
        p.add(selector);
        numComponents += 1;

        // Click a button = Query the database for that record. Close and relaunch. Include quit button.
        JButton queryButton = new JButton("Find Record");
        p.add(queryButton);
        queryButton.addActionListener(e -> {
            edit.setVisible(false);
            JFrame tempF = new JFrame();
            JPanel temp = new JPanel(new SpringLayout());
            // Most of the SQL goes here.
            int numAttrs = 2;
            String[] attrNames = {"id", "name"};
            String[] attrVals = {"1", "Foo Bar"};
            for(int i = 0; i < numAttrs; i++){
                JLabel attrName = new JLabel(attrNames[i], JLabel.TRAILING);
                JTextArea attrVal = new JTextArea(attrVals[i]);
                temp.add(attrName);
                temp.add(attrVal);
            }
            SpringUtilities.makeCompactGrid(temp, numAttrs, 2, 5, 5, 5, 5);
            tempF.setContentPane(temp);
            tempF.pack();
            tempF.setVisible(true);
        });
        JButton quitButton = new JButton("Cancel");
        p.add(quitButton);
        quitButton.addActionListener(e -> {
            edit.dispose();
        });
        numComponents += 1;

        // Render a series of JTextFields that have their pre-filled in values be the current values of the database.

        // Save button = update query.


        // Cleanup to render
        SpringUtilities.makeCompactGrid(p, numComponents, 2, 5, 5, 5, 5);
        edit.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        edit.setSize(guiWidth, guiHeight);
        edit.setResizable(false);
        edit.setLocationRelativeTo(null);
        edit.setTitle("Edit Screen");
        edit.setContentPane(p);
        edit.pack();
        edit.setVisible(true);
    }

    // Give them a selection of reports to run, and just execute the statements. No muss, no fuss.
    private static void reportsWindow(){
        JFrame reports = new JFrame();

        // Report #1: Tracks by ARTIST released before YEAR.
        // Use this as the skeleton for the test of the buttons.
        JButton reportOne = new JButton("Tracks by ARTIST released before YEAR");
        reportOne.addActionListener(e -> {
            reports.setVisible(false);
            JFrame rOneFrame = new JFrame("Tracks by ARTIST released before YEAR");
            JPanel rOnePanel = new JPanel(new SpringLayout());
            // Make the labels.
            JLabel artistLabel = new JLabel("Enter Artist Name:", JLabel.TRAILING);
            JLabel yearLabel = new JLabel("Enter Cutoff Year:", JLabel.TRAILING);
            // Make the fields.
            JTextField artistField = new JTextField();
            JTextField yearField = new JTextField();
            // Assign fields to the labels.
            artistLabel.setLabelFor(artistField);
            yearLabel.setLabelFor(yearField);
            // Make the confirmation buttons.
            JButton runButton = new JButton("Run Report");
            JButton quitButton = makeQuitButton(reports);
            // Set the run button to execute an SQL query.
            runButton.addActionListener(event -> {
                // SQL shit does here.
                JFrame tableFrame = new JFrame();
                tableFrame.setTitle("Report: Tracks by ARTIST released before YEAR");
                String[][] data = {
                    {"Burnout", "Dookie", "269", "1994"},
                    {"Burnout", "1,039/Smoothed Out Slappy Hours", "420", "1991"}
                };
                String[] colNames = {"Song Title", "Album", "Length", "Release Year"};
                JTable table = new JTable(data, colNames);
                table.setBounds(30, 40, 200, 300);
                // Set scroll pane.
                JScrollPane sp = new JScrollPane(table);
                tableFrame.add(sp);
                // Set the frame and render the table.
                tableFrame.setSize(500, 200);
                tableFrame.setVisible(true);
                tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            });
            // Add everything to the panel.
            rOnePanel.add(artistLabel);
            rOnePanel.add(artistField);
            rOnePanel.add(yearLabel);
            rOnePanel.add(yearField);
            rOnePanel.add(runButton);
            rOnePanel.add(quitButton);

            // Set the shit up and display the data.
            SpringUtilities.makeCompactGrid(rOnePanel, 3, 2, 5, 5, 5, 5);
            rOneFrame.setContentPane(rOnePanel);
            rOneFrame.pack();
            rOneFrame.setVisible(true);
        });
        reportOne.setBounds(buttonXPos, buttonYPos, buttonWidth, buttonHeight);
        reports.add(reportOne);

        // Report #2: Number of albums checked out by a single patron.
        JButton reportTwo = new JButton("Number of albums checked out by a single patron.");
        reportTwo.addActionListener(e -> {
            wipMessage();
        });
        reportTwo.setBounds(buttonXPos, buttonYPos + (buttonHeight * 2), buttonWidth, buttonHeight);
        reports.add(reportTwo);

        // Report #3: Most popular actor in the database.
        JButton reportThree = new JButton("Most popular actor in the database.");
        reportThree.addActionListener(e -> {
            wipMessage();
        });
        reportThree.setBounds(buttonXPos, buttonYPos + (buttonHeight * 4), buttonWidth, buttonHeight);
        reports.add(reportThree);

        // Report #4: Most listened to artist in the database.
        JButton reportFour = new JButton("Most listened to artist in the database.");
        reportFour.addActionListener(e -> {
            wipMessage();
        });
        reportFour.setBounds(buttonXPos, buttonYPos + (buttonHeight * 6), buttonWidth, buttonHeight);
        reports.add(reportFour);

        // Report #5: Patron who has checked out the most videos.
        JButton reportFive = new JButton("Patron who has checked out the most videos.");
        reportFive.addActionListener(e -> {
            wipMessage();
        });
        reportFive.setBounds(buttonXPos, buttonYPos + (buttonHeight *8), buttonWidth, buttonHeight);
        reports.add(reportFive);


        // Final Operations.
        reports.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        reports.setSize(guiHeight, guiWidth);
        reports.setLayout(null);
        reports.setResizable(false);
        reports.setLocationRelativeTo(f);
        reports.setTitle("Generate Reports");
        reports.setVisible(true);
    }

    // This should behave more or less like add except with some differences.
    private static void orderWindow(){

    }
    
    public static void main(String[] args){

        MainGUI app = new MainGUI();

        /* // Search Button.
        JButton searchButton = new JButton("Search Records");
        searchButton.setBounds(buttonXPos, buttonYPos, buttonWidth, buttonHeight);
        f.add(searchButton);
        searchButton.addActionListener(e -> searchWindow());

        // Add Button.
        JButton addButton = new JButton("Add Records");
        addButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 2), buttonWidth, buttonHeight);
        f.add(addButton);
        addButton.addActionListener(e -> addWindow());

        // Edit Button.
        JButton editButton = new JButton("Edit Records");
        editButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 4), buttonWidth, buttonHeight);
        f.add(editButton);
        editButton.addActionListener(e -> editWindow());

        // Order Button.
        JButton orderButton = new JButton("Manage Orders");
        orderButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 6), buttonWidth, buttonHeight);
        f.add(orderButton);
        orderButton.addActionListener(e -> wipMessage());

        // Reports Button.
        JButton reportsButton = new JButton("Generate Reports");
        reportsButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 8), buttonWidth, buttonHeight);
        f.add(reportsButton);
        reportsButton.addActionListener(e -> reportsWindow());

        // Render the app gui.
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(guiWidth, guiHeight);
        f.setLayout(null);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setTitle("Team 11 CSE 3241 Database Project");
        f.setVisible(true); */
    }
}

// Imports.
import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AppGUI {
    private static JFrame f = new JFrame();
    // Dimension Variables.
    private static final int buttonWidth = 200, buttonHeight = 30, guiWidth = 400, guiHeight = 400, buttonYPos = 40, buttonXPos = 95;

    // Handles the searching.
    private static void searchWindow(){
        JPanel p = new JPanel(new GridLayout(0, 1));
        String selectionStr = "";
        // Set the search selection dropdown.
        String[] options = {"Artists", "Actors", "Authors", "Albums", "Books", "Movies", "Songs"};
        JComboBox<String> searchSelect = new JComboBox<>(options);

        String message = "Select records to search for.";
        int result = JOptionPane.showOptionDialog(f, new Object[] {message, searchSelect}, "Search Records", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if(result == JOptionPane.OK_OPTION){
            selectionStr = String.valueOf(searchSelect.getSelectedItem());
        }

        // Adjust the next input form based on selectionStr's value.
        if(selectionStr.equals(options[0])){
            message = "Search for albums & songs by Artist Name.";
            String artistName = JOptionPane.showInputDialog(null, message);

            // SELECT record_id, title, release_year, genre FROM Album WHERE artist_id IN (SELECT id FROM Artist WHERE artist = artistName).
            // SELECT title, number, duration FROM Song WHERE album_id IN *The list of record IDs we get from the 1st query.*

            // Display the info in two tables, one for albums, one for songs.
        } else if(selectionStr.equals(options[1])){
            message = "Search for movies starring this actor.";
            String actorName = JOptionPane.showInputDialog(null, message);
            // Query & display all movies the actor was in.
        } else if(selectionStr.equals(options[2])){
            message = "Search for books written by this author.";
            String authorName = JOptionPane.showInputDialog(null, message);
            // Query & display all books written by the entered author.
        } else if(selectionStr.equals(options[3])){
            // Need to search for albums by: name, genre, possibly duration.

            // if genre; display all albums matching the genre.
            // if name; get the id of the album and display all songs that have that album ID.
        } else if(selectionStr.equals(options[4])){
            // Need to search for albums by: name, genre, possibly duration.

            // if genre; display all books matching the genre.
            // if name; return the books name and format info.
        } else if(selectionStr.equals(options[5])){ // Movies.
            // Search by name, genre, length, director

            // Display all the above info + format info.

        } else if(selectionStr.equals(options[6])){ // Songs.
            // Search by title.

            // Display song title, the album its on, and the name of the artist.
        }
    }
    
    public static void main(String[] args){

        // Search Button.
        JButton searchButton = new JButton("Search Records");
        searchButton.setBounds(buttonXPos, buttonYPos, buttonWidth, buttonHeight);
        f.add(searchButton);
        searchButton.addActionListener(e -> searchWindow());

        // Add Button.
        JButton addButton = new JButton("Add Records");
        addButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 2), buttonWidth, buttonHeight);
        f.add(addButton);

        // Edit Button.
        JButton editButton = new JButton("Edit Records");
        editButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 4), buttonWidth, buttonHeight);
        f.add(editButton);

        // Order Button.
        JButton orderButton = new JButton("Order Media");
        orderButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 6), buttonWidth, buttonHeight);
        f.add(orderButton);

        // Reports Button.
        JButton reportsButton = new JButton("Generate Reports");
        reportsButton.setBounds(buttonXPos, buttonYPos + (buttonHeight * 8), buttonWidth, buttonHeight);
        f.add(reportsButton);

        // Render the app gui.
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(guiWidth, guiHeight);
        f.setLayout(null);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setTitle("Team 11 CSE 3241 Database Project");
        f.setVisible(true);
    }
}

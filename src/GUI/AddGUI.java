package GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import DataModels.*;

public class AddGUI extends JFrame implements ActionListener{
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
    //private Connection conn;

    private int movie = 1000, album = 2000, book = 3000, song = 4000, patron = 5000, creator = 10000;

    // Add new records - Returns id of new record
    public int getID(String newRec){
        
        switch (newRec){
            case("Movie"):
                movie ++;
                return movie;
            case("Album"):
                album ++;
                return album;
            case("Book"):
                book ++;
                return book;
            case("Song"):
                song ++;
                return song;
            case("Patron"):
                patron ++;
                return patron;
            case("Creator"):
                creator ++;
                return creator;
        }
        return 0;
    }

    // Constructor.
    public AddGUI(){
        // Add selector logic to the layout.
        this.tableSelect.addActionListener(this);
        this.renderSelect();
        // Add cancel and execute buttons to the layout.
        this.submitButton.addActionListener(this);
        this.quitButton.addActionListener(this);
        this.renderButtons();
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

    private void renderSelect(){
        JLabel selectLabel = new JLabel("Select Record Type to Add:", JLabel.TRAILING);
        selectLabel.setLabelFor(this.tableSelect);
        this.addPanel.add(selectLabel);
        this.addPanel.add(this.tableSelect);
    }

    private void renderButtons(){
        this.addPanel.add(this.submitButton);
        this.addPanel.add(this.quitButton);
    }

    private RecordType renderCreatorForm(String table){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter "+ table +" Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("name");
        this.addPanel.add(nameLabel);
        this.addPanel.add(nameField);
        String name = nameField.getText();

        Creator creator = new Creator(table, 1, name);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
        return creator;
    }

    private RecordType renderAlbumForm(String table){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter Album Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("Title");
        this.addPanel.add(nameLabel);
        this.addPanel.add(nameField);
        String name = nameField.getText();

        JLabel yearLabel = new JLabel("Enter Album Release Year:", JLabel.TRAILING);
        JTextField yearField = new JTextField();
        yearLabel.setLabelFor(yearField);
        // Set field name to column attribute in DB (will be important later).
        yearField.setName("Release Year");
        this.addPanel.add(yearLabel);
        this.addPanel.add(yearField);
        String year = yearField.getText();

        JLabel genreLabel = new JLabel("Enter Album Genre:", JLabel.TRAILING);
        JTextField genreField = new JTextField();
        genreLabel.setLabelFor(genreField);
        // Set field name to column attribute in DB (will be important later).
        genreField.setName("Genre");
        this.addPanel.add(genreLabel);
        this.addPanel.add(genreField);
        String genre = genreField.getText();

        JLabel artistIDLabel = new JLabel("Enter Album Artist ID:", JLabel.TRAILING);
        JTextField artistIDField = new JTextField();
        artistIDLabel.setLabelFor(artistIDField);
        // Set field name to column attribute in DB (will be important later).
        artistIDField.setName("Artist ID");
        this.addPanel.add(artistIDLabel);
        this.addPanel.add(artistIDField);
        String id = artistIDField.getText();

        JLabel tcountLabel = new JLabel("Enter Album Track Count:", JLabel.TRAILING);
        JTextField tcountField = new JTextField();
        tcountLabel.setLabelFor(tcountField);
        // Set field name to column attribute in DB (will be important later).
        tcountField.setName("Track Count");
        this.addPanel.add(tcountLabel);
        this.addPanel.add(tcountField);
        String count = tcountField.getText();

        JLabel durationLabel = new JLabel("Enter Album Total Duration:", JLabel.TRAILING);
        JTextField durationField = new JTextField();
        durationLabel.setLabelFor(durationField);
        // Set field name to column attribute in DB (will be important later).
        durationField.setName("Duration");
        this.addPanel.add(durationLabel);
        this.addPanel.add(durationField);
        String dur = durationField.getText();

        JLabel formatLabel = new JLabel("Enter Album Format:", JLabel.TRAILING);
        JTextField formatField = new JTextField();
        formatLabel.setLabelFor(formatField);
        // Set field name to column attribute in DB (will be important later).
        formatField.setName("Format");
        this.addPanel.add(formatLabel);
        this.addPanel.add(formatField);
        String format = formatField.getText();
        int alcount = (album - 2000);
        Album album = new Album(getID("Album"), name, genre, Integer.parseInt(year), Integer.parseInt(count), Integer.parseInt(dur), 0, Integer.parseInt(id), format, alcount);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
        return album;
    }

    private RecordType renderBookForm(String table){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter Book Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("Title");
        this.addPanel.add(nameLabel);
        this.addPanel.add(nameField);
        String name = nameField.getText();

        JLabel genreLabel = new JLabel("Enter Book Genre:", JLabel.TRAILING);
        JTextField genreField = new JTextField();
        genreLabel.setLabelFor(genreField);
        // Set field name to column attribute in DB (will be important later).
        genreField.setName("Genre");
        this.addPanel.add(genreLabel);
        this.addPanel.add(genreField);
        String genre = genreField.getText();

        JLabel authorIDLabel = new JLabel("Enter Book Author ID:", JLabel.TRAILING);
        JTextField authorIDField = new JTextField();
        authorIDLabel.setLabelFor(authorIDField);
        // Set field name to column attribute in DB (will be important later).
        authorIDField.setName("Author ID");
        this.addPanel.add(authorIDLabel);
        this.addPanel.add(authorIDField);
        String id = authorIDField.getText();

        JLabel physicalLabel = new JLabel("Enter number of Physical Copies Avalible:", JLabel.TRAILING);
        JTextField physicalField = new JTextField();
        physicalLabel.setLabelFor(physicalField);
        // Set field name to column attribute in DB (will be important later).
        physicalField.setName("Physical Copies");
        this.addPanel.add(physicalLabel);
        this.addPanel.add(physicalField);
        String phys = physicalField.getText();

        JLabel digitalLabel = new JLabel("Enter number of Digital Copies Avalible:", JLabel.TRAILING);
        JTextField digitalField = new JTextField();
        digitalLabel.setLabelFor(digitalField);
        // Set field name to column attribute in DB (will be important later).
        digitalField.setName("Digital Copies");
        this.addPanel.add(digitalLabel);
        this.addPanel.add(digitalField);
        String dig = digitalField.getText();

        JLabel ABLabel = new JLabel("Enter number of Audio Books Avalible:", JLabel.TRAILING);
        JTextField ABField = new JTextField();
        ABLabel.setLabelFor(ABField);
        // Set field name to column attribute in DB (will be important later).
        ABField.setName("Audio Books");
        this.addPanel.add(ABLabel);
        this.addPanel.add(ABField);
        String AB = ABField.getText();

        Book book = new Book(getID("Book"), Integer.parseInt(id), name, genre, Integer.parseInt(phys), Integer.parseInt(dig), Integer.parseInt(AB), 0);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
        return book;
    }

    private RecordType renderMovieForm(String table){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter Movie Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("Title");
        this.addPanel.add(nameLabel);
        this.addPanel.add(nameField);
        String name;
        name = nameField.getText();

        JLabel genreLabel = new JLabel("Enter Movie Genre:", JLabel.TRAILING);
        JTextField genreField = new JTextField();
        genreLabel.setLabelFor(genreField);
        // Set field name to column attribute in DB (will be important later).
        genreField.setName("Genre");
        this.addPanel.add(genreLabel);
        this.addPanel.add(genreField);
        String genre;
        genre = genreField.getText();

        JLabel authorIDLabel = new JLabel("Enter Movie Author ID:", JLabel.TRAILING);
        JTextField authorIDField = new JTextField();
        authorIDLabel.setLabelFor(authorIDField);
        // Set field name to column attribute in DB (will be important later).
        authorIDField.setName("Author ID");
        this.addPanel.add(authorIDLabel);
        this.addPanel.add(authorIDField);
        String id;
        id = nameField.getText();

        JLabel durationLabel = new JLabel("Enter Movie Total Duration:", JLabel.TRAILING);
        JTextField durationField = new JTextField();
        durationLabel.setLabelFor(durationField);
        // Set field name to column attribute in DB (will be important later).
        durationField.setName("Duration");
        this.addPanel.add(durationLabel);
        this.addPanel.add(durationField);
        String dur;
        dur = durationField.getText();

        JLabel physicalLabel = new JLabel("Enter number of Physical Copies Avalible:", JLabel.TRAILING);
        JTextField physicalField = new JTextField();
        physicalLabel.setLabelFor(physicalField);
        // Set field name to column attribute in DB (will be important later).
        physicalField.setName("Physical Copies");
        this.addPanel.add(physicalLabel);
        this.addPanel.add(physicalField);
        String physical = physicalField.getText();

        JLabel digitalLabel = new JLabel("Enter number of Digital Copies Avalible:", JLabel.TRAILING);
        JTextField digitalField = new JTextField();
        digitalLabel.setLabelFor(digitalField);
        // Set field name to column attribute in DB (will be important later).
        digitalField.setName("Digital Copies");
        this.addPanel.add(digitalLabel);
        this.addPanel.add(digitalField);
        String digital = digitalField.getText();

        JLabel formatLabel = new JLabel("Enter Movie Format (DVD, Blueray or MP4):", JLabel.TRAILING);
        JTextField formatField = new JTextField();
        formatLabel.setLabelFor(formatField);
        // Set field name to column attribute in DB (will be important later).
        formatField.setName("Format");
        this.addPanel.add(formatLabel);
        this.addPanel.add(formatField);
        String format = formatField.getText();

        Movie movie = new Movie(getID("Movie"), name, genre, Integer.parseInt(dur), Integer.parseInt(id), 
        Integer.parseInt(physical), Integer.parseInt(digital), 0, format);
        

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
        return movie;
    }

    private RecordType renderSongForm(String table){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter Song Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("Title");
        this.addPanel.add(nameLabel);
        this.addPanel.add(nameField);
        String name;
        name = nameField.getText();

        JLabel albumIDLabel = new JLabel("Enter Album ID:", JLabel.TRAILING);
        JTextField albumIDField = new JTextField();
        albumIDLabel.setLabelFor(albumIDField);
        // Set field name to column attribute in DB (will be important later).
        albumIDField.setName("album ID");
        this.addPanel.add(albumIDLabel);
        this.addPanel.add(albumIDField);
        String id;
        id = albumIDField.getText();

        JLabel tcountLabel = new JLabel("Enter Track Number:", JLabel.TRAILING);
        JTextField tcountField = new JTextField();
        tcountLabel.setLabelFor(tcountField);
        // Set field name to column attribute in DB (will be important later).
        tcountField.setName("Track Number");
        this.addPanel.add(tcountLabel);
        this.addPanel.add(tcountField);
        String count;
        count = tcountField.getText();

        JLabel durationLabel = new JLabel("Enter Song Total Duration:", JLabel.TRAILING);
        JTextField durationField = new JTextField();
        durationLabel.setLabelFor(durationField);
        // Set field name to column attribute in DB (will be important later).
        durationField.setName("Duration");
        this.addPanel.add(durationLabel);
        this.addPanel.add(durationField);
        String dur;
        dur = durationField.getText();

        Song song = new Song(getID("Song"), Integer.parseInt(id), name, Integer.parseInt(count), Integer.parseInt(dur), 0);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();

        return song;
    }

    private RecordType renderPatronForm(String table){
        // Remove all components & add the selector back.
        this.addPanel.removeAll();
        this.renderSelect();

        // Add Input Fields.
        JLabel nameLabel = new JLabel("Enter Patron First and Last Name:", JLabel.TRAILING);
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);
        // Set field name to column attribute in DB (will be important later).
        nameField.setName("Name");
        this.addPanel.add(nameLabel);
        this.addPanel.add(nameField);
        String name;
        name = nameField.getText();

        JLabel emailLabel = new JLabel("Enter Email:", JLabel.TRAILING);
        JTextField emailField = new JTextField();
        emailLabel.setLabelFor(emailField);
        // Set field name to column attribute in DB (will be important later).
        emailField.setName("Email");
        this.addPanel.add(emailLabel);
        this.addPanel.add(emailField);
        String email;
        email = emailField.getText();

        JLabel addressLabel = new JLabel("Enter Address:", JLabel.TRAILING);
        JTextField addressField = new JTextField();
        addressLabel.setLabelFor(addressField);
        // Set field name to column attribute in DB (will be important later).
        addressField.setName("Address");
        this.addPanel.add(addressLabel);
        this.addPanel.add(addressField);
        String add;
        add = addressField.getText();

        Patron patron = new Patron(getID("Patron"), name, email, add);
        

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();

        return patron;
    }

    private void executeSQL(RecordType r){
        r.add(App.getApp().conn);
        /* This is there the variable type transmutation and actual SQL will go. */
    }

    @Override
    public void actionPerformed(ActionEvent e){
        RecordType r = null;
        // Form Rendering Logic.
        if (e.getSource().equals(this.tableSelect)) {
            this.current = String.valueOf(this.tableSelect.getSelectedItem());
            // Add Artist to DB
            /* private String[] tables = {"N/A", "Artist", "Actor", "Author", "Director", "Album", "Book", "Movie", "Song", 
                    "Checkout List", "Order List", "Patron", }; */
            if(this.current.equals(this.tables[1]) ||
            this.current.equals(this.tables[2]) ||
            this.current.equals(this.tables[3]) ||
            this.current.equals(this.tables[4])){ 
                r = this.renderCreatorForm(this.current);
            }else if(this.current.equals(this.tables[6])){ 
                r = this.renderAlbumForm(this.tables[6]);
            }else if(this.current.equals(this.tables[7])){ 
                r = this.renderBookForm(this.tables[7]);
            }else if(this.current.equals(this.tables[8])){ 
                r = this.renderMovieForm(this.tables[8]);
            }else if(this.current.equals(this.tables[9])){ 
                r = this.renderSongForm(this.tables[9]);
            }else if(this.current.equals(this.tables[10])){ 
                //this.renderCOLForm(this.tables[10]);
            }else if(this.current.equals(this.tables[11])){ 
                //this.renderOLForm(this.tables[11]);
            }else if(this.current.equals(this.tables[12])){ 
                r = this.renderPatronForm(this.tables[12]);
            }
            // Render Base Form (haha). 
            else {
                this.addPanel.removeAll();
                this.renderSelect();
                this.renderButtons();
                this.renderGUI();
            } 
        }
        if(e.getSource().equals(this.submitButton)){
            // SQL Logic Here. Perhaps its own method?
            this.executeSQL(r);
        }
    }

    
}



import javax.swing.*;

import GUI.ShepGuiUtils;
import GUI.SpringUtilities;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddGUI extends JFrame implements ActionListener{
    // Class Variables.
    private JPanel addPanel = new JPanel(new SpringLayout());
    private String[] tables = {"N/A", "Artist", "Actor", "Author", "Director", "Album", "Book", "Movie", "Song", 
                    "Checkout List", "Order List", "Patron", };
    private JComboBox<String> tableSelect = new JComboBox<String>(this.tables);
    // Spring Utility Variables.
    private int numColumns = 2, initialX = 5, initialY = 5, xPad = 5, yPad = 5;
    private String current = "";
    // Visual Components.
    private JButton quitButton = ShepGuiUtils.makeQuitButton(this);
    private JButton submitButton = new JButton("Add To Database");
    private Connection conn;

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
        this.addPanel.add(tableSelect);
    }

    private void renderButtons(){
        this.addPanel.add(this.submitButton);
        this.addPanel.add(this.quitButton);
    }

    private void renderCreatorForm(String table){
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
        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    private void renderAlbumForm(String table){
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

        JLabel yearLabel = new JLabel("Enter Album Release Year:", JLabel.TRAILING);
        JTextField yearField = new JTextField();
        yearLabel.setLabelFor(yearField);
        // Set field name to column attribute in DB (will be important later).
        yearField.setName("Release Year");
        this.addPanel.add(yearLabel);
        this.addPanel.add(yearField);

        JLabel genreLabel = new JLabel("Enter Album Genre:", JLabel.TRAILING);
        JTextField genreField = new JTextField();
        genreLabel.setLabelFor(genreField);
        // Set field name to column attribute in DB (will be important later).
        genreField.setName("Genre");
        this.addPanel.add(genreLabel);
        this.addPanel.add(genreField);

        JLabel artistIDLabel = new JLabel("Enter Album Artist ID:", JLabel.TRAILING);
        JTextField artistIDField = new JTextField();
        artistIDLabel.setLabelFor(artistIDField);
        // Set field name to column attribute in DB (will be important later).
        artistIDField.setName("Artist ID");
        this.addPanel.add(artistIDLabel);
        this.addPanel.add(artistIDField);

        JLabel tcountLabel = new JLabel("Enter Album Track Count:", JLabel.TRAILING);
        JTextField tcountField = new JTextField();
        tcountLabel.setLabelFor(tcountField);
        // Set field name to column attribute in DB (will be important later).
        tcountField.setName("Track Count");
        this.addPanel.add(tcountLabel);
        this.addPanel.add(tcountField);

        JLabel durationLabel = new JLabel("Enter Album Total Duration:", JLabel.TRAILING);
        JTextField durationField = new JTextField();
        durationLabel.setLabelFor(durationField);
        // Set field name to column attribute in DB (will be important later).
        durationField.setName("Duration");
        this.addPanel.add(durationLabel);
        this.addPanel.add(durationField);

        JLabel formatLabel = new JLabel("Enter Album Format:", JLabel.TRAILING);
        JTextField formatField = new JTextField();
        formatLabel.setLabelFor(formatField);
        // Set field name to column attribute in DB (will be important later).
        formatField.setName("Format");
        this.addPanel.add(formatLabel);
        this.addPanel.add(formatField);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    private void renderBookForm(String table){
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

        JLabel genreLabel = new JLabel("Enter Book Genre:", JLabel.TRAILING);
        JTextField genreField = new JTextField();
        genreLabel.setLabelFor(genreField);
        // Set field name to column attribute in DB (will be important later).
        genreField.setName("Genre");
        this.addPanel.add(genreLabel);
        this.addPanel.add(genreField);

        JLabel authorIDLabel = new JLabel("Enter Book Author ID:", JLabel.TRAILING);
        JTextField authorIDField = new JTextField();
        authorIDLabel.setLabelFor(authorIDField);
        // Set field name to column attribute in DB (will be important later).
        authorIDField.setName("Author ID");
        this.addPanel.add(authorIDLabel);
        this.addPanel.add(authorIDField);

        JLabel physicalLabel = new JLabel("Enter number of Physical Copies Avalible:", JLabel.TRAILING);
        JTextField physicalField = new JTextField();
        physicalLabel.setLabelFor(physicalField);
        // Set field name to column attribute in DB (will be important later).
        physicalField.setName("Physical Copies");
        this.addPanel.add(physicalLabel);
        this.addPanel.add(physicalField);

        JLabel digitalLabel = new JLabel("Enter number of Digital Copies Avalible:", JLabel.TRAILING);
        JTextField digitalField = new JTextField();
        digitalLabel.setLabelFor(digitalField);
        // Set field name to column attribute in DB (will be important later).
        digitalField.setName("Digital Copies");
        this.addPanel.add(digitalLabel);
        this.addPanel.add(digitalField);

        JLabel ABLabel = new JLabel("Enter number of Audio Books Avalible:", JLabel.TRAILING);
        JTextField ABField = new JTextField();
        ABLabel.setLabelFor(ABField);
        // Set field name to column attribute in DB (will be important later).
        ABField.setName("Audio Books");
        this.addPanel.add(ABLabel);
        this.addPanel.add(ABField);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    private void renderMovieForm(String table){
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

        JLabel genreLabel = new JLabel("Enter Movie Genre:", JLabel.TRAILING);
        JTextField genreField = new JTextField();
        genreLabel.setLabelFor(genreField);
        // Set field name to column attribute in DB (will be important later).
        genreField.setName("Genre");
        this.addPanel.add(genreLabel);
        this.addPanel.add(genreField);

        JLabel authorIDLabel = new JLabel("Enter Movie Author ID:", JLabel.TRAILING);
        JTextField authorIDField = new JTextField();
        authorIDLabel.setLabelFor(authorIDField);
        // Set field name to column attribute in DB (will be important later).
        authorIDField.setName("Author ID");
        this.addPanel.add(authorIDLabel);
        this.addPanel.add(authorIDField);

        JLabel durationLabel = new JLabel("Enter Movie Total Duration:", JLabel.TRAILING);
        JTextField durationField = new JTextField();
        durationLabel.setLabelFor(durationField);
        // Set field name to column attribute in DB (will be important later).
        durationField.setName("Duration");
        this.addPanel.add(durationLabel);
        this.addPanel.add(durationField);

        JLabel physicalLabel = new JLabel("Enter number of Physical Copies Avalible:", JLabel.TRAILING);
        JTextField physicalField = new JTextField();
        physicalLabel.setLabelFor(physicalField);
        // Set field name to column attribute in DB (will be important later).
        physicalField.setName("Physical Copies");
        this.addPanel.add(physicalLabel);
        this.addPanel.add(physicalField);

        JLabel digitalLabel = new JLabel("Enter number of Digital Copies Avalible:", JLabel.TRAILING);
        JTextField digitalField = new JTextField();
        digitalLabel.setLabelFor(digitalField);
        // Set field name to column attribute in DB (will be important later).
        digitalField.setName("Digital Copies");
        this.addPanel.add(digitalLabel);
        this.addPanel.add(digitalField);

        JLabel formatLabel = new JLabel("Enter Movie Format (DVD, Blueray or MP4):", JLabel.TRAILING);
        JTextField formatField = new JTextField();
        formatLabel.setLabelFor(formatField);
        // Set field name to column attribute in DB (will be important later).
        formatField.setName("Format");
        this.addPanel.add(formatLabel);
        this.addPanel.add(formatField);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    private void renderSongForm(String table){
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

        Song song = new Song(1, Integer.parseInt(id), name, Integer.parseInt(count), Integer.parseInt(dur), 0);
        song.add(App.getApp().conn);

        // Add the buttons bac.
        this.renderButtons();
        // Re-render our panel.
        this.renderGUI();
    }

    private void renderPatronForm(String table){
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

        Patron patron = new Patron(1, name, email, add);
        patron.add(App.getApp().conn);

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
            /* private String[] tables = {"N/A", "Artist", "Actor", "Author", "Director", "Album", "Book", "Movie", "Song", 
                    "Checkout List", "Order List", "Patron", }; */
            if(this.current.equals(this.tables[1]) ||
            this.current.equals(this.tables[2]) ||
            this.current.equals(this.tables[3]) ||
            this.current.equals(this.tables[4])){ 
                this.renderCreatorForm(this.current);
            }else if(this.current.equals(this.tables[6])){ 
                this.renderAlbumForm(this.tables[6]);
            }else if(this.current.equals(this.tables[7])){ 
                this.renderBookForm(this.tables[7]);
            }else if(this.current.equals(this.tables[8])){ 
                this.renderMovieForm(this.tables[8]);
            }else if(this.current.equals(this.tables[9])){ 
                this.renderSongForm(this.tables[9]);
            }else if(this.current.equals(this.tables[10])){ 
                //this.renderCOLForm(this.tables[10]);
            }else if(this.current.equals(this.tables[11])){ 
                //this.renderOLForm(this.tables[11]);
            }else if(this.current.equals(this.tables[12])){ 
                this.renderPatronForm(this.tables[12]);
            }
            // Render Base Form (haha). 
            else {
                this.addPanel.removeAll();
                this.renderSelect();
                this.renderButtons();
                this.renderGUI();
            } 
        }
    }
}

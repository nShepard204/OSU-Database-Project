package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SearchGUI extends JFrame implements ActionListener{
    // Class Swing Variables.
    private JPanel master, top, criteria, buttons;
    private JButton addFilter = new JButton("Add Filter");
    private JButton removeFilter = new JButton("Remove Filter");
    private JButton quit = ShepGuiUtils.makeQuitButton(this);
    private JButton submit = new JButton("Search Database");

    // Other shit.
    private int criteriaCount = 0;
    private LinkedList<String> attrList = new LinkedList<String>(Arrays.asList("Select Column", "title", "genre"));

    // Table Selector.
    private final String[] tables = {"N/A", "Movie"};
    private JComboBox<String> tblSelect = new JComboBox<String>(this.tables);
    
    // Constructor.
    public SearchGUI(){
        // Initialize Master Panel.
        this.master = new JPanel();
        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));

        // Initialize Top Panel.
        this.top = new JPanel(new SpringLayout());
        JLabel tableLabel = new JLabel("Select Table to Search:", JLabel.TRAILING);
        tblSelect.addActionListener(this);
        this.top.add(tableLabel);
        this.top.add(tblSelect);
        SpringUtilities.makeGrid(this.top, 1, 2, 5, 5, 5, 5);

        // Initialize Center Panel.
        // Attribute Selector
        this.criteria = new JPanel(new SpringLayout());

        // Initialize Buttons Panel.
        this.buttons = new JPanel(new SpringLayout());
        this.addFilter.addActionListener(this);
        this.buttons.add(this.addFilter);
        this.removeFilter.addActionListener(this);
        this.buttons.add(this.removeFilter);
        this.quit.addActionListener(this);
        this.buttons.add(this.quit);
        this.submit.addActionListener(this);
        this.buttons.add(this.submit);
        SpringUtilities.makeGrid(this.buttons, 2, 2, 5, 5, 5, 5);

        // Bring it all together.
        this.master.add(this.top);
        this.master.add(this.criteria);
        this.master.add(this.buttons);
        this.setContentPane(this.master);
        this.setTitle("Search Database");
        ShepGuiUtils.frameCleanup(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        System.out.println(this.criteriaCount);

        if(e.getSource().equals(this.addFilter)){
            JComboBox<String> attrSelect = new JComboBox<String>(attrList.toArray(new String[attrList.size()]));
            attrSelect.addActionListener(this);

            // Operator Selector.
            String[] operators = {"==", "!=", ">=", "<=", ">", "<"};
            JComboBox<String> opSelect = new JComboBox<String>(operators);
            opSelect.addActionListener(this);

            // User Input for Comparison.
            JTextField userInput = new JTextField();

            this.criteria.add(attrSelect, -1);
            this.criteria.add(opSelect, -1);
            this.criteria.add(userInput, -1);
            this.criteriaCount++;
            SpringUtilities.makeGrid(this.criteria, this.criteriaCount, 3, 5, 5, 5, 5);
            this.criteria.revalidate();
            this.criteria.repaint();
            this.pack();
        }

        if(e.getSource().equals(this.removeFilter)){
            // Remove the last three components from the criteria panel.
            int currentCount = this.criteria.getComponentCount();
            for(int i = 1; i <= 3; i++){
                this.criteria.remove(currentCount-i);
            }
            this.criteriaCount--;
            if(this.criteriaCount == 0){
                this.criteria.removeAll();
                this.master.remove(this.criteria);
                this.master.remove(this.buttons);
                this.criteria = new JPanel(new SpringLayout());
                this.master.add(this.criteria);
                this.master.add(this.buttons);
            } else {
                SpringUtilities.makeGrid(this.criteria, this.criteriaCount, 3, 5, 5, 5, 5);
            }
            this.criteria.revalidate();
            this.criteria.repaint();
            this.pack();

        }
    }
}

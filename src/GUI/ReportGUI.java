package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ReportGUI extends JFrame implements ActionListener {
    private final String[] reportOptions = {"Songs by ARTIST release before YEAR", "# of Albums checked out by a single patron", "Most popular actor in database", "Most listened to artist in database", "Patron who has checked out the most videos"};
    private JPanel reportPanel;
    private JComboBox<String> reportSelector;
    private JButton runReport;
    private JTable reportTable;

    public ReportGUI() {
        this.reportPanel = new JPanel(new SpringLayout());
        this.reportSelector = new JComboBox<String>(reportOptions);
        this.runReport = new JButton("Run Report");
        this.runReport.addActionListener(this);
        this.reportPanel.add(this.reportSelector);
        this.reportPanel.add(this.runReport);

        SpringUtilities.makeCompactGrid(this.reportPanel, 2, 1, 5, 5, 5, 5);
        this.setContentPane(this.reportPanel);
        this.setTitle("Run Reports");
        ShepGuiUtils.frameCleanup(this);
    }

    private void runFirstReport(){
        // Query for data.
        ArrayList<Object[]> dataList = new ArrayList<Object[]>();
        ArrayList<String> columnNameList = new ArrayList<String>();
        
        JFrame tableFrame = new JFrame(reportOptions[0]);
        //this.reportTable = new JTable(data, colNames);
        this.reportTable.setBounds(30, 40, 200, 300);
        this.reportTable.setEnabled(false);
        // Set scroll pane.
        JScrollPane sp = new JScrollPane(this.reportTable);
        tableFrame.add(sp);
        // Set the frame and render the table.
        tableFrame.setSize(500, 200);
        tableFrame.setVisible(true);
        tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.runReport)){
            ShepGuiUtils.wipMessage();
            /* String reportStr = String.valueOf(this.reportSelector.getSelectedItem());
            if(reportStr.equals(reportOptions[0])){
                runFirstReport();
            } else if(reportStr.equals(reportOptions[1])){
                // runSecondReport();
            } else if(reportStr.equals(reportOptions[2])){
                // runThirdReport();
            } else if(reportStr.equals(reportOptions[3])){
                // runFourthReport();
            } else if(reportStr.equals(reportOptions[4])){
                // runFifthReport();
            } else {
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.", "Error!", JOptionPane.ERROR_MESSAGE);
            } */
        }
    }
}

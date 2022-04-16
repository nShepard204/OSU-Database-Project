package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class OrderGUI extends JFrame implements ActionListener{

    public OrderGUI(){
        JPanel master = new JPanel();
        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));
        JPanel one = new JPanel();
        one.add(new JLabel("one"));
        JPanel two = new JPanel();
        two.add(new JLabel("two"));
        JPanel three = new JPanel();
        three.add(new JLabel("three"));

        master.add(one);
        master.add(two);
        master.add(three);
        this.setContentPane(master);
        ShepGuiUtils.frameCleanup(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
    }
}

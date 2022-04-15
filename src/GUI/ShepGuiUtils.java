package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShepGuiUtils {
    // Makes a quit button for the current frame.
    public static JButton makeQuitButton(JFrame parent){
        JButton quitButton = new JButton("Cancel");
        quitButton.addActionListener(e -> {
            parent.dispose();
        });
        return quitButton;
    }

    // Handles all the misc. bullshit for the frames.
    public static void frameCleanup(JFrame f){
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

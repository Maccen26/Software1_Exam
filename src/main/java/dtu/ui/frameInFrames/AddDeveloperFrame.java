package dtu.ui.frameInFrames;

import javax.swing.*;
import java.awt.*;

import dtu.app.*;
import dtu.domain.*;

public class AddDeveloperFrame extends JFrame{
    public AddDeveloperFrame(App app, Activity activity){
        setTitle("Add developer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(200, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add components
        JLabel developerLabel = new JLabel("Developer:");
        developerLabel.setBounds(0, 20, 200, 25);
        developerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(developerLabel);

        JTextField developerField = new JTextField();
        developerField.setBounds(60, 50, 80, 25);
        add(developerField);

        JButton addButton = new JButton("Add developer");
        addButton.setBounds(25, 100, 150, 30);
        addButton.addActionListener(e -> {
           String developerInitials = developerField.getText();
            if (developerInitials.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter developer initials", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                        Developer dev = app.getDeveloper(developerInitials);
                        activity.addDeveloper(app.getLoggedInDeveloper(), dev);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            setVisible(false);
            dispose();
            System.out.println("Tried to add developer");
        });
        add(addButton);
        
        setVisible(true);
    }   
}
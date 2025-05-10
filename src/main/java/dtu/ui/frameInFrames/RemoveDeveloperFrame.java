package dtu.ui.frameInFrames;

import javax.swing.*;

import dtu.app.*;
import dtu.domain.*;

public class RemoveDeveloperFrame extends JFrame{
    public RemoveDeveloperFrame(App app, Activity activity){
        setTitle("Remove developer");
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

        JButton removeButton = new JButton("Remove developer");
        removeButton.setBounds(25, 100, 150, 30);
        removeButton.addActionListener(e -> {
        String developerInitials = developerField.getText();
            if (developerInitials.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter developer initials", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                        Developer dev = app.getDeveloper(developerInitials);
                        //activity.removeDeveloper(app.getLoggedInDeveloper(), dev);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            setVisible(false);
            dispose();
            System.out.println("Tried to remove developer");
        });
        add(removeButton);
        
        setVisible(true);
    }
}
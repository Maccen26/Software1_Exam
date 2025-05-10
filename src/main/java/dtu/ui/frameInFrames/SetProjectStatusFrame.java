package dtu.ui.frameInFrames;

import javax.swing.*;

import dtu.app.*;
import dtu.domain.*;

public class SetProjectStatusFrame extends JFrame{
    public SetProjectStatusFrame(App app, Project project){
        setTitle("Set status");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(200, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add components
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(0, 20, 200, 25);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel);

        JTextField statusField = new JTextField();
        statusField.setBounds(60, 50, 80, 25);
        add(statusField);

        JButton setButton = new JButton("Set status");
        setButton.setBounds(25, 100, 150, 30);
        setButton.addActionListener(e -> {
            String status = statusField.getText();
            if (status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a status", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    project.setStatus(statusField.getText(), app.getLoggedInDeveloper());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            setVisible(false);
            dispose();
            System.out.println("Tried to set status");
        });
        add(setButton);

        setVisible(true);
    }
    
}

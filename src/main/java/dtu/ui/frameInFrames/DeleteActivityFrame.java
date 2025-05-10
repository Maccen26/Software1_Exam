package dtu.ui.frameInFrames;

import java.awt.*;

import javax.swing.*;

import dtu.app.*;
import dtu.domain.*;

public class DeleteActivityFrame extends JFrame {
    public DeleteActivityFrame(App app, Project project) {
        setTitle("Delete activity");
        setSize(200, 300);
        setLayout(null);
        setLocationRelativeTo(null); 
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        // Add components
        JLabel deleteLabel = new JLabel("Delete activity:");
        deleteLabel.setBounds(0, 20, 200, 25);
        deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(deleteLabel);

        JTextField activityName = new JTextField();
        activityName.setBounds(60, 50, 80, 25);
        add(activityName);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(50, 100, 100, 30);
        deleteButton.addActionListener(e -> {
            String activityNameText = activityName.getText();
            if (!activityNameText.isEmpty()) {
                try {
                    project.removeActivity(app.getLoggedInDeveloper(), activityNameText);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            setVisible(false);
            dispose();
            System.out.println("Deleted activity");
        });
        add(deleteButton);
        
        setVisible(true);
    }
}

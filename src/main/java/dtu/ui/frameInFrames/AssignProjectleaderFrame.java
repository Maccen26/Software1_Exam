package dtu.ui.frameInFrames;

import javax.swing.*;

import dtu.app.App;
import dtu.domain.Project;

public class AssignProjectleaderFrame extends JFrame {
    public AssignProjectleaderFrame(App app, Project project) {
        // Set up the frame
        setTitle("Assign project leader");
        setSize(200, 300);
        setLayout(null);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure windowClosed is triggered

        // Add components
        JLabel projectLeader = new JLabel("Project leader:");
        projectLeader.setBounds(0, 20, 200, 25);
        projectLeader.setHorizontalAlignment(SwingConstants.CENTER);
        add(projectLeader);

        JTextField projectLeaderField = new JTextField();
        projectLeaderField.setBounds(60, 50, 80, 25);
        add(projectLeaderField);

        JButton assignButton = new JButton("Assign");
        assignButton.setBounds(50, 100, 100, 30);
        assignButton.addActionListener(e -> {
            String projectLeaderName = projectLeaderField.getText();
            try{
                project.assignProjectLeader(app.getLoggedInDeveloper(), app.getDeveloper(projectLeaderName));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            setVisible(false);
            dispose();
            System.out.println("Created project");
        });
        add(assignButton);

        // Make the frame visible
        setVisible(true);
    }
}

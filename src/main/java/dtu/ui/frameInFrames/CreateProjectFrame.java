package dtu.ui.frameInFrames;

import javax.swing.*;

import dtu.app.*;

public class CreateProjectFrame extends JFrame {

    public CreateProjectFrame(App app) {
        // Set up the frame
        setTitle("Create project");
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

        JButton createButton = new JButton("Create project");
        createButton.setBounds(50, 100, 100, 30);
        createButton.addActionListener(e -> {
            String projectLeaderName = projectLeaderField.getText();
            if (projectLeaderName.isEmpty()) {
                app.createProject();
            } else {
                try {
                    app.createProject(app.getDeveloper(projectLeaderName));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            setVisible(false);
            dispose();
            System.out.println("Created project");
        });
        add(createButton);

        JLabel note = new JLabel("Note: Project leader");
        note.setBounds(0, 150, 200, 25);
        note.setHorizontalAlignment(SwingConstants.CENTER);
        add(note);

        JLabel note2 = new JLabel("is not necessary");
        note2.setBounds(0, 170, 200, 25);
        note2.setHorizontalAlignment(SwingConstants.CENTER);
        add(note2);

        // Make the frame visible
        setVisible(true);
    }
}
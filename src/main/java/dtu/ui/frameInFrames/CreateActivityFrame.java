package dtu.ui.frameInFrames;

import java.awt.*;

import javax.swing.*;

import dtu.app.*;
import dtu.domain.*;

public class CreateActivityFrame extends JFrame {
    public CreateActivityFrame(App app, Project givenProject) {
        // Set up the frame
        setTitle("Create activity");
        setSize(300, 400);
        setLayout(new GridLayout(2, 1, 0, 10));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add components
        JPanel panel = new JPanel(new GridLayout(6, 2, 0, 5));
        add(panel);

        JLabel projectLabel = new JLabel("Assigned project:");
        JTextField projectField = new JTextField();
        if (givenProject == null) {
            panel.add(projectLabel);
            panel.add(projectField);
        }

        JLabel nameLabel = new JLabel("Activity name:");
        panel.add(nameLabel);
        JTextField nameField = new JTextField();
        panel.add(nameField);

        JLabel startWeekLabel = new JLabel("Start week:");
        panel.add(startWeekLabel);
        JTextField startWeekField = new JTextField();
        panel.add(startWeekField);

        JLabel startYearLabel = new JLabel("Start year:");
        panel.add(startYearLabel);
        JTextField startYearField = new JTextField();
        panel.add(startYearField);

        JLabel endWeekLabel = new JLabel("End week:");
        panel.add(endWeekLabel);
        JTextField endWeekField = new JTextField();
        panel.add(endWeekField);

        JLabel endYearLabel = new JLabel("End year:");
        panel.add(endYearLabel);
        JTextField endYearField = new JTextField();
        panel.add(endYearField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(buttonPanel);

        JButton createButton = new JButton("Create activity");
        createButton.addActionListener(e -> {
            if ((projectField.getText().isEmpty() && givenProject == null)|| nameField.getText().isEmpty() || startWeekField.getText().isEmpty()
                    || startYearField.getText().isEmpty() || endWeekField.getText().isEmpty()
                    || endYearField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String projectName = projectField.getText();
            String activityName = nameField.getText();
            int startWeek = Integer.parseInt(startWeekField.getText());
            int startYear = Integer.parseInt(startYearField.getText());
            int endWeek = Integer.parseInt(endWeekField.getText());
            int endYear = Integer.parseInt(endYearField.getText());
            int startTime = (startYear - 2020) * 52 + startWeek;
            int endTime = (endYear - 2020) * 52 + endWeek;
            
            if (startTime > endTime) {
                JOptionPane.showMessageDialog(this, "Start date must be before end date", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (startWeek < 1 || startWeek > 52 || endWeek < 1 || endWeek > 52) {
                JOptionPane.showMessageDialog(this, "Week must be between 1 and 52", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (startYear > endYear || (startYear == endYear && startWeek > endWeek)) {
                JOptionPane.showMessageDialog(this, "Start date must be before end date", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!app.hasProject(projectName) && givenProject == null) {
                JOptionPane.showMessageDialog(this, "Project does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Project project = app.getProject(projectName);
            if (givenProject != null) {
                project = givenProject;
            }
            

            if (project.containsActivityName(activityName)) {
                JOptionPane.showMessageDialog(this, "Activity already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                project.addActivity(app.getLoggedInDeveloper(), activityName, new int[] { startWeek, endWeek },
                        new int[] { startYear, endYear });
            } catch (AssertionError ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            setVisible(false);
            dispose();
        });
        buttonPanel.add(createButton);

        // Make the frame visible
        setVisible(true);
    }
}

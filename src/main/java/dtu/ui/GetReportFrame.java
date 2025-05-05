package dtu.ui;

import javax.swing.*;

import dtu.app.*;
import dtu.domain.*;

public class GetReportFrame extends Ui{
    private JFrame getReportFrame;

    

    public GetReportFrame(App app) { // Lukas
        app.createProject();

        getReportFrame = new JFrame("Get Report");
        getReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getReportFrame.setSize(300, 300);
        getReportFrame.setLayout(null);
        getReportFrame.setLocationRelativeTo(null); // Center the frame on the screen
        getReportFrame.setResizable(false);

        JLabel reportLabel = new JLabel("Report:");
        reportLabel.setBounds(10, 10, 200, 200);
        getReportFrame.add(reportLabel);

        JTextField projectNumberField = new JTextField("Enter project number:");
        projectNumberField.setBounds(10, 10, 200, 30);
        getReportFrame.add(projectNumberField);

        JButton getReportButton = new JButton("Get Report");
        getReportButton.setBounds(100, 50, 100, 30);
        getReportButton.addActionListener(e -> {
            String projectNumber = projectNumberField.getText();
            Project project = app.getProject(projectNumber);
            if (project == null) {
                reportLabel.setText("Project not found");
                return;
            }
            reportLabel.setText(project.getReport());
            System.out.println("Got report for project");
        });
        getReportFrame.add(getReportButton);
    }
    
    public void show() { // Lukas
        getReportFrame.setVisible(true);
    }
    public void hide() { // Lukas
        getReportFrame.setVisible(false);
    }
}

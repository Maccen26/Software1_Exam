package dtu.ui;

import dtu.app.*;
import dtu.domain.*;

import java.util.ArrayList;

import javax.swing.*;

public class EditActivityFrame extends Ui{
    private JFrame editActivityFrame;

    private Activity activity = new Activity("Activity1", "25021", new ArrayList<Developer>(), 30, new int[]{20, 25}, new int[]{2025, 2025});

    public EditActivityFrame() { // Lukas
        editActivityFrame = new JFrame("Edit Activity");
        editActivityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editActivityFrame.setSize(300, 500);
        editActivityFrame.setLayout(null);
        editActivityFrame.setLocationRelativeTo(null); // Center the frame on the screen
        editActivityFrame.setResizable(false);

        JTextField activityNameField = new JTextField();
        activityNameField.setBounds(50, 50, 200, 30);
        editActivityFrame.add(activityNameField);
        JTextField activityTimeField = new JTextField();
        activityTimeField.setBounds(50, 100, 200, 30);
        editActivityFrame.add(activityTimeField);
        JTextField startWeekField = new JTextField();
        startWeekField.setBounds(50, 150, 200, 30);
        editActivityFrame.add(startWeekField);
        JTextField endWeekField = new JTextField();
        endWeekField.setBounds(50, 200, 200, 30);
        editActivityFrame.add(endWeekField);
        JTextField startYearField = new JTextField();
        startYearField.setBounds(50, 250, 200, 30);
        editActivityFrame.add(startYearField);
        JTextField endYearField = new JTextField();
        endYearField.setBounds(50, 300, 200, 30);
        editActivityFrame.add(endYearField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100, 350, 100, 30);
        saveButton.addActionListener(e -> {
            String activityName = activityNameField.getText();
            int activityTime = Integer.parseInt(activityTimeField.getText());
            int startWeek = Integer.parseInt(startWeekField.getText());
            int endWeek = Integer.parseInt(endWeekField.getText());
            int startYear = Integer.parseInt(startYearField.getText());
            int endYear = Integer.parseInt(endYearField.getText());

            activity.setName(activityName);
            activity.setTimeBudget(activityTime);
            activity.setWeekPlan(new int[]{startWeek, endWeek});
            activity.setYearPlan(new int[]{startYear, endYear});

            System.out.println("Activity saved: " + activity.getName());
            System.out.println("Time budget: " + activity.getTimeBudget());
            System.out.println("Week plan: " + activity.getWeekPlan()[0] + " - " + activity.getWeekPlan()[1]);
            System.out.println("Year plan: " + activity.getYearPlan()[0] + " - " + activity.getYearPlan()[1]);
        });
        editActivityFrame.add(saveButton);
    }

    public void show() { // Lukas
        editActivityFrame.setVisible(true);
    }
    public void hide() { // Lukas
        editActivityFrame.setVisible(false);
    }
}

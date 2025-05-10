package dtu.ui.frameInFrames;

import dtu.app.*;
import dtu.domain.*;

import java.awt.*;
import javax.swing.*;

public class EditActivityFrame extends JFrame{
    public EditActivityFrame(App app, Activity activity){
        setTitle("Edit activity");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setLayout(new GridLayout(2, 1, 0, 10));
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(8, 2, 0, 5));
        add(panel);

        JLabel nameLabel = new JLabel("Activity name:");
        panel.add(nameLabel);
        JTextField nameField = new JTextField();
        panel.add(nameField);

        JLabel activityTimeLabel = new JLabel("Activity time:");
        panel.add(activityTimeLabel);
        JTextField activityTimeField = new JTextField();
        panel.add(activityTimeField);

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

        JButton saveButton = new JButton("Save");
        Developer dev = app.getLoggedInDeveloper();
        saveButton.addActionListener(e -> {
            try{
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    activity.setName(dev, nameField.getText());
                }

                String activityTime = activityTimeField.getText();
                if (!activityTime.isEmpty()) {
                    activity.setTimeBudget(dev, Double.parseDouble(activityTimeField.getText()));
                }

                String startWeek = startWeekField.getText();
                String startYear = startYearField.getText();
                String endWeek = endWeekField.getText();
                String endYear = endYearField.getText();
                int startWeekInt = 0;
                int startYearInt = 0;
                int endWeekInt = 0;
                int endYearInt = 0;
                if (!startWeek.isEmpty()){
                    startWeekInt = Integer.parseInt(startWeekField.getText());
                } else {
                    startWeekInt = activity.getWeekPlan()[0];
                }
                if (!startYear.isEmpty()){
                    startYearInt = Integer.parseInt(startYearField.getText());
                } else {
                    startYearInt = activity.getYearPlan()[0];
                }
                if (!endWeek.isEmpty()){
                    endWeekInt = Integer.parseInt(endWeekField.getText());
                } else {
                    endWeekInt = activity.getWeekPlan()[1];
                }
                if (!endYear.isEmpty()){
                    endYearInt = Integer.parseInt(endYearField.getText());
                } else {
                    endYearInt = activity.getYearPlan()[1];
                }
                int startTime = (startYearInt - 2020) * 52 + startWeekInt;
                int endTime = (endYearInt - 2020) * 52 + endWeekInt;
                if (startTime > endTime) {
                    JOptionPane.showMessageDialog(this, "Start date must be before end date", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (startWeekInt < 1 || startWeekInt > 52 || endWeekInt < 1 || endWeekInt > 52) {
                    JOptionPane.showMessageDialog(this, "Week must be between 1 and 52", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                activity.setWeekPlan(dev, new int[]{startWeekInt, endWeekInt});
                activity.setYearPlan(dev, new int[]{startYearInt, endYearInt});
            } catch (ErrorMessage error){
                JOptionPane.showMessageDialog(this, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            setVisible(false);
            dispose();
        });
        buttonPanel.add(saveButton);

        JLabel note = new JLabel("Note: only filled fields will be updated");
        buttonPanel.add(note);

        setVisible(true);
    }
}

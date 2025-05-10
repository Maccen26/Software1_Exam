package dtu.ui.frameInFrames;

import javax.swing.*;

import dtu.app.*;
import dtu.domain.*;

public class RegisterTimeFrame extends JFrame{
    public RegisterTimeFrame(App app, Activity activity){
        setTitle("Register time");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(200, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add components
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(0, 20, 200, 25);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(60, 50, 80, 25);
        add(timeField);

        JButton registerButton = new JButton("Register time");
        registerButton.setBounds(25, 100, 150, 30);
        registerButton.addActionListener(e -> {
            String time = timeField.getText();
            if (time.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a time", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //activity.registerTime(app.getLoggedInDeveloper(), Integer.parseInt(time));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            setVisible(false);
            dispose();
            System.out.println("Tried to register time");
        });
        add(registerButton);

        setVisible(true);
    }
}

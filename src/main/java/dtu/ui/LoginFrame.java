package dtu.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;

import dtu.app.*;
import dtu.domain.*;
import dtu.ui.*;

public class LoginFrame{
	private JFrame loginFrame;
    private String initials;

	public LoginFrame(App app){ // Lukas
		loginFrame = new JFrame("Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(1000, 700);

        JTextField initialsField = new JTextField();
        initialsField.setBounds(400, 150, 200, 30);
        loginFrame.add(initialsField);

        initialsField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (initialsField.getText().equals("Enter initials")) {
                    initialsField.setText("");
                    initialsField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (initialsField.getText().isEmpty()) {
                    initialsField.setText("Enter initials");
                    initialsField.setForeground(Color.GRAY);
                }
            }
        });

        JLabel label = new JLabel("Project Management System");
        label.setBounds(0, 100, 1000, 30);
        loginFrame.add(label);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setFont(label.getFont().deriveFont(20f));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            initials = initialsField.getText();
            System.out.println("Logged in with initials: " + initials);
            if (app.hasDeveloper(initials)) {
                System.out.println("Developer exists");
                ActivityOverviewFrame activityOverviewFrame = new ActivityOverviewFrame(app);
                loginFrame.setVisible(false);
                loginFrame.dispose();
            } else {
                System.out.println("Developer does not exist");
                initialsField.setText("Developer does not exist");
                initialsField.setForeground(java.awt.Color.RED);
            }
        });
        loginFrame.add(loginButton);
        loginButton.setBounds(400, 200, 200, 30);
        loginFrame.setLayout(null);
        loginFrame.setLocationRelativeTo(null); // Center the frame on the screen
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
	}
}
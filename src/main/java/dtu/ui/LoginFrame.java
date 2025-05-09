package dtu.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;

import dtu.app.*;

public class LoginFrame {
    private JFrame loginFrame;
    private String initials;

    public LoginFrame(App app) {
        // Create the login frame
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(1000, 700);

        // Header panel
        JPanel header = new JPanel();
        header.setBackground(Color.GRAY);
        header.setPreferredSize(new Dimension(1000, 80));
        header.setLayout(new GridLayout(1, 3));

        JLabel title = new JLabel("Project Management System");
        title.setForeground(Color.BLACK);
        title.setFont(title.getFont().deriveFont(40f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title);
        loginFrame.add(header, BorderLayout.NORTH);

        // Main panel
        JPanel main = new JPanel();
        main.setLayout(null);
        loginFrame.add(main, BorderLayout.CENTER);

        // Text field for initials
        JTextField initialsField = new JTextField("Enter initials");
        initialsField.setBounds(400, 150, 200, 30);
        initialsField.setForeground(Color.GRAY);
        main.add(initialsField);
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

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(400, 200, 200, 30);
        loginButton.addActionListener(e -> {
            initials = initialsField.getText();
            System.out.println("Logged in with initials: " + initials);
            if (app.hasDeveloper(initials)) {
                System.out.println("Developer exists");
                new ActivityOverviewFrame(app);
                loginFrame.setVisible(false);
                loginFrame.dispose();
            } else {
                System.out.println("Developer does not exist");
                initialsField.setText("Developer does not exist");
                initialsField.setForeground(Color.RED);
            }
        });
        main.add(loginButton);

        // Finalize frame
        loginFrame.setLocationRelativeTo(null); // Center the frame on the screen
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
    }
}
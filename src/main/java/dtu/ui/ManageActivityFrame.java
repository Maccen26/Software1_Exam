package dtu.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import dtu.app.*;
import dtu.domain.*;
import dtu.ui.frameInFrames.*;

public class ManageActivityFrame {
    private JFrame manageActivityFrame;

    public ManageActivityFrame(App app, Activity activity) {
        Project project = activity.getProject();

        // Create the login frame
        manageActivityFrame = new JFrame("Login");
        manageActivityFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manageActivityFrame.setSize(1000, 700);

        // Header panel
        JPanel header = new JPanel();
        header.setBackground(Color.GRAY);
        header.setPreferredSize(new Dimension(1000, 80));
        header.setLayout(new BorderLayout());

        JLabel dropdownLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/dtu/icons/Account.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dropdownLabel.setIcon(scaledIcon);
        header.add(dropdownLabel, BorderLayout.WEST);

        JPanel dropdownContainer = new JPanel();
        dropdownContainer.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column
        dropdownContainer.setBounds(0, 80, 150, 75);
        dropdownContainer.setBackground(Color.LIGHT_GRAY);
        dropdownContainer.setVisible(false); // Initially hidden
        manageActivityFrame.add(dropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton activityOverviewButton = new JButton("Activities");
        activityOverviewButton.addActionListener(e -> {
            System.out.println("Activities clicked");
            ActivityOverviewFrame activityOverviewFrame = new ActivityOverviewFrame(app);
            manageActivityFrame.setVisible(false);
            manageActivityFrame.dispose();
        });
        activityOverviewButton.setBounds(0, 0, 150, 25);
        dropdownContainer.add(activityOverviewButton);

        JButton projectOverviewButton = new JButton("Projects");
        projectOverviewButton.addActionListener(e -> {
            System.out.println("Projects clicked");
            ProjectOverviewFrame manageProjectsFrame = new ProjectOverviewFrame(app);
            manageActivityFrame.setVisible(false);
            manageActivityFrame.dispose();
        });
        projectOverviewButton.setBounds(0, 50, 150, 25);
        dropdownContainer.add(projectOverviewButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            System.out.println("Logged out");
            LoginFrame loginFrame = new LoginFrame(app);
            manageActivityFrame.setVisible(false);
            manageActivityFrame.dispose();
        });
        logoutButton.setBounds(0, 100, 150, 25);
        dropdownContainer.add(logoutButton);

        // Add MouseListener to the label
        dropdownLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dropdownContainer.setVisible(true); // Show the container on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Check if the mouse is still inside the dropdownContainer
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mouseLocation, dropdownContainer);
                if (!dropdownContainer.contains(mouseLocation)) {
                    dropdownContainer.setVisible(false); // Hide the container if the mouse is outside
                }
            }
        });

        // Add MouseListener to the container
        dropdownContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                dropdownContainer.setVisible(true); // Keep the container visible on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Check if the mouse is still inside the dropdownLabel
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mouseLocation, dropdownLabel);
                if (!dropdownLabel.contains(mouseLocation)) {
                    dropdownContainer.setVisible(false); // Hide the container if the mouse is outside
                }
            }
        });

        JLabel title = new JLabel("Project " + project.getProjectNumber() + " - " + activity.getName());
        title.setForeground(Color.BLACK);
        title.setFont(title.getFont().deriveFont(40f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);
        manageActivityFrame.add(header, BorderLayout.NORTH);

        JLabel addLabel = new JLabel();
        originalIcon = new ImageIcon(getClass().getResource("/dtu/icons/plusSign.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        addLabel.setIcon(scaledIcon);
        header.add(addLabel, BorderLayout.EAST);

        JPanel addDropdownContainer = new JPanel();
        addDropdownContainer.setLayout(new GridLayout(5, 1)); // 3 rows, 1 column
        addDropdownContainer.setBounds(850, 80, 150, 125);
        addDropdownContainer.setBackground(Color.LIGHT_GRAY);
        addDropdownContainer.setVisible(false); // Initially hidden
        manageActivityFrame.add(addDropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton updateStatus = new JButton("Update status");
        updateStatus.addActionListener(e -> {
            System.out.println("Update Status clicked");
            SetStatusFrame setStatusFrame = new SetStatusFrame(app, activity);
            // Add a WindowListener to detect when CreateProjectFrame is closed
            setStatusFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app, activity); // Refresh the ProjectOverviewFrame
                }
            });
        });
        addDropdownContainer.add(updateStatus);

        JButton editActivity = new JButton("Edit activity");
        editActivity.addActionListener(e -> {
            System.out.println("Edit activity clicked");
            EditActivityFrame editActivityFrame = new EditActivityFrame(app, activity);
            // Add a WindowListener to detect when CreateProjectFrame is closed
            editActivityFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app, activity); // Refresh the ProjectOverviewFrame
                }
            });
        });
        addDropdownContainer.add(editActivity);

        JButton registerTime = new JButton("Register time");
        registerTime.addActionListener(e -> {
            System.out.println("Register Time clicked");
            RegisterTimeFrame registerTimeFrame = new RegisterTimeFrame(app, activity);
            // Add a WindowListener to detect when CreateProjectFrame is closed
            registerTimeFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app, activity); // Refresh the ProjectOverviewFrame
                }
            });
        });
        addDropdownContainer.add(registerTime);

        JButton addDeveloper = new JButton("Add developer");
        addDeveloper.addActionListener(e -> {
            System.out.println("Add Developer clicked");
            AddDeveloperFrame addDeveloperFrame = new AddDeveloperFrame(app, activity);
            // Add a WindowListener to detect when CreateProjectFrame is closed
            addDeveloperFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app, activity); // Refresh the ProjectOverviewFrame
                }
            });
        });
        addDropdownContainer.add(addDeveloper);

        JButton removeDeveloper = new JButton("Remove developer");
        removeDeveloper.addActionListener(e -> {
            System.out.println("Remove Developer clicked");
            RemoveDeveloperFrame removeDeveloperFrame = new RemoveDeveloperFrame(app, activity);
            // Add a WindowListener to detect when CreateProjectFrame is closed
            removeDeveloperFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app, activity); // Refresh the ProjectOverviewFrame
                }
            });
        });
        addDropdownContainer.add(removeDeveloper);

        // Add MouseListener to the label
        addLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addDropdownContainer.setVisible(true); // Show the container on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Check if the mouse is still inside the addDropdownContainer
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mouseLocation, addDropdownContainer);
                if (!addDropdownContainer.contains(mouseLocation)) {
                    addDropdownContainer.setVisible(false); // Hide the container if the mouse is outside
                }
            }
        });

        // Add MouseListener to the container
        addDropdownContainer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addDropdownContainer.setVisible(true); // Keep the container visible on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Check if the mouse is still inside the addLabel
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(mouseLocation, addLabel);
                if (!addLabel.contains(mouseLocation)) {
                    addDropdownContainer.setVisible(false); // Hide the container if the mouse is outside
                }
            }
        });

        // Main panel
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        manageActivityFrame.add(main, BorderLayout.CENTER);
        main.setBackground(Color.WHITE);

        // Status panel
        JPanel statusPanel = new JPanel(new GridLayout(3, 1));
        statusPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20), // Outer padding
            BorderFactory.createLineBorder(Color.GRAY, 1)    // Optional: Add a border for better visibility
        ));

        JLabel statusTitle = new JLabel("Status");
        statusTitle.setFont(statusTitle.getFont().deriveFont(40f));
        statusTitle.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(statusTitle);

        JLabel dateLabel = new JLabel(activity.getWeekPlan()[0] + "/" + activity.getYearPlan()[0] + " - " +
            activity.getWeekPlan()[1] + "/" + activity.getYearPlan()[1]);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setFont(dateLabel.getFont().deriveFont(20f));
        statusPanel.add(dateLabel);

        JLabel statusLabel = new JLabel(activity.getStatus().toString() + " - " + activity.getTimeSpent() + "/" + activity.getTimeBudget());
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(statusLabel.getFont().deriveFont(20f));
        statusPanel.add(statusLabel);
        main.add(statusPanel, BorderLayout.NORTH);

        // Developer panel
        JPanel developerPanel = new JPanel();
        developerPanel.setLayout(new BorderLayout()); // Use BorderLayout for better control
        developerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 300, 20), // Outer padding
            BorderFactory.createLineBorder(Color.GRAY, 1)    // Optional: Add a border for better visibility
        ));

        JLabel developerTitle = new JLabel("Developers");
        developerTitle.setHorizontalAlignment(SwingConstants.CENTER);
        developerTitle.setFont(developerTitle.getFont().deriveFont(40f));
        developerPanel.add(developerTitle, BorderLayout.NORTH); // Add title to the top

        JPanel developerListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Dynamic rows
        for (Developer developer : activity.getAssignedDevelopers()) {
            JLabel developerLabel = new JLabel(developer.getInitials() + "  ");
            developerLabel.setFont(developerLabel.getFont().deriveFont(20f));
            developerListPanel.add(developerLabel);
        }
        developerPanel.add(developerListPanel, BorderLayout.CENTER); // Add list to the center

        // Add the developer panel to the main panel
        main.add(developerPanel, BorderLayout.CENTER);

        
        // Finalize frame
        manageActivityFrame.setLocationRelativeTo(null); // Center the frame on the screen
        manageActivityFrame.setResizable(false);
        manageActivityFrame.setVisible(true);
    }

    private void refreshFrame(App app, Activity activity) {
        manageActivityFrame.setVisible(false);
        manageActivityFrame.dispose();
        ManageActivityFrame manageActivityFrame = new ManageActivityFrame(app, activity);
    }
}

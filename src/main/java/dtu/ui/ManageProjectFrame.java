package dtu.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import dtu.app.*;
import dtu.domain.*;
import dtu.ui.frameInFrames.*;

public class ManageProjectFrame {
    private JFrame manageProjectFrame;

    private JButton activityButton(Activity activity, App app) {
        // Create a button for the activity
        JButton activityButton = new JButton();
        activityButton.setPreferredSize(new Dimension(300, 60));
        activityButton.setBorderPainted(false);
        activityButton.setOpaque(true);
        activityButton.setBackground(Color.LIGHT_GRAY);
    
        // Create a panel to hold the text and status label
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Make the panel transparent
    
        // Add the activity name as a label
        JLabel nameLabel = new JLabel(activity.getName() + ": " +
            activity.getWeekPlan()[0] + "/" + activity.getYearPlan()[0] + " - " +
            activity.getWeekPlan()[1] + "/" + activity.getYearPlan()[1]);
        nameLabel.setFont(nameLabel.getFont().deriveFont(16f)); // Set font size
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        contentPanel.add(nameLabel, BorderLayout.NORTH);
    
        // Add the status label
        JLabel statusLabel = new JLabel(activity.getStatus());
        statusLabel.setFont(statusLabel.getFont().deriveFont(14f)); // Set font size
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        contentPanel.add(statusLabel, BorderLayout.SOUTH);
    
        // Add the content panel to the button
        activityButton.setLayout(new BorderLayout());
        activityButton.add(contentPanel, BorderLayout.CENTER);
    
        // Add an action listener
        activityButton.addActionListener(e -> {
            System.out.println("Activity " + activity.getName() + " clicked");
            ManageActivityFrame manageActivityFrame = new ManageActivityFrame(app, activity);
            manageProjectFrame.setVisible(false);
            manageProjectFrame.dispose();
        });
    
        return activityButton;
    }

    public ManageProjectFrame(App app, Project project) {
        // Create the login frame
        manageProjectFrame = new JFrame("Login");
        manageProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manageProjectFrame.setSize(1000, 700);

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
        manageProjectFrame.add(dropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton activityOverviewButton = new JButton("Activities");
        activityOverviewButton.addActionListener(e -> {
            System.out.println("Activities clicked");
            ActivityOverviewFrame activityOverviewFrame = new ActivityOverviewFrame(app);
            manageProjectFrame.setVisible(false);
            manageProjectFrame.dispose();
        });
        activityOverviewButton.setBounds(0, 0, 150, 25);
        dropdownContainer.add(activityOverviewButton);

        JButton projectOverviewButton = new JButton("Projects");
        projectOverviewButton.addActionListener(e -> {
            System.out.println("Projects clicked");
            ProjectOverviewFrame manageProjectsFrame = new ProjectOverviewFrame(app);
            manageProjectFrame.setVisible(false);
            manageProjectFrame.dispose();
        });
        projectOverviewButton.setBounds(0, 50, 150, 25);
        dropdownContainer.add(projectOverviewButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            System.out.println("Logged out");
            LoginFrame loginFrame = new LoginFrame(app);
            manageProjectFrame.setVisible(false);
            manageProjectFrame.dispose();
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

        JLabel title = new JLabel("Project " + project.getProjectNumber());
        title.setForeground(Color.BLACK);
        title.setFont(title.getFont().deriveFont(40f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title);
        manageProjectFrame.add(header, BorderLayout.NORTH);

        JLabel addLabel = new JLabel();
        originalIcon = new ImageIcon(getClass().getResource("/dtu/icons/plusSign.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        addLabel.setIcon(scaledIcon);
        if (app.getLoggedInDeveloper().getInitials().equals(project.getProjectLeader()) || project.getProjectLeader() == null) {
            addLabel.setVisible(true);
        } else {
            addLabel.setVisible(false);
        }
        header.add(addLabel, BorderLayout.EAST);

        JPanel addDropdownContainer = new JPanel();
        addDropdownContainer.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column
        addDropdownContainer.setBounds(800, 80, 200, 75);
        addDropdownContainer.setBackground(Color.LIGHT_GRAY);
        addDropdownContainer.setVisible(false); // Initially hidden
        manageProjectFrame.add(addDropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton newActivity = new JButton("New activity");
        newActivity.addActionListener(e -> {
            System.out.println("New activity clicked");
            CreateActivityFrame createActivityFrame = new CreateActivityFrame(app, project);

            // Add a WindowListener to detect when CreateProjectFrame is closed
            createActivityFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app, project); // Refresh the ProjectOverviewFrame
                }
            });
        });
        newActivity.setBounds(0, 0, 200, 25);
        addDropdownContainer.add(newActivity);

        JButton assignProjectleader = new JButton("Assign projectleader");
        assignProjectleader.addActionListener(e -> {
            System.out.println("Assign Projectleader clicked");
            AssignProjectleaderFrame assignProjectleaderFrame = new AssignProjectleaderFrame(app, project);

            // Add a WindowListener to detect when CreateProjectFrame is closed
            assignProjectleaderFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app, project); // Refresh the ProjectOverviewFrame
                }
            });
        });
        assignProjectleader.setBounds(0, 50, 200, 25);
        addDropdownContainer.add(assignProjectleader);

        JButton generateReport = new JButton("Generate report");
        generateReport.addActionListener(e -> {
            System.out.println("Generate report clicked");
            GetReportFrame getReportFrame = new GetReportFrame(app, project);
        });
        generateReport.setBounds(0, 100, 200, 25);
        addDropdownContainer.add(generateReport);

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
        manageProjectFrame.add(main, BorderLayout.CENTER);

        JLabel projectLeaderLabel = new JLabel("Project leader: " + project.getProjectLeader());
        projectLeaderLabel.setFont(projectLeaderLabel.getFont().deriveFont(20f)); // Set font size
        projectLeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        projectLeaderLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding
        main.add(projectLeaderLabel, BorderLayout.NORTH);

        // Create a container for activities
        JPanel activityContainer = new JPanel();
        activityContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Horizontal alignment with spacing
        activityContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Activity activity : project.getActivities()) {
            JButton activityButton = activityButton(activity, app);
            activityContainer.add(activityButton);
        }
        main.add(activityContainer, BorderLayout.CENTER);

        // Finalize frame
        manageProjectFrame.setLocationRelativeTo(null); // Center the frame on the screen
        manageProjectFrame.setResizable(false);
        manageProjectFrame.setVisible(true);
    }

    private void refreshFrame(App app, Project project) {
        manageProjectFrame.setVisible(false);
        manageProjectFrame.dispose();
        new ManageProjectFrame(app, project);
    }
}

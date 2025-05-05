package dtu.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

import dtu.app.*;
import dtu.domain.*;
import dtu.ui.*;

public class ManageProjectsFrame {
    private JFrame manageProjectsFrame = new JFrame("Manage Projects");

    private JPanel createProjectPanel(Project project){
        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BorderLayout());
        projectPanel.setPreferredSize(new Dimension(1000, 100));
        projectPanel.setBackground(Color.LIGHT_GRAY);
        
        JLabel projectLabel = new JLabel(project.getProjectNumber());
        projectLabel.setFont(projectLabel.getFont().deriveFont(20f));
        projectLabel.setHorizontalAlignment(SwingConstants.CENTER);
        projectPanel.add(projectLabel, BorderLayout.CENTER);

        return projectPanel;
    }

    public ManageProjectsFrame(App app) {
        // Set up the frame
        manageProjectsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manageProjectsFrame.setSize(1000, 700);
        manageProjectsFrame.setLayout(new BorderLayout());
        manageProjectsFrame.setLocationRelativeTo(null); // Center the frame on the screen
        manageProjectsFrame.setResizable(false);

        // (1) Create your header panel however you like:
        JPanel header = new JPanel();
        header.setBackground(Color.GRAY);
        header.setPreferredSize(new Dimension(1000, 80));   // height = 80px
        header.setLayout(new GridLayout(1, 3)); // 1 row, 3 columns

        
        JLabel dropdownLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/dtu/icons/Account.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dropdownLabel.setIcon(scaledIcon);
        header.add(dropdownLabel);

        JPanel dropdownContainer = new JPanel();
        dropdownContainer.setLayout(new BorderLayout());
        // dropdownContainer.setPreferredSize(new Dimension(300, 200));
        dropdownContainer.setBounds(0, 80, 150, 50);
        dropdownContainer.setBackground(Color.LIGHT_GRAY);
        dropdownContainer.setVisible(false); // Initially hidden
        manageProjectsFrame.add(dropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton manageProjectsButton = new JButton("Activities");
        manageProjectsButton.addActionListener(e -> {
            System.out.println("Activities clicked");
            ActivityOverviewFrame activityOverviewFrame = new ActivityOverviewFrame(app);
            manageProjectsFrame.setVisible(false);
            manageProjectsFrame.dispose();
            // Add your action here
        });
        manageProjectsButton.setBounds(0, 0, 150, 25);
        dropdownContainer.add(manageProjectsButton, BorderLayout.NORTH);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            System.out.println("Logged out");
            manageProjectsFrame.setVisible(false);
            manageProjectsFrame.dispose();
            LoginFrame loginFrame = new LoginFrame(app);
        });
        logoutButton.setBounds(0, 50, 150, 25);
        dropdownContainer.add(logoutButton, BorderLayout.SOUTH);

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
        
        JLabel title = new JLabel("Projects");
        title.setForeground(Color.BLACK);
        title.setFont(title.getFont().deriveFont(26f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title);

        JLabel temp = new JLabel("");
        header.add(temp);

        // (2) Add it to the top (NORTH) of the frame:
        manageProjectsFrame.add(header, BorderLayout.NORTH);


        // (3) Add your main content in CENTER:
        JPanel main = new JPanel();
        manageProjectsFrame.add(main, BorderLayout.CENTER);

        JPanel searchContainer = new JPanel();
        searchContainer.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel searchIcon = new JLabel();
        ImageIcon searchOriginalIcon = new ImageIcon(getClass().getResource("/dtu/icons/Search.png"));
        Image searchScaledImage = searchOriginalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon searchScaledIcon = new ImageIcon(searchScaledImage);
        searchIcon.setIcon(searchScaledIcon);
        searchContainer.add(searchIcon);
        
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (searchField.getText().equals("Search for activity")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for activity");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        searchContainer.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            System.out.println("Search for: " + searchText);
            // Add your action here
        });
        searchContainer.add(searchButton);
        main.add(searchContainer, BorderLayout.NORTH);
        
        manageProjectsFrame.setVisible(true);
    }
}

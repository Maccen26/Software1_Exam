package dtu.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;
import dtu.ui.*;
import dtu.ui.frameInFrames.*;

public class ProjectOverviewFrame {
    private JFrame projectOverviewFrame = new JFrame("Manage Projects");

    ArrayList<Color> colors = new ArrayList<Color>();
    {
        colors.add(Color.PINK);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        colors.add(Color.ORANGE);
    }

    private JPanel createProjectPanel(Project project, App app){
        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BorderLayout());
        projectPanel.setPreferredSize(new Dimension(1000, 100));
        
        JButton projectButton = new JButton(project.getProjectNumber());
        projectButton.setPreferredSize(new Dimension(200, 200));
        projectButton.setFont(projectButton.getFont().deriveFont(40f));
        projectButton.setHorizontalAlignment(SwingConstants.CENTER);
        String number = project.getProjectNumber();
        int nr = Integer.parseInt(number.substring(4, number.length()));
        projectButton.setBorderPainted(false);
        projectButton.setOpaque(true);
        projectButton.setBackground(colors.get(nr % colors.size()));

        projectButton.addActionListener(e -> {
            System.out.println("Project " + project.getProjectNumber() + " clicked");
            ManageProjectFrame manageProjectFrame = new ManageProjectFrame(app, project);
            projectOverviewFrame.setVisible(false);
            projectOverviewFrame.dispose();
        });

        projectPanel.add(projectButton, BorderLayout.CENTER);
        return projectPanel;
    }

    public ProjectOverviewFrame(App app) {
        // Set up the frame
        projectOverviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        projectOverviewFrame.setSize(1000, 700);
        projectOverviewFrame.setLayout(new BorderLayout());
        projectOverviewFrame.setLocationRelativeTo(null); // Center the frame on the screen
        projectOverviewFrame.setResizable(false);

        // (1) Create your header panel however you like:
        JPanel header = new JPanel();
        header.setBackground(Color.GRAY);
        header.setPreferredSize(new Dimension(1000, 80));   // height = 80px
        header.setLayout(new BorderLayout()); // 1 row, 3 columns

        
        JLabel dropdownLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/dtu/icons/Account.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        dropdownLabel.setIcon(scaledIcon);
        header.add(dropdownLabel, BorderLayout.WEST);

        JPanel dropdownContainer = new JPanel();
        dropdownContainer.setLayout(new BorderLayout());
        // dropdownContainer.setPreferredSize(new Dimension(300, 200));
        dropdownContainer.setBounds(0, 80, 150, 50);
        dropdownContainer.setBackground(Color.LIGHT_GRAY);
        dropdownContainer.setVisible(false); // Initially hidden
        projectOverviewFrame.add(dropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton manageProjectsButton = new JButton("Activities");
        manageProjectsButton.addActionListener(e -> {
            System.out.println("Activities clicked");
            ActivityOverviewFrame activityOverviewFrame = new ActivityOverviewFrame(app);
            projectOverviewFrame.setVisible(false);
            projectOverviewFrame.dispose();
        });
        manageProjectsButton.setBounds(0, 0, 150, 25);
        dropdownContainer.add(manageProjectsButton, BorderLayout.NORTH);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            System.out.println("Logged out");
            LoginFrame loginFrame = new LoginFrame(app);
            projectOverviewFrame.setVisible(false);
            projectOverviewFrame.dispose();
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
        title.setFont(title.getFont().deriveFont(40f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);

        JLabel addLabel = new JLabel();
        originalIcon = new ImageIcon(getClass().getResource("/dtu/icons/plusSign.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        addLabel.setIcon(scaledIcon);
        header.add(addLabel, BorderLayout.EAST);

        JPanel addDropdownContainer = new JPanel();
        addDropdownContainer.setLayout(new GridLayout(2, 1)); // 3 rows, 1 column
        addDropdownContainer.setBounds(850, 80, 150, 50);
        addDropdownContainer.setBackground(Color.LIGHT_GRAY);
        addDropdownContainer.setVisible(false); // Initially hidden
        projectOverviewFrame.add(addDropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton createProject = new JButton("Create project");
        createProject.addActionListener(e -> {
            System.out.println("Create project clicked");
            CreateProjectFrame createProjectFrame = new CreateProjectFrame(app);
            
            // Add a WindowListener to detect when CreateProjectFrame is closed
            createProjectFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshFrame(app); // Refresh the ProjectOverviewFrame
                }
            });
        });
        createProject.setBounds(0, 0, 150, 25);
        addDropdownContainer.add(createProject);

        JButton createActivity = new JButton("Create activity");
        createActivity.addActionListener(e -> {
            System.out.println("Create activity clicked");
        });
        createActivity.setBounds(0, 50, 150, 25);
        addDropdownContainer.add(createActivity);

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

        // (2) Add it to the top (NORTH) of the frame:
        projectOverviewFrame.add(header, BorderLayout.NORTH);


        // (3) Add your main content in CENTER:
        JPanel main = new JPanel();
        projectOverviewFrame.add(main, BorderLayout.CENTER);

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
        
        // Create a container for projects
        double projectCount = app.getProjects().size();
        int rows = (int) Math.ceil(projectCount / 3.0);
        JPanel projectContainer = new JPanel(new GridLayout(rows, 4, 20, 20));
        projectContainer.setPreferredSize(new Dimension(1000, rows * 100));
        projectContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        int i = 0;
        for (Project project : app.getProjects()) {
            if (i == 15){
                break;
            }
            i++;
            // if (project.getStatus().equals("Finished")) {
            //     continue;
            // }
            JPanel projectPanel = createProjectPanel(project, app);
            projectContainer.add(projectPanel);
        }
        main.add(projectContainer, BorderLayout.CENTER);
        
        projectOverviewFrame.setVisible(true);
    }
    private void refreshFrame(App app) {
        projectOverviewFrame.setVisible(false);
        projectOverviewFrame.dispose();
        new ProjectOverviewFrame(app);
    }
}

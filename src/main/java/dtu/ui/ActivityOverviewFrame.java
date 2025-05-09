package dtu.ui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

public class ActivityOverviewFrame extends JFrame {
    private JFrame activityOverviewFrame = new JFrame("Activity Overview");

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

    private JButton activityButton(Activity activity, int curWeek, int curYear, int index, App app) {
        int curWeekOfYear = curWeek + (curYear - 2020) * 52;
        int startWeek = activity.getWeekPlan()[0] + (activity.getYearPlan()[0] - 2020)* 52;
        int endWeek = activity.getWeekPlan()[1] + (activity.getYearPlan()[1] - 2020)* 52;

        if (curWeekOfYear + 3 < startWeek || curWeekOfYear > endWeek) {
            // Check if the activity is currently active
            return null;
        }
    
        int startX = Math.max((startWeek - curWeekOfYear) * 225 + 50, 0); // Calculate X position based on start week
        int width = Math.min(Math.min(endWeek - startWeek + 1, endWeek - curWeekOfYear + 1) * 225, 1000 - startX); // Calculate width based on duration
        if (startX == 0){
            width += 50; // Add the missing 50 px
        }
        
        JButton activityButton = new JButton(activity.getName());
        activityButton.setBounds(startX, 50 * index + 10, width, 40); // Position the button
        activityButton.setFont(activityButton.getFont().deriveFont(14f));
        activityButton.setBorderPainted(false);
        activityButton.setOpaque(true);

        String name = activity.getProject().getProjectNumber();
        int nr = Integer.parseInt(name.substring(name.length() - 1));
        activityButton.setBackground(colors.get(nr % colors.size()));

        activityButton.addActionListener(e -> {
            System.out.println("Activity " + activity.getName() + " clicked");
            ManageActivityFrame manageActivityFrame = new ManageActivityFrame(app, activity);
            activityOverviewFrame.setVisible(false);
            activityOverviewFrame.dispose();
        });

        return activityButton;
    }

    public ActivityOverviewFrame(App app) {
        activityOverviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        header.add(dropdownLabel, BorderLayout.WEST); // Add the label to the right side of the header

        JPanel dropdownContainer = new JPanel();
        dropdownContainer.setLayout(new BorderLayout());
        dropdownContainer.setBounds(0, 80, 150, 50);
        dropdownContainer.setBackground(Color.LIGHT_GRAY);
        dropdownContainer.setVisible(false); // Initially hidden
        activityOverviewFrame.add(dropdownContainer); // Add the container to the frame

        // Create the dropdown items
        JButton projectOverviewBUtton = new JButton("Projects");
        projectOverviewBUtton.addActionListener(e -> {
            System.out.println("Manage Projects clicked");
            ProjectOverviewFrame projectOverviewFrame = new ProjectOverviewFrame(app);
            activityOverviewFrame.setVisible(false);
            activityOverviewFrame.dispose();
        });
        projectOverviewBUtton.setBounds(0, 0, 150, 25);
        dropdownContainer.add(projectOverviewBUtton, BorderLayout.NORTH);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            System.out.println("Logged out");
            LoginFrame loginFrame = new LoginFrame(app);
            activityOverviewFrame.setVisible(false);
            activityOverviewFrame.dispose();
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
        
        JLabel title = new JLabel("Activities");
        title.setForeground(Color.BLACK);
        title.setFont(title.getFont().deriveFont(40f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(title, BorderLayout.CENTER);

        // (2) Add it to the top (NORTH) of the frame:
        activityOverviewFrame.add(header, BorderLayout.NORTH);

        // (3) Add your main content in CENTER:
        JPanel main = new JPanel();
        activityOverviewFrame.add(main, BorderLayout.CENTER);

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

        class LinePanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Call the superclass method to ensure proper rendering
                Graphics2D g2d = (Graphics2D) g; // Cast to Graphics2D for more control
                g2d.setStroke(new BasicStroke(2)); // Set the line thickness
                g2d.setColor(Color.BLACK); // Set the line color
                int height = 10;
                int dif = 10;
                g2d.drawLine(0, height, 1000, height); 
                g2d.drawLine(50, height - dif, 50, height + dif); 
                g2d.drawLine(275, height - dif, 275, height + dif); 
                g2d.drawLine(500, height - dif, 500, height + dif); 
                g2d.drawLine(725, height - dif, 725, height + dif);
                g2d.drawLine(950, height - dif, 950, height + dif);  
            }
        }
        // Add the custom panel to the frame
        JPanel linePanel = new LinePanel();
        int curWeek = 20; // Example current week
        int curYear = 2025; // Example current year
        JPanel weekPanel = new JPanel(new GridLayout(1, 4));
        weekPanel.setPreferredSize(new Dimension(900, 20));
        weekPanel.setOpaque(true);
        for (int i = curWeek; i < curWeek + 4; i++) {
            JLabel weekLabel = new JLabel("Week " + i);
            weekLabel.setHorizontalAlignment(SwingConstants.CENTER);
            weekLabel.setFont(weekLabel.getFont().deriveFont(20f));
            weekLabel.setOpaque(true);
            weekPanel.add(weekLabel);
        }
        main.add(weekPanel);
        linePanel.setPreferredSize(new Dimension(1000, 20)); // Set the preferred size of the panel
        main.add(linePanel, BorderLayout.CENTER); // Add it to the frame


        ArrayList<Project> projects = app.getProjects();
        ArrayList<Activity> activities = new ArrayList<Activity>();
        for (Project project : projects) {
            for (Activity activity : project.getActivities()) {
                activities.add(activity);
            }
        }
        // sort activities by end week
        activities.sort((a, b) -> {
            int aEndWeek = a.getWeekPlan()[1];
            int bEndWeek = b.getWeekPlan()[1];
            return Integer.compare(aEndWeek, bEndWeek);
        });

        
        // Create a timeline panel
        JPanel timelinePanel = new JPanel();
        timelinePanel.setLayout(null); // Use absolute positioning for precise placement
        timelinePanel.setPreferredSize(new Dimension(1000, 400)); // Adjust height as needed

        // Add activity buttons to the timeline panel
        int i = 0;
        for (Activity activity : activities) {
            JButton btn = activityButton(activity, curWeek, curYear, i, app);
            if (btn != null) {
                timelinePanel.add(btn); // Add buttons to the timeline panel
            } else {
                System.out.println("Activity " + activity.getName() + " is not active");
            }
            i++;
        }

        // Add the timeline panel to the main panel
        main.add(timelinePanel, BorderLayout.CENTER);

        // (4) Size & show
        activityOverviewFrame.setSize(1000, 700);
        activityOverviewFrame.setLocationRelativeTo(null);
        activityOverviewFrame.setVisible(true);
    }
}
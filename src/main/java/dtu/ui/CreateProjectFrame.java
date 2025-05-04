package dtu.ui;

import javax.swing.*;

public class CreateProjectFrame extends Ui{
    private JFrame createProjectFrame;

    public CreateProjectFrame() { // Lukas
        createProjectFrame = new JFrame("Create Project");
        createProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createProjectFrame.setSize(300, 300);
        createProjectFrame.setLayout(null);
        createProjectFrame.setLocationRelativeTo(null); // Center the frame on the screen
        createProjectFrame.setResizable(false);

        JButton createButton = new JButton("Create Project");
        createButton.setBounds(100, 100, 100, 30);
        createButton.addActionListener(e -> {
            app.createProject();
            System.out.println("Created project");
        });
        createProjectFrame.add(createButton);
    }

    public void show() { // Lukas
        createProjectFrame.setVisible(true);
    }
    public void hide() { // Lukas
        createProjectFrame.setVisible(false);
    }
}

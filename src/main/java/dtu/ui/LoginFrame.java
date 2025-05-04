package dtu.ui;

import javax.swing.*;

public class LoginFrame extends Ui{
	private JFrame loginFrame;
    private String initials;

	public LoginFrame(){
		loginFrame = new JFrame("Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(300, 300);
        JTextField textField = new JTextField("Enter your initials:");
        loginFrame.add(textField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            initials = textField.getText();
            System.out.println("Logged in with initials: " + initials);
            if (app.hasDeveloper(initials)) {
                System.out.println("Developer exists");
                loginFrame.setVisible(false);
            } else {
                System.out.println("Developer does not exist");
                textField.setText("Developer does not exist");
                textField.setForeground(java.awt.Color.RED);
            }
        });
        loginFrame.add(loginButton);
        loginButton.setBounds(100, 100, 100, 30);
        textField.setBounds(50, 50, 200, 30);
        loginFrame.setLayout(null);
        loginFrame.setLocationRelativeTo(null); // Center the frame on the screen
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
	}
	
	public void show(){
		loginFrame.setVisible(true);	
	}

    public void hide(){
        loginFrame.setVisible(false);
    }
}
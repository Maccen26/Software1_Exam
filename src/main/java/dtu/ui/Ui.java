package dtu.ui;

import javax.swing.*;

import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

public class Ui {
    protected App app = new App(new ArrayList<Project>(), new ArrayList<Developer>());
    
    public static void main(String[] args) {
        // Create a new instance of the LoginFrame
        LoginFrame loginFrame = new LoginFrame();
        // Show the login frame
        loginFrame.show();
    }

}

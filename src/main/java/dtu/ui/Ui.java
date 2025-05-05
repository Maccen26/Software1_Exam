package dtu.ui;

import javax.swing.*;

import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

public class Ui {
    private static App app = new App();
    
    public static void main(String[] args) { // Lukas
        LoginFrame loginFrame = new LoginFrame(app);
    }
}

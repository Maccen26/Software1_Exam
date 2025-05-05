package dtu.ui;

import javax.swing.*;

import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

public class Ui {
<<<<<<< HEAD
    private static App app = new App(new ArrayList<Project>(), new ArrayList<Developer>());

    
=======
    protected App app = new App();
    protected static LoginFrame loginFrame = new LoginFrame();
    protected static CreateProjectFrame createProjectFrame = new CreateProjectFrame();
    protected static EditActivityFrame editActivityFrame = new EditActivityFrame();
    protected static GetReportFrame getReportFrame = new GetReportFrame();
>>>>>>> 0cfe1e5ad15c0123a2fa57f42336905199004cac
    
    public static void main(String[] args) { // Lukas
        LoginFrame loginFrame = new LoginFrame(app);
    }
}

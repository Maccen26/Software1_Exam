package dtu.ui;

import javax.swing.*;

import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

public class Ui {
    protected App app = new App(new ArrayList<Project>(), new ArrayList<Developer>());
    protected static LoginFrame loginFrame = new LoginFrame();
    protected static CreateProjectFrame createProjectFrame = new CreateProjectFrame();
    protected static EditActivityFrame editActivityFrame = new EditActivityFrame();
    protected static GetReportFrame getReportFrame = new GetReportFrame();
    
    public static void main(String[] args) { // Lukas
        loginFrame.show();
        editActivityFrame.show();
        createProjectFrame.show();
        getReportFrame.show();
    }
}

package dtu.ui;

import javax.swing.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dtu.app.*;
import dtu.domain.*;

public class Ui {
    private static App app = new App();
    
    public static void main(String[] args) { // Lukas
        app.createProject();
        app.createProject();
        app.createProject();

        ArrayList<Project> projects = app.getProjects();
        Developer dev;
        try {
            dev = app.getDeveloper("huba");
        } catch (Exception e) {
            dev = new Developer("huba");
            System.out.println(e.getMessage());
        }

        String[] devs = {"mhh", "lkm", "jkv", "tf", "fbj"};
        ArrayList<Developer> devsList = new ArrayList<Developer>();
        for (String devsName : devs) {
            try {
                app.addDeveloper(new Developer(devsName));
                devsList.add(app.getDeveloper(devsName));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        try {
            projects.get(0).addActivity(dev, "Activity 1", new int[] {20, 21}, new int[] {2025, 2025});
            projects.get(0).getActivity("Activity 1").addDeveloper(devsList.get(0));
            projects.get(0).getActivity("Activity 1").addDeveloper(devsList.get(1));
            projects.get(0).addActivity(dev, "Activity 2", new int[] {19, 24}, new int[] {2025, 2025});
            projects.get(0).getActivity("Activity 2").addDeveloper(devsList.get(3));
            projects.get(0).getActivity("Activity 2").addDeveloper(devsList.get(4));
            projects.get(0).getActivity("Activity 2").addDeveloper(devsList.get(2));
            projects.get(0).getActivity("Activity 2").addDeveloper(dev);
            projects.get(0).addActivity(dev, "Activity 3", new int[] {22, 23}, new int[] {2025, 2025});
            projects.get(0).getActivity("Activity 3").addDeveloper(devsList.get(0));
    
            projects.get(1).addActivity(dev, "Activity 1", new int[] {18, 20}, new int[] {2025, 2025});
            projects.get(1).getActivity("Activity 1").addDeveloper(devsList.get(4));
            projects.get(1).getActivity("Activity 1").addDeveloper(devsList.get(3));
            projects.get(1).addActivity(dev, "Activity 2", new int[] {20, 22}, new int[] {2025, 2025});
            projects.get(1).getActivity("Activity 2").addDeveloper(devsList.get(2));
    
            projects.get(2).addActivity(dev, "Activity 1", new int[] {20, 21}, new int[] {2025, 2025});
            projects.get(2).getActivity("Activity 1").addDeveloper(devsList.get(0));
            projects.get(2).getActivity("Activity 1").addDeveloper(devsList.get(1));
            projects.get(2).getActivity("Activity 1").addDeveloper(devsList.get(2));
            projects.get(2).getActivity("Activity 1").addDeveloper(devsList.get(3));
            projects.get(2).getActivity("Activity 1").addDeveloper(devsList.get(4));
            projects.get(2).getActivity("Activity 1").addDeveloper(dev);
        } catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }

        LoginFrame loginFrame = new LoginFrame(app);
    }
}

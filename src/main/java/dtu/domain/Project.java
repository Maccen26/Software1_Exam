package dtu.domain;

import java.util.ArrayList;

public class Project {
    private String projectNumber;
    private String projectLeader;
    private ArrayList<Activity> activities;

    public Project(String projectNumber) { // Lukas
        // Initialise the project number and project leader
        this.projectNumber = projectNumber;
        this.projectLeader = null;
        this.activities = new ArrayList<Activity>();
    }

    public String getProjectNumber() { // Lukas
        // Get the project number
        return projectNumber;
    }
}

package dtu.domain;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

public class Project {
    private String projectNumber;
    private String projectLeader;
    private ArrayList<Activity> activities;

    public Project(String projectNumber) {
        // Initialise the project number and project leader
        this.projectNumber = projectNumber;
        this.projectLeader = null;
        this.activities = new ArrayList<Activity>();
    }

    public String getProjectNumber() {
        // Get the project number
        return projectNumber;
    }
}

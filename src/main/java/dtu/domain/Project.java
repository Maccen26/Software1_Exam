package dtu.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Project {
    private String projectNumber;
    private String projectLeader;
    private ArrayList<Activity> activities;

    public Project(String projectNumber, String projectLeader) {
        // Initialise the project number and project leader
        this.projectNumber = projectNumber;
        this.projectLeader = projectLeader;
        this.activities = new ArrayList<Activity>();
    }
}

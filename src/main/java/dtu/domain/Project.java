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

    public Activity getActivity(String activityName) { // Lukas
        // Get an activity by its name
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }

    public String getReport() { // Lukas
        if (this.activities.isEmpty()) {
            return projectNumber + ", no activities";
        }
        // Get the report for the project
        String report = "";
        for (Activity activity : activities) {
            String status = activity.getStatus();
            report += activity.getName() + status + "\n";

        }
        return report;
    }
}

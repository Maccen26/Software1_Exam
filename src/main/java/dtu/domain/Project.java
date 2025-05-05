package dtu.domain;

import java.util.ArrayList;
import java.util.Objects;

import io.cucumber.java.hu.De;

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

    public void assignProjectLeader(Developer requester, Developer newLeader)
            throws ErrorMessage {
        // If there already is a leader, only they can change it:
        if (this.projectLeader != null
            && !requester.getInitials().equals(this.projectLeader)) {
            throw new ErrorMessage("Only the current project leader can reassign the project leader");
        }

        // At this point either there was no leader, or the requester *is* the leader:
        this.projectLeader = newLeader.getInitials();
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

    public void addActivity(
            Developer requester,
            String name, 
            int[] weekPlan, 
            int[] yearPlan)
            throws ErrorMessage {

        // ---- Basic validation ----
        if (!requester.getInitials().equals(this.projectLeader) && this.projectLeader != null) {
            throw new ErrorMessage("Developer is not project leader");
        }

        if (containsActivityName(name)) {
            throw new ErrorMessage("Activity title already exists");
        }

        int[] weekPlanCopy = weekPlan.clone();
        int[] yearPlanCopy = yearPlan.clone();

        // ---- Create and register the activity ----
        Activity newActivity = new Activity(
            name,
            this.projectNumber,
            weekPlanCopy,
            yearPlanCopy);

        activities.add(newActivity);
    }

    public Boolean containsActivityName(String name) {
        for (Activity activity : activities) {
            if (activity.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

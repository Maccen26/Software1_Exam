package dtu.domain;

import java.util.ArrayList;
import java.util.Objects;

import io.cucumber.java.hu.De;

public class Project {
    private String projectNumber;
    private String projectLeader;
    private ArrayList<Activity> activities;
    private String status;

    public Project(String projectNumber) { // Lukas
        // Initialise the project number and project leader
        this.projectNumber = projectNumber;
        this.projectLeader = null;
        this.activities = new ArrayList<Activity>();
        this.status = "Not started";
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
            int[] yearPlan, 
            ArrayList<Developer> assignedDevelopers) 
            throws ErrorMessage {

        // ---- Basic validation ----
        Objects.requireNonNull(requester, "Caller must not be null");

        if (!requester.getInitials().equals(this.projectLeader) && this.projectLeader != null) {
            throw new ErrorMessage("Developer is not project leader");
        }

        Objects.requireNonNull(name, "Activity name must not be null");

        if (name.trim().isEmpty()) {
            throw new ErrorMessage("Activity name must not be blank");
        }

        if (containsActivityName(name)) {
            throw new ErrorMessage("Activity title already exists");
        }
        if (weekPlan == null || weekPlan.length != 2) {
            throw new ErrorMessage("Week plan must contain exactly two integers: {startWeek, endWeek}");
        }
        if (yearPlan == null || yearPlan.length != 2) {
            throw new ErrorMessage("Year plan must contain exactly two integers: {startYear, endYear}");
        }


        // ---- Defensive copies & safe defaults ----
        ArrayList<Developer> devs = (assignedDevelopers == null)
            ? new ArrayList<>() 
            : new ArrayList<>(assignedDevelopers); // shallow copy is fine here

        int[] weekPlanCopy = weekPlan.clone();
        int[] yearPlanCopy = yearPlan.clone();
        int timeBudget = 0;

        // ---- Create and register the activity ----
        Activity newActivity = new Activity(
            name,
            this.projectNumber,
            devs,
            timeBudget,
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

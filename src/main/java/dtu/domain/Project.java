package dtu.domain;

import java.util.ArrayList;
import java.util.Objects;

import io.cucumber.java.en_old.Ac;
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
    public ArrayList<Activity> getAllActivities() //MAds
        {
            return activities;

        }

    public String getProjectNumber() { // Lukas
        // Get the project number
        return projectNumber;
    }

    public String getProjectLeader() { // Lukas
        // Get the project leader
        return projectLeader;
    }

    public Activity getActivity(String activityName) throws ErrorMessage{ // Lukas
        // Get an activity by its name
        for (Activity activity : activities) {
            if (activity.getName().equals(activityName)) {
                return activity;
            }
        }
        throw new ErrorMessage("Activity not found");
    }

    public String getReport(Developer dev) throws ErrorMessage{ // Lukas
        if (!dev.getInitials().equals(this.projectLeader)) {
            throw new ErrorMessage("Only the project leader can get the report");
        }

        if (this.activities.isEmpty()) {
            return projectNumber + ", no activities";
        }

        // Get the report for the project
        String report = projectNumber + ": " + status + "\n";
        for (Activity activity : activities) {
            String status = activity.getStatus();
            report += activity.getName() + ": " + status + "\n";

        }
        return report;
    }

    public void addActivity( //Johan
            Developer requester,
            String name, 
            int[] weekPlan, 
            int[] yearPlan)
            throws ErrorMessage {

        // ---- Basic validation ----
        if (!requester.getInitials().equals(this.projectLeader) && this.projectLeader != null) {
            throw new ErrorMessage("Developer is not projectleader");
        }

        if (containsActivityName(name)) {
            throw new ErrorMessage("Activity title already exists");
        }

        int[] weekPlanCopy = weekPlan.clone();
        int[] yearPlanCopy = yearPlan.clone();

        // ---- Create and register the activity ----
        Activity newActivity = new Activity(
            name,
            this,
            weekPlanCopy,
            yearPlanCopy);
        //newActivity.addDeveloper(requester); //SKAL IKKE TILFØJE EN AKTIVITET TIL EN DEN SAMME BRUGER - SÅ KAN PROJEJTLEDEREN KUN TILFØJE AKTIVITETR TIL SIG SELV?
        activities.add(newActivity);
    }

    public Boolean containsActivityName(String name) { //Johan
        for (Activity activity : activities) {
            if (activity.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addDeveloperToActivity(String activityName, Developer developer) throws ErrorMessage { //;ads
        Activity activity = getActivity(activityName); 
        activity.addDeveloper(developer);
    }

    public void setActivtyStatus(String activityName, String status2) throws ErrorMessage {
        Activity activity = getActivity(activityName); 
        activity.setStatus(status2);
    }

}
